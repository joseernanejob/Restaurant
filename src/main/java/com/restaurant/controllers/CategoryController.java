package com.restaurant.controllers;

import com.restaurant.services.CategoryServices;
import com.restaurant.vos.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryServices service;

    @PostMapping
    public ResponseEntity<CategoryVO> create(@RequestBody CategoryVO data){
        return new ResponseEntity<>(service.create(data), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryVO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryVO> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryVO> update(@RequestBody CategoryVO data){
        return new ResponseEntity<>(service.update(data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
