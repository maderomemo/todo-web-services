/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.type;

import com.madero.todo.user.User;
import com.madero.todo.user.UserService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author gmadero
 */

@Service
public class TypeServiceImpl implements TypeService{
    
    private final TypeRepository typeRepository;
    
    private final UserService userService;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository, UserService userService) {
        this.typeRepository = typeRepository;
        this.userService = userService;
    }

    @Override
    public Type createType(Type type) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if(type.getName() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Must indicate a type name");
        }
        
        type.setCreationDate(new Date());
        type.setOwner(userService.finUserByUsername(auth.getPrincipal().toString()));
        
        return typeRepository.save(type);
    }

    @Override
    public List<Type> findAll() {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.finUserByUsername(auth.getPrincipal().toString());
        return typeRepository.findAllByOwner(user);
    }

    @Override
    public Type findById(Long id) {
        return typeRepository.findById(id).orElse(null);
    }
    
}
