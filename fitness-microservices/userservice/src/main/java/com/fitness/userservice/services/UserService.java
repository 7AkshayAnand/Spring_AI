package com.fitness.userservice.services;

import com.fitness.userservice.Repository.UserRepository;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {


    private final UserRepository userRepository;



    public UserResponse register(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("User with email already exists");
        }

        User user=new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User savedUser= userRepository.save(user);


        UserResponse response=new UserResponse();
        response.setId(savedUser.getId());
        response.setPassword(savedUser.getPassword());
        response.setEmail(savedUser.getEmail());
        response.setLastName(savedUser.getLastName());
        response.setFirstName(savedUser.getFirstName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());

        return response;


    }

    public UserResponse getUserProfile(String userId){
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));

        UserResponse response=new UserResponse();
        response.setId(user.getId());
        response.setPassword(user.getPassword());
        response.setEmail(user.getEmail());
        response.setLastName(user.getLastName());
        response.setFirstName(user.getFirstName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());

        return response;

    }
}
