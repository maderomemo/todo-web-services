/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.user;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author gmadero
 */
public interface UserService extends UserDetailsService{
    
    User create(User user) throws ResponseStatusException;

    User finUserByUsername(String username) throws UsernameNotFoundException;

    List<User> findAll() throws Exception;
    
}
