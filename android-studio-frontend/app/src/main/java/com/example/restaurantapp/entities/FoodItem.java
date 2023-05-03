package com.example.restaurantapp.entities;


import java.util.HashSet;
import java.util.Set;

public class FoodItem {

    private Long id;
    private String foodName;
    private String description;
    private double price;
    private Integer calories;
    private String imageUrl;

    private Category category;

    private Set<Ingredient> ingredients = new HashSet<>();

    public FoodItem(Long id, String foodName, String description, double price, Integer calories, String imageUrl, Category category, Set<Ingredient> ingredients) {
        this.id = id;
        this.foodName = foodName;
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.imageUrl = imageUrl;
        this.category = category;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", calories=" + calories +
                ", imageUrl='" + imageUrl + '\'' +
                ", category=" + category +
                ", ingredients=" + ingredients +
                '}';
    }
}
