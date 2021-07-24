package com.lerkin.titllist.rest_controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @GetMapping(Navigation.SLASH)
    public String index() {
        return "index";
    }

    @GetMapping(Navigation.START)
    public String start() {
        return "start_page";
    }

    @GetMapping(Navigation.MAIN)
    public String main() {
        return "main_page";
    }

    @GetMapping(Navigation.REGISTRATION_PAGE)
    public String registration(){
        return "registration_page";
    }

    @GetMapping(Navigation.BLOCKED)
    public String blocked(){
        return "blocked_page";
    }
}
