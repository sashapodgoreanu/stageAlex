/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandru Podgoreanu
 */

@Component
public class Debug {
    private boolean value;

    public Debug() {
        this.value = true;
    }
    
    public boolean getValue(){
        return value;
    }
}
