package com.restaurant.services;

import com.restaurant.exceptions.AppException;
import com.restaurant.exceptions.InvalidRoleException;
import com.restaurant.exceptions.NotNullException;
import com.restaurant.mappers.Mapper;
import com.restaurant.models.Permission;
import com.restaurant.models.User;
import com.restaurant.repostories.UserRepository;
import com.restaurant.security.jwt.JwtTokenProvider;
import com.restaurant.vos.LoginVO;
import com.restaurant.vos.RegisterVO;
import com.restaurant.vos.TokenVO;
import com.restaurant.vos.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;


    public UserVO register(RegisterVO data){
        if (data == null) throw  new NotNullException("Data is null!");

        boolean login = repository.existsByUsername(data.getUsername());
        if (login) throw new AppException("Username already registered!", HttpStatus.BAD_REQUEST);

        boolean email = repository.existsByEmail(data.getEmail());
        if (email) throw new AppException("Email already registered!", HttpStatus.BAD_REQUEST);

        List<String> listRoles = new ArrayList<>();
        listRoles.add("ADMIN");
        listRoles.add("USER");


        if(!listRoles.contains(data.getRole().toUpperCase()))
            throw new InvalidRoleException("Invalid Role!");

        Permission role = Permission.valueOf(data.getRole().toUpperCase());
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());

        User user = Mapper.parseObject(data, User.class);
        user.setPassword(encryptedPassword);
        user.setRole(role);
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);

        return Mapper.parseObject(repository.save(user), UserVO.class);

    }

    public TokenVO login(LoginVO data){
        if (data == null) throw  new NotNullException("Data is null!");

        try{
            String username = data.getUsername();
            String password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            User user = repository.findByUsername(username);
            TokenVO tokenResponse = new TokenVO();
            if (user != null){
                tokenResponse = tokenProvider.createAccessToken(username, user.getRole().toString());
            } else {
                throw new UsernameNotFoundException("Username/Password invalid.");
            }
            return tokenResponse;
        } catch (Exception ex){
            throw new BadCredentialsException(ex.toString());
        }
    }
}
