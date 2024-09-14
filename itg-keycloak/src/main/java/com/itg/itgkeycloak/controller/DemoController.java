package com.itg.itgkeycloak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> adminDetails(){
        return ResponseEntity.ok("Hello Admin");
    }
    @GetMapping("/user")
    @PreAuthorize("hasRole('user')")
    public ResponseEntity<String> userDetails(){
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/hello-everyone")
    @PreAuthorize("hasAnyRole('user', 'admin')")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello Everyone!");
    }
}