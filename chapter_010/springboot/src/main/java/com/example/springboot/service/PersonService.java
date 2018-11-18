package com.example.springboot.service;



import com.example.springboot.domain.Person;
import com.example.springboot.domain.User;

import java.util.List;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public interface PersonService {
    List<Person> getAll();

    Person add(Person person);
}
