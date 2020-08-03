package com.fh.product.service;

import com.fh.common.ServerResponse;
import com.fh.param.Param;
import com.fh.product.mapper.ProductMapper;
import com.fh.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;


    @Override
    public ServerResponse queryHotList() {
        List<Product> listHot = productMapper.queryHotList();

        return ServerResponse.success(listHot);
    }

    @Override
    public ServerResponse queryAllList() {
        List<Product> allList = productMapper.queryAllList();
        return ServerResponse.success(allList);
    }

    @Override
    public ServerResponse queryPageList(Param param) {
        List<Product> list = productMapper.queryPageList(param);
        Long totalCount = productMapper.queryTotalCount();
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("totalCount", totalCount);
        return ServerResponse.success(map);
    }

    @Override
    public Product getProductById(Integer id) {


        return productMapper.getProductById(id);
    }

    @Override
    public Long updateProductStock(Integer id, int count) {

        Map<String, Integer> map = new HashMap<>();
        map.put("id", id);
        map.put("count", count);
        return productMapper.updateProductStock(map);
    }
}
