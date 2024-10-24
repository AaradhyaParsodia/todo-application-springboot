package com.enablero.todo.dal.repository;

import com.enablero.todo.dal.entity.User;

import java.util.List;

public interface UserRepositoryInterface {
    List<String> getAllowListUsers();
    User getUserByEmail(String email);
}
