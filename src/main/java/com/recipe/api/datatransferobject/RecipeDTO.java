package com.recipe.api.datatransferobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.recipe.api.domainobjects.RecipeType;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDTO {
    private Long id;
    @NotNull(message = "name can not be null!")
    private String name;
    @NotNull(message = "servings can not be null!")
    private Integer servings;
    @NotNull(message = "userName can not be null!")
    private String userName;
    @NotNull(message = "type can not be null!")
    private RecipeType recipeType;
    private ZonedDateTime dateCreated;
    @NotEmpty(message = "ingredients can not be empty")
    private List<IngredientDTO> ingredients;
    @NotEmpty(message = "instructions can not be empty")
    private List<InstructionDTO> instructions;

    public RecipeDTO(Long id, String name, Integer servings, String userName, RecipeType recipeType, ZonedDateTime dateCreated,
                     List<IngredientDTO> ingredients, List<InstructionDTO> instructions) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.userName = userName;
        this.recipeType = recipeType;
        this.dateCreated = dateCreated;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public RecipeDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getServings() {
        return servings;
    }

    public String getUserName() {
        return userName;
    }

    public RecipeType getRecipeType() {
        return recipeType;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public List<InstructionDTO> getInstructions() {
        return instructions;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeDTO recipeDTO = (RecipeDTO) o;
        return id.equals(recipeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static RecipeDTOBuilder builder() {
        return new RecipeDTOBuilder();
    }

    public static class RecipeDTOBuilder {
        private Long id;
        private String name;
        private Integer servings;
        private String userName;
        private RecipeType recipeType;
        private ZonedDateTime dateCreated;
        private List<IngredientDTO> ingredients;
        private List<InstructionDTO> instructions;

        public RecipeDTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RecipeDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public RecipeDTOBuilder withServings(Integer servings) {
            this.servings = servings;
            return this;
        }

        public RecipeDTOBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public RecipeDTOBuilder withRecipeType(RecipeType recipeType){
            this.recipeType = recipeType;
            return this;
        }

        public RecipeDTOBuilder withDateCreated(ZonedDateTime dateCreated) {
            this.dateCreated = dateCreated;
            return this;
        }

        public RecipeDTOBuilder withIngredientList(List<IngredientDTO> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public RecipeDTOBuilder withInstructionList(List<InstructionDTO> instructions) {
            this.instructions = instructions;
            return this;
        }

        public RecipeDTO build() {
            return new RecipeDTO(id, name, servings, userName,recipeType,dateCreated, ingredients,instructions);
        }
    }
}
