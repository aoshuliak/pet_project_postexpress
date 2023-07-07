package com.postexpress.Postrexpress.service;


import com.postexpress.Postrexpress.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User readById(long id);
    User update(User user);
    void delete(long id);
    User findByEmail(String email);
    List<User> getAll();
    boolean userExistsById(long id);
}

