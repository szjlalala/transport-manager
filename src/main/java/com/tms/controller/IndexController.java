package com.tms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by song on 2017/1110.
 */
@Controller
public class IndexController {
    @GetMapping("/login")
    public String login() {
        return "index";
    }

    @GetMapping("/user/**")
    public String user() {
        return "index";
    }

    @GetMapping("/order/**")
    public String order() {
        return "index";
    }

    @GetMapping("/delivery/**")
    public String deliver() {
        return "index";
    }

    @GetMapping("/driver/**")
    public String driver() {
        return "index";
    }

    @GetMapping("/dashboard/**")
    public String dashboard() {
        return "index";
    }

    @GetMapping("/vehicle/**")
    public String vehicle() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "index";
    }


}
