/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.security;

import com.madero.todo.user.User;
import com.madero.todo.user.UserService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.GenericFilterBean;

/**
 *
 * @author gmadero
 */
public class AuthenticationFilter extends GenericFilterBean {

    private final UserService userService;
    
    private final JwtManager jwtManager;

    public AuthenticationFilter(UserService userService, JwtManager jwtManager) {
        this.userService = userService;
        this.jwtManager = jwtManager;
    }

    @Override
    public void doFilter(ServletRequest requets, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String token = ((HttpServletRequest) requets).getHeader("token");
            if(token != null){
                if(jwtManager.validateJwt(token)){
                    String username = jwtManager.getUsernameFromJwt(token);
                    User user = userService.finUserByUsername(username);
                    
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        } catch (UsernameNotFoundException ex) {
            throw new UsernameNotFoundException(ex.getMessage());
        }
        
        filterChain.doFilter(requets, response);
    }

}
