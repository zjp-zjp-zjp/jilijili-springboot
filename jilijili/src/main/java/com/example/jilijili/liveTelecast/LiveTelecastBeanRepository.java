package com.example.jilijili.liveTelecast;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LiveTelecastBeanRepository extends JpaRepository<LiveTelecastBean, Long> {
    Optional<LiveTelecastBean> findUserByRoomId(Long roomId);

    Optional<LiveTelecastBean> findUserById(Long Id);
}



