package com.example.jilijili.home;

import com.example.jilijili.user.User;
import com.example.jilijili.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("home")
    public ModelAndView home_page() {
        return new ModelAndView("index");
    }

    @GetMapping(path = "genre")
    public ModelAndView genre() {
        return new ModelAndView("genre");
    }

    @GetMapping(path = "about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }


    @GetMapping("contact")
    public ModelAndView contact(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("please log in before contacting");
        }
        User user = userService.getUserById(userId);
        if (user.getType() == 0)
            return new ModelAndView("contact");
        else
            return new ModelAndView("adminContact");
    }
}
