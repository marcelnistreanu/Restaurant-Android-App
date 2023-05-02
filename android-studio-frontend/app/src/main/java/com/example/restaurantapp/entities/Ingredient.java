package com.example.restaurantapp.entities;

public class Ingredient {

    private Long id;
    private String ingredientName;

    public Ingredient(Long id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
