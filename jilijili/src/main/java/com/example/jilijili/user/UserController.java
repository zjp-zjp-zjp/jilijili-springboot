package com.example.jilijili.user;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping(path = "account")
@RestController
public class UserController {
    @Resource
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(path = "register")
    public void registerUser(@RequestBody User user){
        userService.addUser(user);
    }
    @PostMapping(path = "login")
    public boolean userLogin(@RequestParam String nickname,@RequestParam String password){
        return userService.userLogin(nickname,password);
    }
    @GetMapping
    public List<User> getUser(){
        return userService.getAllUser();
    }
}
