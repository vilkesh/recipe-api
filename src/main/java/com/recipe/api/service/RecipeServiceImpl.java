package com.recipe.api.service;

import com.recipe.api.controller.mapper.RecipeMapper;
import com.recipe.api.dataaccessobjects.RecipeRepository;
import com.recipe.api.datatransferobject.RecipeDTO;
import com.recipe.api.domainobjects.Recipes;
import com.recipe.api.exception.ConstraintsViolationException;
import com.recipe.api.exception.EntityNotFoundException;
import com.recipe.api.specification.RecipeSearchCriteria;
import com.recipe.api.specification.RecipeSpecification;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final Logger LOG = LoggerFactory.getLogger(RecipeServiceImpl.class);

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    @Transactional
    public RecipeDTO find(Long recipeId) throws EntityNotFoundException {
        Optional<Recipes> recipes = recipeRepository.findById(recipeId);
        if(recipes.isPresent()){
            return RecipeMapper.convertToDTO(recipes.get());
        }else{
            throw new EntityNotFoundException("Recipe Not Found with ID = " + recipeId);
        }
    }

    @Override
    public Recipes create(Recipes recipes) throws ConstraintsViolationException {
        Recipes recipe;
        try {
            recipe = recipeRepository.save(recipes);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("ConstraintsViolationException while creating a recipe: {}", recipes, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return recipe;
    }

    @Override
    public void delete(Long recipeId) throws EntityNotFoundException {
        try {
            recipeRepository.deleteById(recipeId);
        }catch(EmptyResultDataAccessException ex){
            throw new EntityNotFoundException("Recipe Not Found with ID = " + recipeId);
        }
    }

    @Override
    public Recipes update(Recipes recipes) throws EntityNotFoundException {
        Recipes updatedRecipe;
        if(recipeRepository.findById(recipes.getId()).isPresent()){
            updatedRecipe = recipeRepository.save(recipes);
        }else{
            throw new EntityNotFoundException("Recipe Not Found with ID = " + recipes.getId());
        }
        return updatedRecipe;
    }

    @Override
    @Transactional
    public Set<RecipeDTO> search(RecipeSearchCriteria searchCriteria) {
        RecipeSpecification specification = new RecipeSpecification(searchCriteria);
        List<Recipes> recipesList = recipeRepository.findAll(specification);
        return recipesList.stream()
            .filter(Objects::nonNull)
            .map(RecipeMapper::convertToDTO)
            .collect(Collectors.toSet());
    }
}
