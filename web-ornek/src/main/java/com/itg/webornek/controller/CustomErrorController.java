package com.itg.webornek.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;


@Controller
public class CustomErrorController implements ErrorController {

    /*@RequestMapping("/error")
    public String handleError() {
        return "redirect:/access-denied";
    }*/
}