package com.restaurant.controllers;

import com.restaurant.services.ProductServices;
import com.restaurant.vos.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServices service;

    @PostMapping
    public ResponseEntity<ProductVO> create(@RequestBody ProductVO data){
        return new ResponseEntity<>(service.create(data), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductVO>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductVO>> findByCategory(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.findAllByCategory(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ProductVO> update(@RequestBody ProductVO data){
        return new ResponseEntity<>(service.update(data), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
