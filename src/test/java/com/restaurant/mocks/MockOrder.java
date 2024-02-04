package com.restaurant.mocks;

import com.restaurant.models.Category;
import com.restaurant.models.Order;
import com.restaurant.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MockOrder {

    public Order mockEntity(Integer number){
        List<Product> listProduct = listProduct();

        Order order = new Order();
        order.setId(STR."ID-\{number}");
        order.setClient(STR."Usuário-\{number}");

        return order;
    }




    private List<Product> listProduct(){
        List<Product> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Product product = newProduct(i);
            list.add(product);
        }

        return list;
    }

    private Product newProduct(Integer number){
        Category category = new Category(
                (long) (((number % 2) == 0) ? 2 : 1),
                ((number % 2) == 0) ? "Sobremesa" : "Bebida"
        );
        return new Product(
                number.longValue(),
                STR."Produto \{number}",
                STR."Descrição produto \{number}",
                category
        );
    }

}
