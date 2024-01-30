package com.restaurant.mocks;

import com.restaurant.models.Category;
import com.restaurant.models.Product;
import com.restaurant.vos.CategoryVO;
import com.restaurant.vos.RequestProductVO;
import com.restaurant.vos.ProductVO;

import java.util.ArrayList;
import java.util.List;

public class MockProduct {

    public Product mockEntity(){
        return mockEntity(0);
    }

    public ProductVO mockVO(){
        return mockVO(0);
    }

    public Product mockEntity(Integer number){
        Category category = new Category(
                Long.valueOf(((number % 2) == 0)? 2 : 1),
                ((number % 2) == 0) ? "Sobremesa" : "Bebida"
        );
        return new Product(
                number.longValue(),
                STR."Produto \{number}",
                STR."Descrição produto \{number}",
                category
        );
    }

    public List<Product> mockEntityList(){

        List<Product> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            list.add(mockEntity(i));
        }
        return list;
    }

    public ProductVO mockVO(Integer number){
        CategoryVO category = new CategoryVO(
                Long.valueOf(((number % 2) == 0)? 2 : 1),
                ((number % 2) == 0) ? "Sobremesa" : "Bebida"
        );

        return new ProductVO(
                number.longValue(),
                STR."Produto\{number}",
                STR."Descrição produto \{number}",
                category
        );
    }

    public RequestProductVO createMockVO(Integer number){
        CategoryVO category = new CategoryVO(
                Long.valueOf(((number % 2) == 0)? 2 : 1),
                ((number % 2) == 0) ? "Sobremesa" : "Bebida"
        );

        return new RequestProductVO(
                number.longValue(),
                STR."Produto\{number}",
                STR."Descrição produto \{number}",
                category.getId()
        );
    }

    public List<RequestProductVO> mockListVO(){
        List<RequestProductVO> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            list.add(createMockVO(i));
        }

        return list;
    }


}
