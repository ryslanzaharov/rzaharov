package com.platforms.crudrepository;

import com.platforms.models.Engine;
import org.springframework.data.repository.CrudRepository;

public interface EngineDataRepository extends CrudRepository<Engine, Integer> {
}
