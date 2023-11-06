package be.heh.g2.application.domain.model;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class Order {
    private long id;
    private double price;
    private double priceDiscount;
    private String clientName;
    boolean isPaid;
    private Date date;
    private ArrayList<Product> productList;

    public Order(long id, double price, double priceDiscount, String clientName, boolean isPaid, Date date,
                 ArrayList<Product> productList) {
        this.clientName = clientName;
        this.date = date;
        this.priceDiscount = priceDiscount;
        this.id = id;
        this.price = price;
        this.isPaid = isPaid;
        this.productList = productList;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productIdList) {
        this.productList = productIdList;
    }

    public long getId() {
        return id;
    }
    public Date getDate() {
        return date;
    }

    public double getPriceDiscount() {
        return priceDiscount;
    }
    public void setPriceDiscount(double priceDiscount) {
        this.priceDiscount = priceDiscount;
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", price=" + price + ", priceDiscount=" + priceDiscount + ", clientName="
                + clientName + ", isPaid=" + isPaid + ", date=" + date + ", productList=" + productList + "]";
    }

}
