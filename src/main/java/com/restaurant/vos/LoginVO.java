package com.restaurant.vos;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private String username;
    private String password;
}
