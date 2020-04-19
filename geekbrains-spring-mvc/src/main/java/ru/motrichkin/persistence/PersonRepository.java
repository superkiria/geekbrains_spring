package ru.motrichkin.persistence;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void insert(Person person) {
        entityManager.persist(person);
    }

    public List<Person> getAllPersons() {
        return entityManager.createQuery("from Person").getResultList();
    }
}
