package com.example.Bookstore.Service;

import com.example.Bookstore.Constants.UserRole;
import com.example.Bookstore.RequestBody.LoginBody;
import com.example.Bookstore.RequestBody.RegistrationBody;
import com.example.Bookstore.Model.User;
import com.example.Bookstore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String registerUser(RegistrationBody registrationBody) {
        Optional<User> existingEmail = userRepository.findByEmail(registrationBody.getEmail());
        Optional<User> existingUsername = userRepository.findByUsername(registrationBody.getUsername());

        if (existingEmail.isPresent()) {
            throw new IllegalArgumentException("Email is already in use!");
        }
        if (existingUsername.isPresent()) {
            throw new IllegalArgumentException("Username is already in use!");
        }

        User user = new User();
        user.setUsername(registrationBody.getUsername());
        user.setEmail(registrationBody.getEmail());
        user.setPassword(registrationBody.getPassword());
        user.setRole(UserRole.CLIENT);

        userRepository.save(user);
        return "Registration successful!";
    }

}



