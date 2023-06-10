package com.example.springbootbackend.services;

import com.example.springbootbackend.dto.UserDto;
import com.example.springbootbackend.entities.User;
import com.example.springbootbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUser(String email){
        User user = userRepository.findByEmail(email).orElseThrow();
        UserDto userDto = new UserDto();
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().toString());
        return userDto;
    }

    public void updateUserInfo(String email, UserDto userDto) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        userRepository.save(user);
    }
}
