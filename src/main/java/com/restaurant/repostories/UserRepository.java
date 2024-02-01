package com.restaurant.repostories;

import com.restaurant.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, String> {


    User findByLogin(String Login);
}
