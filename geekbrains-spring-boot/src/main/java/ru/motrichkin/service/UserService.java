package ru.motrichkin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.motrichkin.persistence.User;
import ru.motrichkin.persistence.jpa.UserRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(UserRepresentation userRepr) {
        User user = new User();
        user.setId(userRepr.getId());
        user.setUsername(userRepr.getUsername());
        user.setPassword(passwordEncoder.encode(userRepr.getPassword()));
        user.setRoles(userRepr.getRoles());
        userRepository.save(user);
    }

    public List<UserRepresentation> findAll() {
        return userRepository.findAll().stream()
                .map(UserRepresentation::new)
                .collect(Collectors.toList());
    }

    public Optional<UserRepresentation> findById(Long id) {
        return userRepository.findById(id)
                .map(UserRepresentation::new);
    }

    public Optional<UserRepresentation> findByUserName(String username) {
        return userRepository.findUserByUsername(username).map(UserRepresentation::new);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
