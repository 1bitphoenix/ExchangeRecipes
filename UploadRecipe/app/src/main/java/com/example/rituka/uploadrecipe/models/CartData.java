package com.example.rituka.uploadrecipe.models;

/**
 * Created by rituka on 28/10/17.
 */

public class CartData implements java.io.Serializable{

    private String state , dishname;
    private int id  , quantity;

    public CartData(String dishname , String state , int id)
    {
        this.id = id;
        this.state = state;
        this.dishname = dishname;
        this.quantity = 0 ;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getDishname() {
        return dishname;
    }

    public String getState() {
        return state;
    }

}
