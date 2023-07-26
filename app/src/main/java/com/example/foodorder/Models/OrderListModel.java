package com.example.foodorder.Models;
public class OrderListModel  {
    int image;
    String foodName,orderNumber,price;

    public OrderListModel(int image, String foodName, String orderNumber, String price) {
        this.image = image;
        this.foodName = foodName;
        this.orderNumber = orderNumber;
        this.price = price;
    }

    public OrderListModel() {

    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return foodName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setName(String name) {
        this.foodName = name;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
