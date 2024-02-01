package com.restaurant.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

public class JwtTokenFilter  extends GenericFilterBean {

    @Autowired
    private JwtTokenProvider provider;

    private final HandlerExceptionResolver resolver;


    public JwtTokenFilter(JwtTokenProvider provider, HandlerExceptionResolver resolver) {
        this.provider = provider;
        this.resolver = resolver;
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException{
        try {
            String token = provider.resolveToken((HttpServletRequest) request);
            if(token != null && provider.validateToken(token)){
                Authentication auth = provider.getAuthentication(token);

                if( auth != null){
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            HttpServletRequest req = ((HttpServletRequest) request);
            HttpServletResponse resp = ((HttpServletResponse) response);
            resolver.resolveException(req, resp, null, ex);

        }
    }
}
