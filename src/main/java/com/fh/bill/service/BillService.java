package com.fh.bill.service;

import com.fh.common.ServerResponse;

import javax.servlet.http.HttpServletRequest;

public interface BillService {

    ServerResponse createBill(String billJson, String memberJson, HttpServletRequest request,Integer payType);
}
