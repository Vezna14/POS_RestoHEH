package be.heh.g2.application.domain.model;

import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Product {
    private long id;
    private String name;
    private double price;
    private ArrayList<String> category;
    private int stock;
    private String photo;


    public Product(long id,String name) {
        this.id=id;
        this.name = name;
        this.price=0;
        this.category=new ArrayList<String>();
        this.stock=0;
        this.photo="";
    }
    public Product(long id, String name, double price, ArrayList<String> category, int stock, String photo) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock=stock;
        this.photo=photo;

    }

    public Product(long id, String name, double price,String category, int stock, String photo) {
        this.id=id;
        this.name = name;
        this.price = price;
        this.category=new ArrayList<String>();
        this.category.add(category);
        this.stock=stock;
        this.photo=photo;

    }

    // Getters et setters

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<String> getCategory() {
        return category;
    }
    public void setCategory(ArrayList category) {
        this.category = category;
    }

    public void addCategory(String categoryToAdd){
        this.category.add(categoryToAdd);
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
