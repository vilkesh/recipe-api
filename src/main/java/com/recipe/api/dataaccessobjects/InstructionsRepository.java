package com.recipe.api.dataaccessobjects;

import com.recipe.api.domainobjects.Instructions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionsRepository extends CrudRepository<Instructions,Long> {
}
