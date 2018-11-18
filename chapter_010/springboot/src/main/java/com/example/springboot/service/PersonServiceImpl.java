package com.example.springboot.service;


import com.example.springboot.domain.Person;
import com.example.springboot.domain.User;
import com.example.springboot.repository.PersonRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Autowired
    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Person> getAll() {
        return Lists.newArrayList(this.repository.findAll());
    }

    @Override
    public Person add(Person person) {
        return this.repository.save(person);
    }
}
