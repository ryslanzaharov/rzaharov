package com.example.demo.repository;

import com.example.demo.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleDataRepository extends CrudRepository<Role, Integer> {
}
