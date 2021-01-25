/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gmadero
 */

@RestController
@RequestMapping("TodoRestful/management")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @PostMapping("/tasks")
    public ResponseEntity creaetTask(@RequestBody Task task){
        return ResponseEntity.status(201).body(taskService.createTask(task));
    }
    
    @PostMapping("/tasks/finalize")
    public ResponseEntity finalizeTask(@RequestBody Task task){
        return ResponseEntity.ok().body(taskService.finalizeTask(task));
    }
    
    @GetMapping("/tasks")
    public ResponseEntity getAllTasks(){
        return ResponseEntity.ok().body(taskService.findAllTask());
    }
   
}
