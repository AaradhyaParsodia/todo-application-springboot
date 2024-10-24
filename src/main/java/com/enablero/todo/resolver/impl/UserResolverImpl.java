package com.enablero.todo.resolver.impl;

import com.enablero.todo.resolver.UserResolverInterface;
import com.enablero.todo.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserResolverImpl implements UserResolverInterface {

    private final UserService userService;

    @Autowired
    public UserResolverImpl(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    @Override
    public List<String> getAllowListUsers() {
        return userService.getAllowListUsers();
    }
}
