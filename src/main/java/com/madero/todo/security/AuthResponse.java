/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.madero.todo.security;

import java.io.Serializable;

/**
 *
 * @author gmadero
 */
public class AuthResponse implements Serializable{
    
    private String username;
    private String fullname;
    private String token;

    public AuthResponse(String username, String fullname, String token) {
        this.username = username;
        this.fullname = fullname;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponse{" + "username=" + username + ", fullname=" + fullname + ", token=" + token + '}';
    }
    
}
