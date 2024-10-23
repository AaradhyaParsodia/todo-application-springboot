package com.enablero.todo.service.impl;

import com.enablero.todo.dal.dataprovider.UserDataProviderInterface;
import com.enablero.todo.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    private final UserDataProviderInterface userDataProviderInterface;

    @Autowired
    public UserService(UserDataProviderInterface userDataProviderInterface) {
        this.userDataProviderInterface = userDataProviderInterface;
    }

    @Override
    public List<String> getAllowListUsers() {
        return userDataProviderInterface.getAllowListUsers();
    }

}
