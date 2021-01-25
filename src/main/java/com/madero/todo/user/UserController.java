/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.user;

import com.madero.todo.security.AuthResponse;
import com.madero.todo.security.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gmadero
 */

@RestController
@RequestMapping("TodoRestful/security")
public class UserController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtManager jwtManager;
    
    @PostMapping("/authentication")
    public ResponseEntity authenticate(@RequestHeader("username") String username, 
            @RequestHeader("password") String password){
        try{
            
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            
            User user = userService.finUserByUsername(username);
            
            String token = jwtManager.createJwt(user);
            
            return ResponseEntity.ok().body(new AuthResponse(user.getUsername(), user.getFullname(), token));
            
        }catch(AuthenticationException ex){
            throw new BadCredentialsException("Incorrect username or password", ex);
        }
    }
    
    @PostMapping("users")
    public ResponseEntity createUser(@RequestBody User user){
        return ResponseEntity.status(201).body(userService.create(user));
    }
    
}
