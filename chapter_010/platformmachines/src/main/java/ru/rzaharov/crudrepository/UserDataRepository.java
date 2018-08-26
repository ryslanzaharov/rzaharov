package ru.rzaharov.crudrepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.rzaharov.models.User;

import java.util.List;

public interface UserDataRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where login=:login")
    List<User> getUserByLogin(@Param("login") String login);

    @Query("select u from User u")
    List<User> getAll();
}
