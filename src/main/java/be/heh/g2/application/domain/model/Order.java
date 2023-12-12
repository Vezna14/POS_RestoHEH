package be.heh.g2.application.domain.model;

import java.util.List;

public class Order {
    private int id;
    private int idTable;
    private List<ProductOrder> productList;
    private boolean isPaid;
    private double totalPrice;
    private String date;

    public Order(int id, int idTable, List<ProductOrder> productList, boolean isPaid, double totalPrice, String date) {
        this.id = id;
        this.idTable = idTable;
        this.productList = productList;
        this.isPaid = isPaid;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", price=" + totalPrice + " table="
                + idTable + ", isPaid=" + isPaid + ", date=" + date + ", productList=" + productList + "]";
    }

    public int getId() {
        return id;
    }

    public int getIdTable() {
        return idTable;
    }

    public List<ProductOrder> getProductList() {
        return productList;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public void setProductList(List<ProductOrder> productList) {
        this.productList = productList;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
