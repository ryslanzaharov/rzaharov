package com.example.carplatform.crudrepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.carplatform.models.Car;

import java.util.List;

public interface CarDataRepository extends CrudRepository<Car, Integer> {
    @Query("select c from Car c left join fetch c.user left join fetch c.engine" +
            " left join fetch c.condition")
    List<Car> getAll();

    @Query("select c from Car c where user_id=:user_id")
    List<Car> getByUserId(@Param("user_id") Integer user_id);

    @Query("select c from Car c left join fetch c.user left join fetch c.engine " +
            " left join fetch c.condition order by c.date desc")
    List<Car> getLastDay();

    @Query("select c from Car c left join fetch c.user left join fetch c.engine " +
            " left join fetch c.condition where c.mark=:mark")
    List<Car> getByMark(@Param("mark") String mark);
}
