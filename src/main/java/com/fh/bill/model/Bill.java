package com.fh.bill.model;

import java.math.BigDecimal;

public class Bill {

    private String id;

    private Integer memberShopId;

    private BigDecimal totalPrice;

    private Integer addressId1;

    private Integer addressId2;

    private Integer addressId3;

    private Integer payType;

    private Integer status;


    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", memberShopId=" + memberShopId +
                ", totalPrice=" + totalPrice +
                ", addressId1=" + addressId1 +
                ", addressId2=" + addressId2 +
                ", addressId3=" + addressId3 +
                ", payType=" + payType +
                ", status=" + status +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMemberShopId() {
        return memberShopId;
    }

    public void setMemberShopId(Integer memberShopId) {
        this.memberShopId = memberShopId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getAddressId1() {
        return addressId1;
    }

    public void setAddressId1(Integer addressId1) {
        this.addressId1 = addressId1;
    }

    public Integer getAddressId2() {
        return addressId2;
    }

    public void setAddressId2(Integer addressId2) {
        this.addressId2 = addressId2;
    }

    public Integer getAddressId3() {
        return addressId3;
    }

    public void setAddressId3(Integer addressId3) {
        this.addressId3 = addressId3;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
