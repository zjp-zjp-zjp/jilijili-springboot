package com.example.jilijili.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nickname;
    private String password;
    private String email;
    private String tel;
    @JsonFormat(pattern="yyyy-M-d")
    private LocalDate dob;
    @JsonFormat(pattern="yyyy-M-d")
    private LocalDate registerDate;
    private Integer gender;
    private Integer type;

    public User() {

    }

    public User(String nickname, String email,String password) {
        this.nickname = nickname;
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(10);
        String _password=encoder.encode(password);
        this.password = _password;
        this.email = email;
        this.dob=LocalDate.now();
        this.tel="empty";
        this.registerDate= LocalDate.now();
        this.gender=1;
        this.type=1;
    }

//    public User(String nickname, String password, String email, String tel, LocalDate dob, LocalDate registerDate, Integer gender) {
//        this.nickname = nickname;
//        this.password = password;
//        this.email = email;
//        this.tel = tel;
//        this.dob = dob;
//        this.registerDate = registerDate;
//        this.gender = gender;
//    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", dob=" + dob +
                ", registerDate=" + registerDate +
                ", gender=" + gender +
                ", type=" + type +
                '}';
    }
}
