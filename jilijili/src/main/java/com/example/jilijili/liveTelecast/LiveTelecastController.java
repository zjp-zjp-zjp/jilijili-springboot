package com.example.jilijili.liveTelecast;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//工作流程：接受路由，然后通过method去调用service类里面的服务
@Controller
@RequestMapping(path="LiveTelecast")//路由
public class LiveTelecastController {
    private final LiveTelecastService liveService;//启动的服务

    public LiveTelecastController(LiveTelecastService liveService){
        this.liveService=liveService;
    }

    //GET请求
    //显示所有的直播出来
    //@param:返回一个list里面有id,roomId,userId
    @GetMapping
    public ModelAndView getRooms(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("LiveShowAll");//动态网页名称
        modelAndView.addObject("rooms", liveService.getAllRooms());
        return modelAndView;
    }
    @GetMapping(path = "{id}")
    public ModelAndView createRoom(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("liveUser");//动态网页名称
        LiveTelecastBean room=liveService.getRoomById(id);
        modelAndView.addObject("roomId",room.getRoomId());
        modelAndView.addObject("userId",room.getUserId());
        return modelAndView;
    }
    @GetMapping(path = "/watcher/{id}")
    public ModelAndView InRoom(@PathVariable("id")Long id){
        System.out.println(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("liveWatcher");//动态网页名称
        LiveTelecastBean room=liveService.getRoomById(id);
        modelAndView.addObject("roomId",room.getRoomId());
        modelAndView.addObject("userId",room.getUserId());
        return modelAndView;
    }
    //POST请求
    @PostMapping
    public void addNewRooms(@RequestBody LiveTelecastBean live){
        liveService.addNewRoom(live);
    }

    //DELETE请求
    @DeleteMapping(path="{id}")
    public void deleteRoom(@PathVariable("id") Long id){
        liveService.deleteRoom(id);
    }

}
