package com.orilinc.portfolio.service;

import com.orilinc.portfolio.model.User;

public interface UserService {
    Iterable<User> findAllUsers();
    User addUser(User client);
}
