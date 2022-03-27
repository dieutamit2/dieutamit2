package com.example.foodapp_midterm;

import java.io.Serializable;

public class Food implements Serializable {
    public int id;
    public String foodName;
    public String foodDescription;
    public int ratingbar;
    public double foodPrice;

    public Food(int id, String foodName, String foodDescription, int ratingbar, double foodPrice) {
        this.id = id;
        this.foodName = foodName;
        this.foodDescription = foodDescription;
        this.ratingbar = ratingbar;
        this.foodPrice = foodPrice;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public int getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(int ratingbar) {
        this.ratingbar = ratingbar;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
}
