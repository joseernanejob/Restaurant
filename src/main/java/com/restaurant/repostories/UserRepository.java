package com.restaurant.repostories;

import com.restaurant.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {


    User findByUsername(String Login);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
