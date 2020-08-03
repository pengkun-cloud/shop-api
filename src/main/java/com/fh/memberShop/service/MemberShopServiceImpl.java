package com.fh.memberShop.service;

import com.fh.common.ServerResponse;
import com.fh.memberShop.mapper.MemberShopMapper;
import com.fh.memberShop.model.MemberShop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberShopServiceImpl implements MemberShopService {

    @Autowired
    private MemberShopMapper memberShopMapper;


    @Override
    public ServerResponse queryList() {

        List<MemberShop> list = memberShopMapper.queryMemberList();

        return ServerResponse.success(list);
    }

    @Override
    @Transient
    public ServerResponse updateAddress(Integer id) {
        memberShopMapper.updateAddressStatus();

        memberShopMapper.updateAddress(id);
        return ServerResponse.success();
    }
}
