package com.restaurant.controllers;

import com.restaurant.services.AuthServices;
import com.restaurant.vos.LoginVO;
import com.restaurant.vos.RegisterVO;
import com.restaurant.vos.TokenVO;
import com.restaurant.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServices services;


    @PostMapping("/register")
    public ResponseEntity<UserVO> register(@RequestBody RegisterVO data){
        UserVO user = services.register(data);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenVO> login(@RequestBody LoginVO data){
        TokenVO token = services.login(data);

        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}
