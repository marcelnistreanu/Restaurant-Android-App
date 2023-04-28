package com.example.springbootbackend.services;

import com.example.springbootbackend.dto.UserDto;
import com.example.springbootbackend.user.User;
import com.example.springbootbackend.user.UserRepository;
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
}
