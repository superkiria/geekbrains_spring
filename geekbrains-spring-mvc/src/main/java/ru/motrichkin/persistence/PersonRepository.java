package ru.motrichkin.persistence;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class PersonRepository {

    private final List<Person> persons = new ArrayList<>();

    public PersonRepository() {
        persons.add(new Person("Иван", "Иванов"));
        persons.add(new Person("Петр", "Петров"));
        persons.add(new Person("Даня", "Данилов"));
    }

    public void insert(Person person) {
        persons.add(person);
    }

    public List<Person> getAllPersons() {
        return Collections.unmodifiableList(persons);
    }

}
