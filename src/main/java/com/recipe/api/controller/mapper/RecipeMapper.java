package com.recipe.api.controller.mapper;

import com.recipe.api.datatransferobject.IngredientDTO;
import com.recipe.api.datatransferobject.InstructionDTO;
import com.recipe.api.datatransferobject.RecipeDTO;
import com.recipe.api.domainobjects.Ingredients;
import com.recipe.api.domainobjects.Instructions;
import com.recipe.api.domainobjects.Recipes;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecipeMapper {

    public static RecipeDTO convertToDTO(Recipes recipes) {
        return RecipeDTO.builder()
            .withId(recipes.getId())
            .withName(recipes.getName())
            .withUserName(recipes.getUserName())
            .withRecipeType(recipes.getRecipeType())
            .withServings(recipes.getServings())
            .withIngredientList(convertToIngredientList(recipes.getIngredients()))
            .withInstructionList(convertToInstructionList(recipes.getInstructions()))
            .build();
    }

    private static List<IngredientDTO> convertToIngredientList(List<Ingredients> ingredientList) {
        return ingredientList.stream()
            .filter(Objects::nonNull)
            .sorted(Comparator.comparing(Ingredients::getId))
            .map(ingredients -> {
                IngredientDTO ingredientDTO = new IngredientDTO();
                ingredientDTO.setId(ingredients.getId());
                ingredientDTO.setIngredient(ingredients.getIngredient());
                return ingredientDTO;
            })
            .collect(Collectors.toList());
    }

    private static List<InstructionDTO> convertToInstructionList(List<Instructions> instructions) {
        List<Instructions> instructionsDOList = new ArrayList<>(instructions);
        return instructionsDOList.stream()
            .filter(Objects::nonNull)
            .sorted(Comparator.comparing(Instructions::getId))
            .map(instruction -> {
                InstructionDTO instructionDTO = new InstructionDTO();
                instructionDTO.setId(instruction.getId());
                instructionDTO.setInstruction(instruction.getInstruction());
                return instructionDTO;
            })
            .collect(Collectors.toList());
    }

    public static Recipes convertToEntity(RecipeDTO recipeDTO) {
        Recipes recipes = new Recipes();
        recipes.setId(recipeDTO.getId());
        recipes.setRecipeType(recipeDTO.getRecipeType());
        recipes.setName(recipeDTO.getName());
        recipes.setServings(recipeDTO.getServings());
        recipes.setUserName(recipeDTO.getUserName());
        List<Ingredients> ingredientsList = new ArrayList<>();
        recipeDTO.getIngredients().forEach(ingredientDto -> {
            Ingredients ingredients = new Ingredients();
            ingredients.setId(ingredientDto.getId());
            ingredients.setIngredient(ingredientDto.getIngredient());
            ingredients.setRecipe(recipes);
            ingredientsList.add(ingredients);
        });
        recipes.setIngredients(ingredientsList);
        List<Instructions> instructionsList = new ArrayList<>();
        recipeDTO.getInstructions().forEach(instructionDTO -> {
            Instructions instructions = new Instructions();
            instructions.setId(instructionDTO.getId());
            instructions.setInstruction(instructionDTO.getInstruction());
            instructions.setRecipe(recipes);
            instructionsList.add(instructions);
        });
        recipes.setInstructions(instructionsList);
        return recipes;
    }
}
