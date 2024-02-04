package com.restaurant.vos.Order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOderVO {

    private List<ProductOrderVO> product;

    private String client;

    private String ticketDiscount;
}
