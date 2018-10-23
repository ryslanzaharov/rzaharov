package com.example.carplatform.crudrepository;

import org.springframework.data.repository.CrudRepository;
import com.example.carplatform.models.Condition;

public interface ConditionDataRepository extends CrudRepository<Condition, Integer> {
}
