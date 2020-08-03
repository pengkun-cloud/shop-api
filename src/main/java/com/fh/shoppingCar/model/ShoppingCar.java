package com.fh.shoppingCar.model;

import java.math.BigDecimal;

public class ShoppingCar {

    private Integer productId;

    private String productName;

    private String filePath;

    private int count;

    private BigDecimal price;

    @Override
    public String toString() {
        return "ShoppingCar{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
