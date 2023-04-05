package com.example.springbootbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface iUserRepository extends JpaRepository<User, Long> {

    User findByLoginCodeAndPassword(String loginCode, String password);

    Optional<User> findById(Long id);

}
