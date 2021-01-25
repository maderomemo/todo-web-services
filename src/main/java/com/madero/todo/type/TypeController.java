/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.type;

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
public class TypeController {
    
    @Autowired
    private TypeService typeService;
    
    @PostMapping("/types")
    public ResponseEntity createType(@RequestBody Type type){
        return ResponseEntity.status(201).body(typeService.createType(type));
    }
    
    @GetMapping("/types")
    public ResponseEntity getAllTypes(){
        return ResponseEntity.ok().body(typeService.findAll());
    }
    
}
