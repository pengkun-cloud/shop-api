package com.fh.memberShop.service;


import com.fh.common.ServerResponse;

public interface MemberShopService {

    ServerResponse queryList();

    ServerResponse updateAddress(Integer id);


}
