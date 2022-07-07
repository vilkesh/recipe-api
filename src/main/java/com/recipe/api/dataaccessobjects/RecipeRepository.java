package com.recipe.api.dataaccessobjects;

import com.recipe.api.domainobjects.Recipes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipes, Long>, JpaSpecificationExecutor<Recipes> {
}
