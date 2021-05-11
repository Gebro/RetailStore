package com.example.RetailStore.service;

import com.example.RetailStore.model.User;
import com.example.RetailStore.model.UserType;
import com.example.RetailStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static java.util.Calendar.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;



    public User createUser(String name,UserType type,String memberSince){
        User user =new User();
        user.setName(name);
        user.setType(type);
        user.setMemberSince(memberSince);
        user = this.userRepository.save(user);
        return user;

    }


}
