package com.example.springbootbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    iUserRepository iUserRepository;

    public List<User> getListUser() {
        return iUserRepository.findAll();
    }

    public void createUser(User user) {
        iUserRepository.save(user);
    }

    public boolean checkUsersCredentials(String loginCode, String password) {
        User user = iUserRepository.findByLoginCodeAndPassword(loginCode, password);
        return user != null;
    }

    public User getUserById(Long id){
        return iUserRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id: " + id + " was not found."));
    }


}
