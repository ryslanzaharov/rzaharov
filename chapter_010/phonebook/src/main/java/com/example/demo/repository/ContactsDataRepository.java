package com.example.demo.repository;

import com.example.demo.models.Contacts;
import org.springframework.data.repository.CrudRepository;

public interface ContactsDataRepository extends CrudRepository<Contacts, Integer> {
}
