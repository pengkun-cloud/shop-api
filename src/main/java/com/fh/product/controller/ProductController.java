package com.fh.product.controller;

import com.fh.annotation.Ignore;
import com.fh.common.ServerResponse;
import com.fh.param.Param;
import com.fh.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //查询热销商品  热销功能待完成
    @RequestMapping("queryHotList")
    @Ignore
    public ServerResponse queryHotList(){

        return productService.queryHotList();
    }

    //查询所有集合
    @RequestMapping("queryAllList")
    @Ignore
    public ServerResponse queryAllList(){

        return productService.queryAllList();
    }

    //查询分页
    @RequestMapping("queryPageList")
    @Ignore
    public ServerResponse queryPageList(Param param){
        return productService.queryPageList(param);
    }
}
