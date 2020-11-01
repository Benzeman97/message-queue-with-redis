package com.benz.redis.dao;

import com.benz.redis.model.User;

import java.util.Map;


public interface UserDAO {

    void save(User user);
    Map<String,User> findAll();
    User findByUserId(String userId);
    void update(User user);
    void delete(String userId);

}
