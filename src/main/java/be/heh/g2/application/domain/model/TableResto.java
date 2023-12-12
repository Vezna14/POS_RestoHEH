package be.heh.g2.application.domain.model;


import java.util.ArrayList;

public class TableResto {
    private int id;
    private String name;
    private String status;
    private int seats;
    private boolean reserved ;




    public TableResto(int id, String name, String status, int seats, boolean reserved ) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.seats = seats;
        this.reserved = reserved;


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

    /*public Order getCurrentOrder() {
        return currentOrder;
    }*/



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

    /*public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }*/


}
