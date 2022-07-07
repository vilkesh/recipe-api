package com.recipe.api.specification;

import com.recipe.api.domainobjects.RecipeType;

public class RecipeSearchCriteria {
    private final RecipeType type;
    private final Integer servings;
    private final String ingredient;
    private final boolean isInclude;
    private final String instruction;

    public RecipeSearchCriteria(RecipeType type, Integer servings, String ingredient, boolean isInclude, String instruction) {
        this.type = type;
        this.servings = servings;
        this.ingredient = ingredient;
        this.isInclude = isInclude;
        this.instruction = instruction;
    }

    public RecipeType getType() {
        return type;
    }

    public Integer getServings() {
        return servings;
    }

    public String getIngredient() {
        return ingredient;
    }

    public boolean isInclude() {
        return isInclude;
    }

    public String getInstruction() {
        return instruction;
    }
}
