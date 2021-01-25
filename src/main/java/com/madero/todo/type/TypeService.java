/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.type;

import java.util.List;

/**
 *
 * @author gmadero
 */
public interface TypeService {
    
    Type createType(Type type);
    
    List<Type> findAll();
    
    Type findById(Long id);
    
}
