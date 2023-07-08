package com.postexpress.Postrexpress.service;


import com.postexpress.Postrexpress.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);
    User readById(long id);
    User update(User user);
    void delete(long id);
    User findByEmail(String email);
    List<User> getAll();
    boolean userExistsById(long id);
}


