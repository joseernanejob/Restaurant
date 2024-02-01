package com.restaurant.vos;

import com.restaurant.models.Permission;
import lombok.*;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String id;
    private String username;
    private String fullName;
    private String email;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Permission role;
    private Boolean enabled;
}
