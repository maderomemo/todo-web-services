/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.role;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gmadero
 */

@Service
public class RoleServiceImpl implements RoleService{
    
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        init();
    }
    
    private void init(){
        try{
            if(roleRepository.count() > 0)
                return;
            
            Role adminRole = new Role("ADMIN");
            roleRepository.save(adminRole);
            
            Role userRole = new Role("USER");
            roleRepository.save(userRole);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Role create(String roleName) {
        return roleRepository.save(new Role(roleName));
    }

    @Override
    public Role find(String roleName) {
        return roleRepository.findRoleByRoleName(roleName);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    
}
