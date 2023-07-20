package com.postexpress.Postrexpress.service;


import com.postexpress.Postrexpress.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);
    User readById(long id);
    User update(User user);
    void delete(long id);
    User findByEmail(String email);
    Optional<User> findByUserEmail(String email);
    List<User> getAll();
    boolean authenticateUser(String username, String password);
    boolean userExistsById(long id);
}


