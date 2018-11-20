package ru.job4j.service;

import ru.job4j.domain.Person;

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
