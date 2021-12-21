package com.example.jilijili.user;

import com.example.jilijili.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByNickname(String nickname);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByTel(String tel);

    @Query(value = "select * from user where(id LIKE concat('%',?1,'%')or nickname LIKE concat('%',?1,'%'))", nativeQuery = true)
    List<User> findAllBySearch(String search);
}
