package com.restaurant.services;

import com.restaurant.exceptions.NotFoundException;
import com.restaurant.exceptions.NotNullException;
import com.restaurant.mappers.Mapper;
import com.restaurant.models.Category;
import com.restaurant.repostories.CategoryRepository;
import com.restaurant.vos.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServices {
    private  CategoryRepository repository;

    public CategoryServices(CategoryRepository repository) {
        this.repository = repository;
    }

    public CategoryVO create(CategoryVO data){
        if (data == null) throw new NotNullException("Data category is null!");

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
        Category category = repository.findById(data.getId()).orElseThrow();
        category.CategoryUpdate(data);
        return Mapper.parseObject(repository.save(category), CategoryVO.class);
    }



}
