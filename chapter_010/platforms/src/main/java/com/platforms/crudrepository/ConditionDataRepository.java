package com.platforms.crudrepository;

import com.platforms.models.Condition;
import org.springframework.data.repository.CrudRepository;

public interface ConditionDataRepository extends CrudRepository<Condition, Integer> {
}
