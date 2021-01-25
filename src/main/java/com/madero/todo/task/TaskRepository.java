/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.task;

import com.madero.todo.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gmadero
 */
public interface TaskRepository extends JpaRepository<Task, Long>{
    
    List<Task> findAllByOwner(User user);
            
}
