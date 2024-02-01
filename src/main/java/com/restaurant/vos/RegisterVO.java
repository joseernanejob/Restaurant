package com.restaurant.vos;


import com.restaurant.models.Permission;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class RegisterVO {

    private String username;

    private String fullName;

    private String email;

    private String password;

    private String role;
}
