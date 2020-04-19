package ru.motrichkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.motrichkin.persistence.Person;
import ru.motrichkin.persistence.PersonRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void insert(Person person) {
        personRepository.insert(person);
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return personRepository.getAllPersons();
    }

}
