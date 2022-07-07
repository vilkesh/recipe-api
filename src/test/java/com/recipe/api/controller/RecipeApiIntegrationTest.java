package com.recipe.api.controller;

import com.recipe.api.RecipeApiApplicationTests;
import com.recipe.api.dataaccessobjects.RecipeRepository;
import com.recipe.api.datatransferobject.IngredientDTO;
import com.recipe.api.datatransferobject.InstructionDTO;
import com.recipe.api.datatransferobject.RecipeDTO;
import com.recipe.api.domainobjects.Recipes;
import com.recipe.api.exception.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static com.recipe.api.TestConstants.GITHERI;
import static com.recipe.api.TestConstants.INGREDIENT;
import static com.recipe.api.TestConstants.INGREDIENT_1;
import static com.recipe.api.TestConstants.INGREDIENT_2;
import static com.recipe.api.TestConstants.INSTRUCTION_1;
import static com.recipe.api.TestConstants.INSTRUCTION_2;
import static com.recipe.api.TestConstants.INSTRUCTION_3;
import static com.recipe.api.TestConstants.INSTRUCTION_4;
import static com.recipe.api.TestConstants.POTATOES;
import static com.recipe.api.TestConstants.RECIPE_ID_DELETE;
import static com.recipe.api.TestConstants.RECIPE_ID_FAILED;
import static com.recipe.api.TestConstants.RECIPE_NAME;
import static com.recipe.api.TestConstants.RECIPE_TYPE;
import static com.recipe.api.TestConstants.SEARCH;
import static com.recipe.api.TestConstants.SERVING_COUNT;
import static com.recipe.api.TestConstants.SPANISH_RICE;
import static com.recipe.api.TestConstants.UPDATED_INGREDIENT_1;
import static com.recipe.api.TestConstants.UPDATED_INSTRUCTION_1;
import static com.recipe.api.TestConstants.UPDATED_INSTRUCTION_2;
import static com.recipe.api.TestConstants.UPDATED_RECIPE_NAME;
import static com.recipe.api.TestConstants.USER_01;
import static com.recipe.api.TestConstants.USER_02;
import static com.recipe.api.domainobjects.RecipeType.NONVEG;
import static com.recipe.api.domainobjects.RecipeType.VEG;
import static com.recipe.api.specification.RecipeSpecification.SERVINGS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RecipeApiIntegrationTest extends RecipeApiApplicationTests {

    public static final String BASE_PATH = "/v1/recipe/";


    @Autowired
    RecipeRepository recipeRepository;

    @Test
    @Order(1)
    void get_recipe_by_id_success() throws Exception {
        // when
        ResultActions resultActions = mvc().perform(get(BASE_PATH + "2")
            .accept(MediaType.APPLICATION_JSON));

        //then
        resultActions
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(SPANISH_RICE)))
            .andExpect(jsonPath("$.servings", is(4)))
            .andExpect(jsonPath("$.userName", is(USER_02)))
            .andExpect(jsonPath("$.recipeType", is(VEG.name())))
            .andExpect(jsonPath("$.ingredients", hasSize(3)))
            .andExpect(jsonPath("$.instructions", hasSize(6)));
    }

    @Test
    @Order(2)
    void get_recipe_by_id_fail() throws Exception {
        MvcResult mvcResult = mvc().perform(get(BASE_PATH + RECIPE_ID_FAILED)
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andReturn();
        Exception resolvedException = mvcResult.getResolvedException();
        assertThat(resolvedException).isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    @Order(3)
    void create_recipe_success() throws Exception {
        // given

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIngredient(INGREDIENT_1);
        IngredientDTO ingredientDTO2 = new IngredientDTO();
        ingredientDTO2.setIngredient(INGREDIENT_2);
        List<IngredientDTO> ingredientsList = new ArrayList<>();
        ingredientsList.add(ingredientDTO);
        ingredientsList.add(ingredientDTO2);

        InstructionDTO instructionDTO = new InstructionDTO();
        instructionDTO.setInstruction(INSTRUCTION_1);

        InstructionDTO instructionDTO2 = new InstructionDTO();
        instructionDTO2.setInstruction(INSTRUCTION_2);

        InstructionDTO instructionDTO3 = new InstructionDTO();
        instructionDTO3.setInstruction(INSTRUCTION_3);

        InstructionDTO instructionDTO4 = new InstructionDTO();
        instructionDTO4.setInstruction(INSTRUCTION_4);

        List<InstructionDTO> instructionDTOList = new ArrayList<>();
        instructionDTOList.add(instructionDTO);
        instructionDTOList.add(instructionDTO2);
        instructionDTOList.add(instructionDTO3);
        instructionDTOList.add(instructionDTO4);

        RecipeDTO recipeDTO = RecipeDTO.builder()
            .withUserName(USER_01)
            .withRecipeType(VEG)
            .withServings(5)
            .withName(RECIPE_NAME)
            .withIngredientList(ingredientsList)
            .withInstructionList(instructionDTOList)
            .build();

        String postContent = objectMapper.writeValueAsString(recipeDTO);

        // when then
        mvc().perform(post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(postContent))
            .andDo(print())
            .andExpect(jsonPath("$.name", is(RECIPE_NAME)))
            .andExpect(jsonPath("$.servings", is(5)))
            .andExpect(jsonPath("$.userName", is(USER_01)))
            .andExpect(jsonPath("$.recipeType", is(VEG.name())))
            .andExpect(jsonPath("$.ingredients", hasSize(2)))
            .andExpect(jsonPath("$.instructions", hasSize(4)));
    }

    @Test
    @Order(4)
    void delete_recipe() throws Exception {
        // when then
        mvc()
            .perform(delete(BASE_PATH + RECIPE_ID_DELETE))
            .andExpect(status().isOk());

        Optional<Recipes> recipes = recipeRepository.findById(Long.valueOf(RECIPE_ID_DELETE));
        assertThat(recipes).isEmpty();
    }

    @Test
    @Order(5)
    void update_recipe() throws Exception {
        // given
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIngredient(UPDATED_INGREDIENT_1);
        ingredientDTO.setId(1L);
        List<IngredientDTO> ingredientsList = new ArrayList<>();
        ingredientsList.add(ingredientDTO);

        InstructionDTO instructionDTO = new InstructionDTO();
        instructionDTO.setInstruction(UPDATED_INSTRUCTION_1);
        instructionDTO.setId(1L);

        InstructionDTO instructionDTO2 = new InstructionDTO();
        instructionDTO2.setInstruction(UPDATED_INSTRUCTION_2);
        instructionDTO.setId(2L);

        List<InstructionDTO> instructionDTOList = new ArrayList<>();
        instructionDTOList.add(instructionDTO);
        instructionDTOList.add(instructionDTO2);

        RecipeDTO recipeDTO = RecipeDTO.builder()
            .withId(1L)
            .withUserName(USER_01)
            .withRecipeType(VEG)
            .withServings(5)
            .withName(UPDATED_RECIPE_NAME)
            .withIngredientList(ingredientsList)
            .withInstructionList(instructionDTOList)
            .build();

        String requestContent = objectMapper.writeValueAsString(recipeDTO);

        // when then
        mvc().perform(put(BASE_PATH)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(requestContent))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name", is(UPDATED_RECIPE_NAME)))
            .andExpect(jsonPath("$.servings", is(5)))
            .andExpect(jsonPath("$.userName", is(USER_01)))
            .andExpect(jsonPath("$.recipeType", is(VEG.name())))
            .andExpect(jsonPath("$.ingredients", hasSize(1)))
            .andExpect(jsonPath("$.instructions", hasSize(2)));
    }

    @Test
    @Order(6)
    void search_veg_recipes() throws Exception {
        // when then
        mvc().perform(get(BASE_PATH + SEARCH)
                .param(RECIPE_TYPE,VEG.name())
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(5)))
            .andExpect(jsonPath("$[0].name", is(UPDATED_RECIPE_NAME)))
            .andExpect(jsonPath("$[0].servings", is(5)))
            .andExpect(jsonPath("$[0].recipeType", is(VEG.name())))
            .andExpect(jsonPath("$[0].ingredients", hasSize(1)))
            .andExpect(jsonPath("$[0].instructions", hasSize(2)))
            .andExpect(jsonPath("$[1].recipeType", is(VEG.name())))
            .andExpect(jsonPath("$[2].recipeType", is(VEG.name())))
            .andExpect(jsonPath("$[3].recipeType", is(VEG.name())))
            .andExpect(jsonPath("$[3].recipeType", is(VEG.name())));
    }

    @Test
    @Order(7)
    void search_non_veg_recipes_with_four_servings_and_potatoes_as_ingredient() throws Exception {
        // when then
        mvc().perform(get(BASE_PATH + SEARCH)
                .characterEncoding("UTF-8")
                .param(RECIPE_TYPE,NONVEG.name())
                .param(SERVINGS,SERVING_COUNT)
                .param(INGREDIENT,POTATOES)
                .param("isInclude","true")
                .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].name", is(GITHERI)))
            .andExpect(jsonPath("$[0].servings", is(5)))
            .andExpect(jsonPath("$[0].recipeType", is(NONVEG.name())))
            .andExpect(jsonPath("$[0].ingredients", hasSize(4)))
            .andExpect(jsonPath("$[0].instructions", hasSize(6)));
    }
}
