package com.example.jilijili.service;

import com.example.jilijili.entity.User;
import com.example.jilijili.repository.UserRepository;
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

    public void addUser(User user) {
        if (userRepository.findUserByNickname(user.getNickname()).isPresent()) {
            throw new IllegalStateException("nick name taken");
        }
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalStateException("email taken");
        }
        if (user.getTel() != null && userRepository.findUserByTel(user.getTel()).isPresent()) {
            throw new IllegalStateException("telephone number taken");
        }
        userRepository.save(user);
    }

    public boolean userLogin(String nickname, String password) {
        Optional<User> thisUser = userRepository.findUserByNickname(nickname);
        if (!thisUser.isPresent()) {
            throw new IllegalStateException("no user named " + nickname);
        }
        return password.equals(thisUser.get().getPassword());
    }

    public Long getIdByNickname(String nickname) {
        Optional<User> thisUser = userRepository.findUserByNickname(nickname);
        if (!thisUser.isPresent()) {
            throw new IllegalStateException("no user named " + nickname);
        }
        return thisUser.get().getId();
    }

    public User getUserById(Long id) {
        Optional<User> thisUser = userRepository.findById(id);
        if (!thisUser.isPresent()) {
            throw new IllegalStateException("no user with id " + id);
        }
        return thisUser.get();
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
