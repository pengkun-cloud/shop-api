package com.fh.bill.controller;

import com.fh.annotation.Idempotency;
import com.fh.bill.service.BillInfoService;
import com.fh.bill.service.BillService;
import com.fh.common.ServerResponse;
import com.fh.util.RedisUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("bill")
public class BillController {


    @Autowired
    private BillService billService;

    @Autowired
    private BillInfoService billInfoService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("createBill")
    @Idempotency
    public ServerResponse createBill(String billJson, String memberJson, HttpServletRequest request, Integer payType){
        return billService.createBill(billJson, memberJson, request, payType);
    }


    //生成幂等性token
    @RequestMapping("getIdempotencyToken")
    public ServerResponse getIdempotencyToken(){

        String idempotencyToken = UUID.randomUUID().toString();

        redisUtil.set(idempotencyToken,idempotencyToken);

        return ServerResponse.success(idempotencyToken);
    }

}
