package com.restaurant.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany
    private List<Product> product;

    @Column(length = 60, nullable = false)
    private String client;

    @Column(nullable = false)
    private Float price;

    @Column
    private Float discount;

    @Column
    private Float total;

    @Column(nullable = false)
    private Date createAt;

    @Column
    private Date finishAt;

    @Column(nullable = false)
    private OrderStatus status = OrderStatus.valueOf("OPEN");


}
