package ru.rzaharov.crudrepository;

import org.springframework.data.repository.CrudRepository;
import ru.rzaharov.models.Condition;

public interface ConditionDataRepository extends CrudRepository<Condition, Integer> {
}
