package com.enablero.todo.dal.dataprovider.impl;

import com.enablero.todo.dal.dataprovider.UserDataProviderInterface;
import com.enablero.todo.dal.entity.User;
import com.enablero.todo.dal.repository.UserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDataProviderImpl implements UserDataProviderInterface {

    private final UserRepositoryInterface userRepositoryInterface;

    @Autowired
    public UserDataProviderImpl(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }

    @Override
    public List<String> getAllowListUsers() {
        return userRepositoryInterface.getAllowListUsers();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepositoryInterface.getUserByEmail(email);
    }
}
