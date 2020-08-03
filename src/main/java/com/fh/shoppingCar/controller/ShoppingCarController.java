package com.fh.shoppingCar.controller;

import com.fh.common.ServerResponse;
import com.fh.shoppingCar.service.ShoppingCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("shoppingCar")
public class ShoppingCarController {

    @Autowired
    private ShoppingCarService shoppingCarService;

    @RequestMapping("addShoppingCar")
    public ServerResponse addShoppingCar(Integer count, Integer id, HttpServletRequest request){
        return shoppingCarService.addShoppingCar(count, id,request);
    }

    @RequestMapping("queryShoppingCarCount")
    public ServerResponse queryShoppingCarCount(HttpServletRequest request){
        return shoppingCarService.queryShoppingCarCount(request);
    }

    @RequestMapping("queryRedisShoppingCar")
    public ServerResponse queryRedisShoppingCar(HttpServletRequest request){
        return shoppingCarService.queryRedisShoppingCar(request);
    }

    @RequestMapping("deleteShoppingCar/{productId}")
    public ServerResponse deleteShoppingCar(HttpServletRequest request, @PathVariable("productId")Integer productId){

        return shoppingCarService.deleteShoppingCar(request, productId);
    }



}
