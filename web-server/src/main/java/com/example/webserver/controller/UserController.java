package com.example.webserver.controller;

import com.example.webserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get/id")
    public Long getId(@RequestHeader("Authorization") String authorizationHeader){
        String token = extractTokenFromHeader(authorizationHeader);

        return userService.getUserIdFromToken(token);
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
