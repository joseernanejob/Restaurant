package com.restaurant.vos.product;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestProductVO {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private Long categoryId;
}
