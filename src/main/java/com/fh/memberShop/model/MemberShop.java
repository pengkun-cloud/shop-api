package com.fh.memberShop.model;

public class MemberShop {

    private Integer id;

    private String name;

    private Integer status;

    private String phone;

    private Integer addressId1;

    private Integer addressId2;

    private Integer addressId3;

    private String address;

    @Override
    public String toString() {
        return "MemberShop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", addressId1=" + addressId1 +
                ", addressId2=" + addressId2 +
                ", addressId3=" + addressId3 +
                ", address='" + address + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
