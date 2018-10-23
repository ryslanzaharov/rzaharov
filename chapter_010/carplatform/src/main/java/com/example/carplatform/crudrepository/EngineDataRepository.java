package com.example.carplatform.crudrepository;

import org.springframework.data.repository.CrudRepository;
import com.example.carplatform.models.Engine;

public interface EngineDataRepository extends CrudRepository<Engine, Integer> {
}
