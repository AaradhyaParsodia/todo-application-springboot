//package com.enablero.todo.controller;
//
//import com.enablero.todo.dal.repository.UserRepository;
//import org.springframework.graphql.data.method.annotation.QueryMapping;
//import org.springframework.stereotype.Controller;
//
//import java.util.List;
//
//@Controller
//public class UserController {
//
//    private final UserRepository userRepository;
//
//    public UserController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @QueryMapping
//    public List<String> getAllowListUsers() {
//        return userRepository.getAllowListUsers();
//    }
//}
