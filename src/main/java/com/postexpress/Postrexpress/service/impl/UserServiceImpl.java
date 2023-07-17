package com.postexpress.Postrexpress.service.impl;

import com.postexpress.Postrexpress.model.Package;
import com.postexpress.Postrexpress.model.User;
import com.postexpress.Postrexpress.repository.UserRepository;
import com.postexpress.Postrexpress.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.get();
    }

    @Override
    public User update(User user) {
        User oldUser = readById(user.getId());
        return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(readById(id));
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }
    
    public User findByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public Optional<User> findByUserEmail(String email) {
        return userRepository.getByUserEmail(email);
    }

    public boolean authenticateUser(String email, String password) {
        User user = findByEmail(email);
        return user != null && password.equals(user.getPassword());
    }

    @Override
    public boolean userExistsById(long id) {
        return userRepository.existsById(id);
    }
}