package com.benz.redis.dao.impl;

import com.benz.redis.dao.UserDAO;
import com.benz.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserDAO {


    private HashOperations<String,String,User> hashOperations;
    
    
    
    @Autowired
    public UserRepositoryImpl(RedisTemplate<String,User> redisTemplate) {
    	
	    this.hashOperations=redisTemplate.opsForHash();
	}

    @Override
    public void save(User user) {

        hashOperations.put("USER",user.getUserId(),user);
    }

    @Override
    public Map<String, User> findAll() {

       return hashOperations.entries("USER");
     

    }

    @Override
    public User findByUserId(String userId) {

            return (User)hashOperations.get("USER", userId);
    }

    @Override
    public void update(User user) {

        hashOperations.put("USER",user.getUserId(),user);
    }

    @Override
    public void delete(String userId) {

        hashOperations.delete("USER",userId);
    }
}
