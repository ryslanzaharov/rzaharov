package com.platforms.crudrepository;

import com.platforms.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserDataRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where username=:username")
    Optional<User> getUserByLogin(@Param("username") String login);

    @Query("select u from User u")
    List<User> getAll();
}
