package com.dz.OnlineBooking.Controller;

import com.dz.OnlineBooking.Model.LoginUser;
import com.dz.OnlineBooking.service.MongoUserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {
    @RequestMapping("/")
    public String home(HttpSession session) {
//        if (authentication != null) {
//            LoginUser userDetails = (LoginUser) authentication.getPrincipal();
//            session.setAttribute("userId", userDetails.getId());
//        }
        return "index";

    }

    @RequestMapping("/appointment")
    public String appointment() {
        return "appointment";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }
}
