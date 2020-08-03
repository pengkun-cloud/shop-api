package com.fh.memberShop.mapper;

import com.fh.memberShop.model.MemberShop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MemberShopMapper {



    List<MemberShop> queryMemberList();

    void updateAddressStatus();

    void updateAddress(Integer id);

}
