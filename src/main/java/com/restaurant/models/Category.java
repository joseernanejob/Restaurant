package com.restaurant.models;


import com.restaurant.vos.CategoryVO;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;


    public Category(CategoryVO data) {
        this.id = data.getId();
        this.name = data.getName();
    }

    public void CategoryUpdate(CategoryVO data){
        this.name = data.getName();
    }

}
