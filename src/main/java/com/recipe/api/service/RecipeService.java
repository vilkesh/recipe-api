package com.recipe.api.service;

import com.recipe.api.datatransferobject.RecipeDTO;
import com.recipe.api.domainobjects.Recipes;
import com.recipe.api.exception.ConstraintsViolationException;
import com.recipe.api.exception.EntityNotFoundException;
import com.recipe.api.specification.RecipeSearchCriteria;
import java.util.Set;

public interface RecipeService {
    RecipeDTO find(final Long id) throws EntityNotFoundException;

    Recipes create(Recipes recipeDO) throws ConstraintsViolationException;

    void delete(Long recipeId) throws EntityNotFoundException;

    Recipes update(Recipes recipes) throws EntityNotFoundException;

    Set<RecipeDTO> search(RecipeSearchCriteria searchCriteria);
}
