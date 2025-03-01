package com.example.ExternalApi.client.domain;

import java.util.List;

public class RecipeResponse {
    private List<Recipe> recipes;
    private int total;
    private int skip;
    private int limit;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
