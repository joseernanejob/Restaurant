package com.restaurant.models;

import com.restaurant.mappers.Mapper;
import com.restaurant.vos.RequestProductVO;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 500, nullable = false)
    private String description;

    @ManyToOne()
    private Category category;


    public void updateProduct(RequestProductVO data) {
        this.name = data.getName();
        this.description = data.getDescription();
    }
}
