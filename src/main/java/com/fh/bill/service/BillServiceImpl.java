package com.fh.bill.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.bill.mapper.BillInfoMapper;
import com.fh.bill.mapper.BillMapper;
import com.fh.bill.model.Bill;
import com.fh.bill.model.BillInfo;
import com.fh.common.Const;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.memberShop.model.MemberShop;
import com.fh.product.model.Product;
import com.fh.product.service.ProductService;
import com.fh.shoppingCar.model.ShoppingCar;
import com.fh.util.BigDecimalUtil;
import com.fh.util.IdUtil;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillMapper billMapper;

    @Autowired
    private BillInfoMapper billInfoMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    @Transient
    public ServerResponse createBill(String billJson, String memberJson, HttpServletRequest request,Integer payType) {

        //收获人信息
        List<MemberShop> memberShopList = JSON.parseArray(memberJson, MemberShop.class);
        //商品信息
        List<ShoppingCar> shoppingCarList = JSON.parseArray(billJson, ShoppingCar.class);
        //订单详情集合
        List<BillInfo> billInfoList = new ArrayList<>();
        //库存不足的商品集合
        List<Product> stockNotFull = new ArrayList<>();
        //订单号
        String billId = IdUtil.createId();
        //总价格
        BigDecimal totalPrice = new BigDecimal("0.00");
        //去数据库查询库存是否充足
        for (ShoppingCar shoppingCar : shoppingCarList) {
            Product productDB = productService.getProductById(shoppingCar.getProductId());
            if(productDB.getStock() < shoppingCar.getCount()){
                //库存不足的商品
                stockNotFull.add(productDB);
            }else{
                //如果库存充足，先去将数据库的库存更改，在判断更改后的库存是否充足
                Long res = productService.updateProductStock(productDB.getId(), shoppingCar.getCount());
                if(res == 1){
                    //说明更改之后的库存仍满足，这时可以生成订单详情
                    BillInfo billInfo = getBillInfo(billId, shoppingCar);
                    billInfoList.add(billInfo);
                    BigDecimal subTotal = BigDecimalUtil.mul(shoppingCar.getPrice().toString(), shoppingCar.getCount()+"");
                    totalPrice = BigDecimalUtil.add(totalPrice, subTotal);
                }else {
                    stockNotFull.add(productDB);
                }
            }
        }
        if(billInfoList.size() == shoppingCarList.size()){
            //库存充足，保存订单详情
            billInfoMapper.addBillInfoList(billInfoList);
            //同步redis购物车中的数量
            updateRedisShoppingCar(request, billInfoList);
            //生成订单
            Bill bill = getBill(payType, memberShopList, billId, totalPrice);
            billMapper.addBill(bill);
            return ServerResponse.success(billId);
        }else{
            return ServerResponse.error(stockNotFull);
        }
    }

    //生成订单
    private Bill getBill(Integer payType, List<MemberShop> memberShopList, String billId, BigDecimal totalPrice) {
        MemberShop memberShop = memberShopList.get(0);
        Bill bill = new Bill();
        bill.setId(billId);
        bill.setMemberShopId(memberShop.getId());
        bill.setStatus(Const.BILL_STATUS_FINISH);
        bill.setTotalPrice(totalPrice);
        bill.setPayType(payType);
        bill.setAddressId1(memberShop.getAddressId1());
        bill.setAddressId2(memberShop.getAddressId2());
        bill.setAddressId3(memberShop.getAddressId3());
        return bill;
    }

    //更新购物车中的数量
    private void updateRedisShoppingCar(HttpServletRequest request, List<BillInfo> billInfoList) {
        Member member = (Member) request.getSession().getAttribute(Const.SESSION_KEY);
        for (BillInfo billInfo : billInfoList) {
            String shoppingCarJson = (String) redisUtil.hget(Const.SHOPPING_CAR_KET + member.getId(), billInfo.getProductId().toString());
            ShoppingCar shoppingCar = JSON.parseObject(shoppingCarJson, ShoppingCar.class);
            if(billInfo.getCount() < shoppingCar.getCount()){
                shoppingCar.setCount(shoppingCar.getCount() - billInfo.getCount());
                String s = JSONObject.toJSONString(shoppingCar);
                redisUtil.hset(Const.SHOPPING_CAR_KET + member.getId(), billInfo.getProductId().toString(), s);
            }else{
                redisUtil.hdel(Const.SHOPPING_CAR_KET + member.getId(), billInfo.getProductId().toString());
            }
        }
    }

    //生成订单详情
    private BillInfo getBillInfo(String billId, ShoppingCar shoppingCar) {
        BillInfo billInfo = new BillInfo();
        billInfo.setBillId(billId);
        billInfo.setProductId(shoppingCar.getProductId());
        billInfo.setFilePath(shoppingCar.getFilePath());
        billInfo.setCount(shoppingCar.getCount());
        billInfo.setName(shoppingCar.getProductName());
        billInfo.setPrice(shoppingCar.getPrice());
        return billInfo;
    }
}
