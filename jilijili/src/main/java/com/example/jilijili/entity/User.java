package com.example.jilijili.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String password;
    private String email;
    private String tel;
    @JsonFormat(pattern = "yyyy-M-d")
    private LocalDate dob;
    @JsonFormat(pattern = "yyyy-M-d")
    private LocalDate registerDate;
    private Integer gender;
    private Integer type;
    private byte[] head;

    public User() {

    }

    public User(String nickname, String email, String password) {
        this.nickname = nickname;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        this.password = encoder.encode(password);
        this.email = email;
        this.dob = LocalDate.now();
        this.tel = "empty";
        this.registerDate = LocalDate.now();
        this.gender = 1;
        this.type = 0;
        String path = Objects.requireNonNull(User.class.getClassLoader().getResource("")).getPath() + "pictures/head.png";
//        String path = "D:\\JavaEE\\jilijili-springboot\\jilijili\\target\\classes\\pictures\\head.png";
        File picture = new File(path);
        try {
            this.head = getByte(picture);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public byte[] getHead() {
        return head;
    }

    public void setHead(byte[] head) {
        this.head = head;
    }

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

    public static byte[] getByte(File file) throws Exception {
        byte[] bytes = null;
        if (file != null) {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            bytes = new byte[length];
            int offset = 0;
            int numRead;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            //如果得到的字节长度和file实际的长度不一致就可能出错了
            if (offset < bytes.length) {
                System.out.println("file length is error");
                return null;
            }
            is.close();
        }
        return bytes;
    }
}
