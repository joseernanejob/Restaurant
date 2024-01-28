package com.restaurant.controllers;

import com.restaurant.services.CategoryServices;
import com.restaurant.vos.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryServices service;

    @PostMapping
    public ResponseEntity<CategoryVO> create(@RequestBody CategoryVO data){
        return new ResponseEntity<>(service.create(data), HttpStatus.CREATED);
    }
}
