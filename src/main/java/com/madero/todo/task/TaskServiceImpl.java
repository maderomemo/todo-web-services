/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.task;

import com.madero.todo.type.TypeService;
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
public class TaskServiceImpl implements TaskService{
    
    private final TaskRepository taskRepository;
    
    private final TypeService typeService;
    
    private final UserService userService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TypeService typeService, UserService userService) {
        this.taskRepository = taskRepository;
        this.typeService = typeService;
        this.userService = userService;
    }

    @Override
    public Task createTask(Task task) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if(task.getName() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Must indicate name");
        }
        
        if(task.getDescription()== null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Must indicate description");
        }
        
        if(task.getExpirationDate()== null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Must indicate expiration date");
        }
        
        if(task.getType().getId() == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Must indicate a type id");
        }
        
        Date now = new Date();
        if(now.after(task.getExpirationDate())){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Expiration date must be after than now");
        }
        
        task.setCreationDate(now);
        task.setStatus(false);
        task.setType(typeService.findById(task.getType().getId()));
        task.setOwner(userService.finUserByUsername(auth.getPrincipal().toString()));
        
        return taskRepository.save(task);
    }

    @Override
    public Task finalizeTask(Task task) {
        
        task = taskRepository.findById(task.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task doest not exists"));
        
        task.setStatus(true);
        
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findAllTask() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.finUserByUsername(auth.getPrincipal().toString());
        return taskRepository.findAllByOwner(user);
    }
    
}
