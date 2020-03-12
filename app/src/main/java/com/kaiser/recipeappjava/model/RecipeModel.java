package com.kaiser.recipeappjava.model;

public class RecipeModel {

    private String recipeName;
    private String recipeImageURL;
    private String recipeType;
    private String recipeIngredients;
    private String recipeStep;

    public RecipeModel(String recipeName, String recipeImageURL, String recipeType, String recipeIngredients, String recipeStep) {
        this.recipeName = recipeName;
        this.recipeImageURL = recipeImageURL;
        this.recipeType = recipeType;
        this.recipeIngredients = recipeIngredients;
        this.recipeStep = recipeStep;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImageURL() {
        return recipeImageURL;
    }

    public void setRecipeImageURL(String recipeImageURL) {
        this.recipeImageURL = recipeImageURL;
    }

    public String getRecipeType() {
        return recipeType;
    }

    public void setRecipeType(String recipeType) {
        this.recipeType = recipeType;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public String getRecipeStep() {
        return recipeStep;
    }

    public void setRecipeStep(String recipeStep) {
        this.recipeStep = recipeStep;
    }
}