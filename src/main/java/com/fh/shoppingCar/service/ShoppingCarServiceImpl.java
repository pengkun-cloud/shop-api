package com.fh.shoppingCar.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.common.Const;
import com.fh.common.ServerEnum;
import com.fh.common.ServerResponse;
import com.fh.member.model.Member;
import com.fh.product.model.Product;
import com.fh.product.service.ProductService;
import com.fh.shoppingCar.model.ShoppingCar;
import com.fh.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCarServiceImpl implements ShoppingCarService {

    @Autowired
    private ProductService productService;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public ServerResponse addShoppingCar(Integer count, Integer id, HttpServletRequest request) {
        //将信息添加到redis中 key field value memberId productId product
        //获取当前登录用户
        Member member = (Member) request.getSession().getAttribute(Const.SESSION_KEY);
        //验证商品是否存在
        Product product = productService.getProductById(id);
        if(product == null){
            return ServerResponse.notExistError();
        }
        //判断商品是否上架
        if(product.getStatus() == 0){
            return ServerResponse.notOnError();
        }
        //判断购物车中是否存在
        boolean isExist = redisUtil.hasKey(Const.SHOPPING_CAR_KET +member.getId().toString());
        String shoppingCarJsonRedis = (String) redisUtil.hget(Const.SHOPPING_CAR_KET + member.getId().toString(), product.getId().toString());
        if(!isExist || shoppingCarJsonRedis == null){
            //如果不存在，将信息存入redis中
            ShoppingCar shoppingCar = new ShoppingCar();
            shoppingCar.setProductName(product.getName());
            shoppingCar.setPrice(product.getPrice());
            shoppingCar.setCount(count);
            shoppingCar.setFilePath(product.getFilePath());
            shoppingCar.setProductId(product.getId());
            String shoppingCarJson = JSONObject.toJSONString(shoppingCar);
            redisUtil.hset(Const.SHOPPING_CAR_KET + member.getId().toString(), product.getId().toString(),shoppingCarJson);
        }else{
            //如果存在则将购物车数量改变
            String productJson = (String) redisUtil.hget(Const.SHOPPING_CAR_KET + member.getId().toString(), product.getId().toString());
            ShoppingCar shoppingCarProduct = JSON.parseObject(productJson, ShoppingCar.class);
            shoppingCarProduct.setCount(shoppingCarProduct.getCount() + count);
            String shoppingCarProductJson = JSONObject.toJSONString(shoppingCarProduct);
            redisUtil.hset(Const.SHOPPING_CAR_KET + member.getId().toString(), product.getId().toString(),shoppingCarProductJson);
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse queryShoppingCarCount(HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(Const.SESSION_KEY);
        Map<Object, Object> map = redisUtil.hmget(Const.SHOPPING_CAR_KET + member.getId());
        int count = 0;
        if(map != null && map.size() > 0){
            for (Object o : map.keySet()) {
                System.out.println(o);
                String stringShoppingCar  = (String) map.get(o);
                ShoppingCar shoppingCar = JSON.parseObject(stringShoppingCar, ShoppingCar.class);
                count += shoppingCar.getCount();
            }
        }
        return ServerResponse.success(count);
    }

    @Override
    public ServerResponse queryRedisShoppingCar(HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(Const.SESSION_KEY);
        Map<Object, Object> map = redisUtil.hmget(Const.SHOPPING_CAR_KET + member.getId());
        List<ShoppingCar> list = new ArrayList<>();
        if(map != null && map.size() > 0){
            for (Object o : map.keySet()) {
                System.out.println(o);
                String stringShoppingCar  = (String) map.get(o);
                ShoppingCar shoppingCar = JSON.parseObject(stringShoppingCar, ShoppingCar.class);
                list.add(shoppingCar);
            }
        }
        return ServerResponse.success(list);
    }

    @Override
    public ServerResponse deleteShoppingCar(HttpServletRequest request, Integer productId) {
        Member member = (Member) request.getSession().getAttribute(Const.SESSION_KEY);
        redisUtil.hdel(Const.SHOPPING_CAR_KET + member.getId(), productId.toString());

        return ServerResponse.success();
    }
}
