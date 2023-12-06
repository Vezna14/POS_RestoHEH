package be.heh.g2.application.domain.model;


import java.util.ArrayList;

public class TableResto {
    private int id;
    private String name;
    private String status;
    private int seats;
    private boolean reserved;
    private ArrayList<Order> currentOrder;
    private String occupationTime;


    public TableResto(int id, String name, String status, int seats, boolean reserved, ArrayList<Order> currentOrder, String occupationTime) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.seats = seats;
        this.reserved = reserved;
        this.currentOrder = currentOrder;
        this.occupationTime = occupationTime;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getSeats() {
        return seats;
    }

    public boolean isReserved() {
        return reserved;
    }

    public ArrayList<Order> getCurrentOrder() {
        return currentOrder;
    }

    public String getOccupationTime() {
        return occupationTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setCurrentOrder(ArrayList<Order> currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void setOccupationTime(String occupationTime) {
        this.occupationTime = occupationTime;
    }
}
