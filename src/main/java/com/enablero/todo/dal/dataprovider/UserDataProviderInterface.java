package com.enablero.todo.dal.dataprovider;

import com.enablero.todo.dal.entity.User;

import java.util.List;

public interface UserDataProviderInterface {
    List<String> getAllowListUsers();
    User getUserByEmail(String email);
}
