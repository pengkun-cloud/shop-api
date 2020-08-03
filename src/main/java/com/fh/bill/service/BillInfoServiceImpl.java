package com.fh.bill.service;


import com.fh.bill.mapper.BillInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillInfoServiceImpl implements BillInfoService {

    @Autowired
    private BillInfoMapper billInfoMapper;
}
