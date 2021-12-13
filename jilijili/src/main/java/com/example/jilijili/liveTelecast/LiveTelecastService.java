package com.example.jilijili.liveTelecast;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class LiveTelecastService {
    @Resource
    private final  LiveTelecastBeanRepository liveTelecastRepository;

    public LiveTelecastService(LiveTelecastBeanRepository liveTelecastRepository) {
        this.liveTelecastRepository = liveTelecastRepository;
    }

    public  List<LiveTelecastBean> getAllRooms() {
        return liveTelecastRepository.findAll();
    }
    public LiveTelecastBean getRoomById(Long Id) {
        return liveTelecastRepository.findUserById(Id).get();
    }

    public void addNewRoom(LiveTelecastBean room) {
        Optional<LiveTelecastBean> findByRoomId = liveTelecastRepository
                .findUserByRoomId(room.getRoomId());
        if(findByRoomId.isPresent()){
            System.out.println("already exist");
        }
        else {
            liveTelecastRepository.save(room);
            System.out.println(room);
        }

    }

    public void deleteRoom(Long id) {
        boolean result =liveTelecastRepository.existsById(id);
        if(!result){
            throw new IllegalStateException("id: "+id+"   is not exist");
        }
        liveTelecastRepository.deleteById(id);
    }
}
