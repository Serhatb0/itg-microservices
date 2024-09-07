package com.itg.webornek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginLogoutDenied_Controller {

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "security/access-denied";
    }

    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "security/login";
    }

    @GetMapping("/")
    public String showHome() {
        return "redirect:/api/feign-client/home";
    }
}
