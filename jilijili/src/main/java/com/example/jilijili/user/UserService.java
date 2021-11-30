package com.example.jilijili.user;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Resource
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void addUser(User user){
        if(userRepository.findUserByNickname(user.getNickname()).isPresent()){
            throw new IllegalStateException("nick name taken");
        }
        if(userRepository.findUserByEmail(user.getEmail()).isPresent()){
            throw new IllegalStateException("email taken");
        }
        if(userRepository.findUserByTel(user.getTel()).isPresent()){
            throw new IllegalStateException("telephone number taken");
        }
        userRepository.save(user);
    }

    public boolean userLogin(String nickname, String password) {
        Optional<User> thisUser=userRepository.findUserByNickname(nickname);
        if(!thisUser.isPresent()){
            throw new IllegalStateException("no user named "+nickname);
        }
        if(password.equals(thisUser.get().getPassword())){
            return true;
        }
        return false;
    }
    public Long getIdByNickname(String nickname){
        Optional<User> thisUser=userRepository.findUserByNickname(nickname);
        if(!thisUser.isPresent()){
            throw new IllegalStateException("no user named "+nickname);
        }
        return thisUser.get().getId();
    }
    public User getUserById(Long id){
        Optional<User> thisUser= userRepository.findById(id);
        if(!thisUser.isPresent()){
            throw new IllegalStateException("no user with id "+id);
        }
        return thisUser.get();
    }
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
}
