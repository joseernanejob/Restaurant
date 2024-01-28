package com.restaurant.mocks;

import com.restaurant.models.Category;
import com.restaurant.vos.CategoryVO;

import java.util.ArrayList;
import java.util.List;

public class MockCategory {

    public Category mockEntity(){
        return mockEntity(0);
    }

    public CategoryVO mockVO(){
        return mockVO(0);
    }

    public Category mockEntity(Integer number){
        Category category = new Category();
        category.setName("Category " + number);
        category.setId(number.longValue());

        return category;
    }

    public List<Category> mockEntityList(){
        List<Category> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            list.add(mockEntity(i));
        }

        return list;
    }

    public CategoryVO mockVO(Integer number){
        CategoryVO category = new CategoryVO();
        category.setName("Category " + number);
        category.setId(number.longValue());

        return category;
    }

    public List<CategoryVO> mockListVO(){
        List<CategoryVO> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            list.add(mockVO(i));
        }

        return list;
    }
}
