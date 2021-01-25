/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gmadero
 */
public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findUserByUsername(String username);
    
}
