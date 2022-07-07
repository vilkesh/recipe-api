package com.recipe.api.specification;

import com.recipe.api.domainobjects.Ingredients;
import com.recipe.api.domainobjects.Instructions;
import com.recipe.api.domainobjects.Recipes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.isNotBlank;
/**
 * Specification to build up the query to retrieve recipes from search parameters.
 *
 */
public class RecipeSpecification implements Specification<Recipes> {

    public static final String INSTRUCTIONS = "instructions";

    public static final String INGREDIENTS = "ingredients";

    public static final String INSTRUCTION = "instruction";

    public static final String INGREDIENT = "ingredient";

    public static final String RECIPE_TYPE = "recipeType";

    public static final String SERVINGS = "servings";

    private static final String LIKE_WILDCARD = "%";
    private static final String RECIPE = "recipe";

    private final RecipeSearchCriteria recipeSearchCriteria;

    public RecipeSpecification(RecipeSearchCriteria recipeSearchCriteria) {
        this.recipeSearchCriteria = recipeSearchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Recipes> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        if(nonNull(recipeSearchCriteria.getType())){
            predicates.add(cb.equal(cb.upper(root.<String>get(RECIPE_TYPE)),recipeSearchCriteria.getType()));
        }
        if(nonNull(recipeSearchCriteria.getServings())){
            predicates.add(cb.equal(root.<Integer>get(SERVINGS), recipeSearchCriteria.getServings()));
        }
        if(recipeSearchCriteria.isInclude() && isNotBlank(recipeSearchCriteria.getIngredient())){
            Path<Ingredients> ingredientsPath = root.join(INGREDIENTS);
            predicates.add(cb.like(ingredientsPath.get(INGREDIENT), LIKE_WILDCARD + recipeSearchCriteria.getIngredient() + LIKE_WILDCARD));
        }
        if(!recipeSearchCriteria.isInclude() && isNotBlank(recipeSearchCriteria.getIngredient())){
            Subquery<Long> subQuery = query.subquery(Long.class);
		    Root<Ingredients> ingredientsRoot = subQuery.from(Ingredients.class);
		    subQuery.select(ingredientsRoot.get(RECIPE)).where(cb.like(ingredientsRoot.get(INGREDIENT), LIKE_WILDCARD + recipeSearchCriteria.getInstruction() + LIKE_WILDCARD));
            predicates.add(cb.not(cb.exists(subQuery)));
        }
        if (isNotBlank(recipeSearchCriteria.getInstruction())) {
            Path<Instructions> instructionsPath = root.join(INSTRUCTIONS);
            predicates.add(cb.like(instructionsPath.get(INSTRUCTION), LIKE_WILDCARD + recipeSearchCriteria.getInstruction() + LIKE_WILDCARD));
        }
        return andTogether(predicates, cb);
    }

    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
