package com.recipe.api.controller;

import com.recipe.api.controller.mapper.RecipeMapper;
import com.recipe.api.datatransferobject.RecipeDTO;
import com.recipe.api.domainobjects.RecipeType;
import com.recipe.api.domainobjects.Recipes;
import com.recipe.api.exception.ConstraintsViolationException;
import com.recipe.api.exception.EntityNotFoundException;
import com.recipe.api.service.RecipeService;
import com.recipe.api.specification.RecipeSearchCriteria;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/recipe")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping(value = "/{recipeId}")
    public ResponseEntity<RecipeDTO> getRecipe(@PathVariable long recipeId) throws EntityNotFoundException {
        return ResponseEntity.ok().body(recipeService.find(recipeId));
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> createRecipe(@Valid @RequestBody RecipeDTO recipeDTO) throws ConstraintsViolationException {
        Recipes recipeDO = RecipeMapper.convertToEntity(recipeDTO);
        return new ResponseEntity<>(RecipeMapper.convertToDTO(recipeService.create(recipeDO)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable long recipeId) throws EntityNotFoundException {
        recipeService.delete(recipeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RecipeDTO> updateRecipe(@Valid @RequestBody RecipeDTO recipeDTO) throws EntityNotFoundException {
        Recipes recipes = RecipeMapper.convertToEntity(recipeDTO);
        return new ResponseEntity<>(RecipeMapper.convertToDTO(recipeService.update(recipes)), HttpStatus.OK);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Set<RecipeDTO>> search(@RequestParam(value = "recipeType", required = false) RecipeType recipeType,
                                            @RequestParam(value = "servings", required = false) Integer servings,
                                            @RequestParam(value = "ingredient", required = false) String ingredient,
                                            @RequestParam(value = "isInclude", required = false) boolean isInclude,
                                            @RequestParam(value = "instruction", required = false) String instruction){

        //instantiate a search criteria
        RecipeSearchCriteria searchCriteria = new RecipeSearchCriteria(recipeType,servings,ingredient,isInclude,instruction);
        Set<RecipeDTO> recipeDTOSet = recipeService.search(searchCriteria);
        return ResponseEntity.ok().body(recipeDTOSet);
    }
}
