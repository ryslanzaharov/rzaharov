package com.example.springboot.repository;

import com.example.springboot.domain.Person;
import org.springframework.data.repository.CrudRepository;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public interface PersonRepository extends CrudRepository<Person, Integer> {
}
