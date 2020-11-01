package com.benz.redis.services;

import com.benz.redis.dao.impl.UserRepositoryImpl;
import com.benz.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {


      @Autowired
      UserRepositoryImpl userRepository;

    public void save(User user)
    {
            userRepository.save(user);
    }
    public User getUser(String userId)
    {
         return userRepository.findByUserId(userId);
    }

    public Map<String,User> getUsers()
    {
      
    	return userRepository.findAll();
      
    }

    public void update(User user)
    {
        userRepository.update(user);
    }

    public void delete(String userId)
    {
        userRepository.delete(userId);
    }

}
