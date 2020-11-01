package com.benz.redis.controller;

import com.benz.redis.model.User;
import com.benz.redis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {



    private UserService userService;

	
	 @Autowired
     public UserController(UserService userService) {
	  this.userService=userService;
	 }


    @PostMapping("/save")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void saveUser(@RequestBody User user)
    {
        if(!user.getUserId().trim().isEmpty() && !user.getUserName().trim().isEmpty())
            userService.save(user);
    }

    @GetMapping("/all")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Map<String,User> getUsers()
    {
       return userService.getUsers();
    }

    @PostMapping("/{userId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public User getUser(@PathVariable("userId") String userId)
    {
        if(!userId.trim().isEmpty())
        return userService.getUser(userId);
        else
            throw new NullPointerException();
    }

    @PutMapping("/update")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public void updateUser(@RequestBody User user)
    {
        if(!user.getUserId().trim().isEmpty() && !user.getUserName().trim().isEmpty())
            userService.update(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") String userId)
    {
        if(!userId.trim().isEmpty())
            userService.delete(userId);
    }


    
}
