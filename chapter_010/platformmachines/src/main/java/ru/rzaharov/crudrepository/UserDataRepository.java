package ru.rzaharov.crudrepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.rzaharov.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDataRepository extends CrudRepository<User, Integer> {

    @Query("select u from User u where login=:login")
    Optional<User> getUserByLogin(@Param("login") String login);

    @Query("select u from User u")
    List<User> getAll();
}
