package com.fh.bill.mapper;

import com.fh.bill.model.BillInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BillInfoMapper {

    void addBillInfoList(List<BillInfo> billInfoList);
}
