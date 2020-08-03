package com.fh.shoppingCar.service;

import com.fh.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface ShoppingCarService {

    ServerResponse addShoppingCar(Integer count, Integer id, HttpServletRequest request);

    ServerResponse queryShoppingCarCount(HttpServletRequest request);

    ServerResponse queryRedisShoppingCar(HttpServletRequest request);

    ServerResponse deleteShoppingCar(HttpServletRequest request, Integer productId);


}
