/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.user;

import com.madero.todo.role.Role;
import com.madero.todo.role.RoleService;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author gmadero
 */

@Service
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;
    
    private final RoleService roleService;
    
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.encoder = encoder;
        init();
    }
    
    public void init(){
        try{
            if(userRepository.count() > 0)
                return;
            
            Role adminRole = roleService.find("ADMIN");
            Role userRole = roleService.find("USER");
            
            User admin = new User(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);
            admin.setFullname("Guillermo Madero Sanchez");
            admin.setUsername("madero");
            admin.setPassword(encoder.encode("madero123"));
            admin.setLastModified(new Date());
            admin.setGrantedAuthorities(Arrays.asList(adminRole, userRole));
            
            userRepository.save(admin);
            userRepository.flush();
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public User create(User user) throws ResponseStatusException {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if(user.getUsername() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Must indicate username");
        }
        
        if(user.getFullname()== null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Must indicate fullname");
        }
        
        if(user.getPassword()== null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Must indicate username");
        }
        
        User duplicate = userRepository.findUserByUsername(user.getUsername()).orElse(null);
        if(duplicate != null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Username already exists");
        }
        
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        user.setGrantedAuthorities(Arrays.asList(roleService.find("USER")));
        user.setLastModified(new Date());
        user.setModifiedBy(
            userRepository.findUserByUsername(auth.getPrincipal().toString()).orElse(null));
        
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User finUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                 .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }

    @Override
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " not found"));
    }
    
}
