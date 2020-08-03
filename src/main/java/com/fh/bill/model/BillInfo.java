package com.fh.bill.model;

import java.math.BigDecimal;

public class BillInfo {

    private String billId;

    private String name;

    private String filePath;

    private Integer productId;

    private Integer count;

    private BigDecimal price;

    @Override
    public String toString() {
        return "BillInfo{" +
                "billId='" + billId + '\'' +
                ", name='" + name + '\'' +
                ", filePath='" + filePath + '\'' +
                ", productId=" + productId +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
