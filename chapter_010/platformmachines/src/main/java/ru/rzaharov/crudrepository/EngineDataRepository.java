package ru.rzaharov.crudrepository;

import org.springframework.data.repository.CrudRepository;
import ru.rzaharov.models.Engine;

public interface EngineDataRepository extends CrudRepository<Engine, Integer> {
}
