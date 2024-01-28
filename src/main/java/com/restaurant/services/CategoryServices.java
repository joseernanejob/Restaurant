package com.restaurant.services;

import com.restaurant.exceptions.AppException;
import com.restaurant.exceptions.NotFoundException;
import com.restaurant.exceptions.NotNullException;
import com.restaurant.mappers.Mapper;
import com.restaurant.models.Category;
import com.restaurant.repostories.CategoryRepository;
import com.restaurant.vos.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CategoryServices {
    private  CategoryRepository repository;

    public CategoryServices(CategoryRepository repository) {
        this.repository = repository;
    }

    public CategoryVO create(CategoryVO data){
        if (data == null) throw new NotNullException("Data category is null!");
        Boolean name = repository.existsCategoryByName(data.getName());
        if (name) throw new AppException("Category name already registered! ", HttpStatus.BAD_REQUEST);

        Category category = Mapper.parseObject(data, Category.class);
        return Mapper.parseObject(repository.save(category), CategoryVO.class);
    }

    public List<CategoryVO> findAll(){
        return Mapper.parseListObjects(repository.findAll(), CategoryVO.class);
    }

    public CategoryVO findById(Long id){
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("Category is not found!"));
        return Mapper.parseObject(category, CategoryVO.class);
    }
    public CategoryVO update(CategoryVO data){
        if (data == null) throw new NotNullException("Data category is null!");

        Category category = repository.findById(data.getId()).orElseThrow(() -> new NotFoundException("Category is not found!"));
        if(!Objects.equals(category.getName(), data.getName())){
            Boolean name = repository.existsCategoryByName(data.getName());
            if (name) throw new AppException("Category name already registered! ", HttpStatus.BAD_REQUEST);
        }

        category.CategoryUpdate(data);
        return Mapper.parseObject(repository.save(category), CategoryVO.class);
    }

    public void delete(Long id){
        Category category = repository.findById(id).orElseThrow(() -> new NotFoundException("Category is not found!"));
        repository.delete(category);
    }



}
