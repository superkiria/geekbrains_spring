package ru.motrichkin.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.motrichkin.persistence.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
