package ru.motrichkin.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.motrichkin.persistence.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
