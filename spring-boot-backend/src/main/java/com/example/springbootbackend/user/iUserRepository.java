package com.example.springbootbackend.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface iUserRepository extends JpaRepository<User, Long> {

    User findByLoginCodeAndPassword(String loginCode, String password);

}
