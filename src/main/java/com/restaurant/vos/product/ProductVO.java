package com.restaurant.vos.product;


import com.restaurant.vos.CategoryVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductVO {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private CategoryVO category;
}
