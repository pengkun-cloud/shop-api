package com.fh.bill.mapper;

import com.fh.bill.model.Bill;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BillMapper {

    void addBill(Bill bill);

}
