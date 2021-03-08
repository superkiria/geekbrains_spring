package ru.motrichkin.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.motrichkin.persistence.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String userName);
}
