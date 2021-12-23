package com.example.jilijili.controller;

import com.example.jilijili.entity.Comment;
import com.example.jilijili.service.SearchService;
import com.example.jilijili.entity.User;
import com.example.jilijili.entity.Video;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Resource
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public String SearchAll(
            @RequestParam("search") String search,
            Model model,
            HttpServletRequest request) {
        if (request.getSession().getAttribute("userId") == null) {
            throw new IllegalStateException("please log in before updating");
        }
        System.out.println(search);
        //设置查询优先级，先查看关键字
        //视频优先放出来
        //然后是用户
        //最后是评论
        String[] sub=new String[6];
        sub[0]="评论";
        sub[1]="视频";
        sub[2]="用户";
        sub[3]="comment";
        sub[4]="video";
        sub[5]="user";
        int[] arr = {0,0,0,0,0,0};
        int count = 0;
        for(int i=0;i<6;i++){
            int co=0;
            Pattern p = Pattern.compile(sub[i]);
            Matcher m = p.matcher(search);
            while (m.find()) {
                co++;
            }
            arr[i]=co;
        }
        List<Comment> temp1= searchService.getComments(search);
        List<User> temp2= searchService.getUsers(search);
        List<Video> temp3= searchService.getVideos(search);
        model.addAttribute("comments", temp1);
        model.addAttribute("videos", temp3);
        model.addAttribute("users", temp2);


        //返回的元素的名称不一样排版不一样
        //都返回list，根据个数排版
        return "searchResult";
    }

}
