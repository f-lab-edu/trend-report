package com.trendreport.user.controller;

import com.trendreport.user.dto.SignInForm;
import com.trendreport.user.dto.SignUpForm;
import com.trendreport.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/signup")
    public ResponseEntity<String> createUser(@RequestBody SignUpForm form){
        return ResponseEntity.ok(userService.signUpUser(form));
    }

    @PostMapping("/auth/signin")
    public ResponseEntity<String> logInUser(@RequestBody SignInForm form){
        return ResponseEntity.ok(userService.authenticate(form));
    }
}
