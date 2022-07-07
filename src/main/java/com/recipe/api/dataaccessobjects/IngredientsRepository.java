package com.recipe.api.dataaccessobjects;

import com.recipe.api.domainobjects.Ingredients;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientsRepository extends CrudRepository<Ingredients,Long> {
}
