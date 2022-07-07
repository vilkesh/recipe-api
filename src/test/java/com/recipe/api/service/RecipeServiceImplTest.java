package com.recipe.api.service;

import com.recipe.api.dataaccessobjects.RecipeRepository;
import com.recipe.api.datatransferobject.RecipeDTO;
import com.recipe.api.domainobjects.Ingredients;
import com.recipe.api.domainobjects.Instructions;
import com.recipe.api.domainobjects.Recipes;
import com.recipe.api.exception.ConstraintsViolationException;
import com.recipe.api.exception.EntityNotFoundException;
import com.recipe.api.specification.RecipeSearchCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static com.recipe.api.TestConstants.INGREDIENT_1;
import static com.recipe.api.TestConstants.INSTRUCTION_1;
import static com.recipe.api.TestConstants.RECIPE_NAME;
import static com.recipe.api.TestConstants.UPDATED_RECIPE_NAME;
import static com.recipe.api.TestConstants.USER_01;
import static com.recipe.api.domainobjects.RecipeType.NONVEG;
import static com.recipe.api.domainobjects.RecipeType.VEG;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class RecipeServiceImplTest {

    private final RecipeRepository recipeRepository = Mockito.mock(RecipeRepository.class);
    private final RecipeServiceImpl recipeService = new RecipeServiceImpl(recipeRepository);

    @Test
    void find() throws EntityNotFoundException {
        //given
        Recipes recipes = getRecipes();
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipes));

        //when

        RecipeDTO recipeDTO = recipeService.find(1L);

        //then
        assertThat(recipeDTO).isNotNull();
        assertThat(recipeDTO.getId()).isEqualTo(1);
        assertThat(recipeDTO.getRecipeType()).isEqualTo(VEG);
        assertThat(recipeDTO.getIngredients()).isNotEmpty();
        assertThat(recipeDTO.getIngredients()).hasSize(1);
        assertThat(recipeDTO.getInstructions()).isNotEmpty();
        assertThat(recipeDTO.getInstructions()).hasSize(1);
        assertThat(recipeDTO.getServings()).isEqualTo(4);
        assertThat(recipes.getUserName()).isEqualTo(USER_01);
    }

    @Test
    void create() throws ConstraintsViolationException {
        //given
        Recipes recipes = getRecipes();
        when(recipeRepository.save(recipes)).thenReturn(recipes);

        //when
        recipeService.create(recipes);

        //then
        assertThat(recipes).isNotNull();
        assertThat(recipes.getId()).isEqualTo(1);
        assertThat(recipes.getRecipeType()).isEqualTo(VEG);
        assertThat(recipes.getIngredients()).isNotEmpty();
        assertThat(recipes.getIngredients()).hasSize(1);
        assertThat(recipes.getInstructions()).isNotEmpty();
        assertThat(recipes.getInstructions()).hasSize(1);
        assertThat(recipes.getServings()).isEqualTo(4);
        assertThat(recipes.getUserName()).isEqualTo(USER_01);
    }

    @Test
    void update() throws EntityNotFoundException {
        //given
        Recipes recipes = getRecipes();
        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipes));
        recipes.setRecipeType(NONVEG);
        recipes.setServings(5);
        recipes.setName(UPDATED_RECIPE_NAME);
        when(recipeRepository.save(recipes)).thenReturn(recipes);

        //when
        recipeService.update(recipes);

        //then
        assertThat(recipes).isNotNull();
        assertThat(recipes.getId()).isEqualTo(1);
        assertThat(recipes.getName()).isEqualTo(UPDATED_RECIPE_NAME);
        assertThat(recipes.getRecipeType()).isEqualTo(NONVEG);
        assertThat(recipes.getIngredients()).isNotEmpty();
        assertThat(recipes.getIngredients()).hasSize(1);
        assertThat(recipes.getInstructions()).isNotEmpty();
        assertThat(recipes.getInstructions()).hasSize(1);
        assertThat(recipes.getServings()).isEqualTo(5);
        assertThat(recipes.getUserName()).isEqualTo(USER_01);
    }

    @Test
    void search() {
        //given
        RecipeSearchCriteria searchCriteria = new RecipeSearchCriteria(VEG, 4, null, false, null);
        List<Recipes> recipesList = new ArrayList<>();
        recipesList.add(getRecipes());
        when(recipeRepository.findAll(any())).thenReturn(recipesList);

        //when
        Set<RecipeDTO> searchResults = recipeService.search(searchCriteria);

        //then
        assertThat(searchResults).isNotEmpty();
        assertThat(searchResults).hasSize(1);
        searchResults.forEach(recipeDTO -> {
            assertThat(recipeDTO.getRecipeType()).isEqualTo(VEG);
            assertThat(recipeDTO.getIngredients()).isNotEmpty();
            assertThat(recipeDTO.getIngredients()).hasSize(1);
            assertThat(recipeDTO.getInstructions()).isNotEmpty();
            assertThat(recipeDTO.getInstructions()).hasSize(1);
            assertThat(recipeDTO.getServings()).isEqualTo(4);
            assertThat(recipeDTO.getUserName()).isEqualTo(USER_01);
        });
    }

    private Recipes getRecipes() {
        Recipes recipes = new Recipes();
        recipes.setId(1L);
        recipes.setRecipeType(VEG);
        recipes.setServings(4);
        recipes.setUserName(USER_01);
        recipes.setName(RECIPE_NAME);
        Ingredients ingredient = new Ingredients();
        ingredient.setIngredient(INGREDIENT_1);
        List<Ingredients> ingredientsList = new ArrayList<>();
        ingredientsList.add(ingredient);
        recipes.setIngredients(ingredientsList);
        Instructions instruction = new Instructions();
        instruction.setInstruction(INSTRUCTION_1);
        List<Instructions> instructionsList = new ArrayList<>();
        instructionsList.add(instruction);
        recipes.setInstructions(instructionsList);
        return recipes;
    }
}
