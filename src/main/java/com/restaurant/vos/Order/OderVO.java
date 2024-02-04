package com.restaurant.vos.Order;

import com.restaurant.models.OrderStatus;
import com.restaurant.models.Product;
import com.restaurant.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OderVO {

    private String id;

    private List<Product> product;

    private String client;

    private Float price;

    private Float discount;

    private Float total;

    private Date createAt;

    private Date finishAt;

    private OrderStatus status = OrderStatus.valueOf("OPEN");
}
