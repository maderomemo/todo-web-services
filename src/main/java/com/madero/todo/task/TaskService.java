/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.task;

import java.util.List;

/**
 *
 * @author gmadero
 */
public interface TaskService {
    
    Task createTask(Task task);
    
    Task finalizeTask(Task task);
    
    List<Task> findAllTask();
    
}
