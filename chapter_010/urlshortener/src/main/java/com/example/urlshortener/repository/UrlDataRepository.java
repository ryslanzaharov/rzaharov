package com.example.urlshortener.repository;

import com.example.urlshortener.models.Url;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UrlDataRepository extends CrudRepository<Url, Integer> {


    @Query("select u from Url u where users_id=:users_id")
    public List<Url> getUrlsById(@Param("users_id") Integer users_id);
}
