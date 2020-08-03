package com.fh.memberShop.controller;

import com.fh.common.ServerResponse;
import com.fh.memberShop.service.MemberShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("memberShop")
public class MemberShopController {

    @Autowired
    private MemberShopService memberShopService;


    @RequestMapping("queryList")
    public ServerResponse queryList(){
        return memberShopService.queryList();
    }

    @RequestMapping("updateAddress")
    public ServerResponse updateAddress(@RequestParam("id") Integer id){
        return memberShopService.updateAddress(id);
    }
}
