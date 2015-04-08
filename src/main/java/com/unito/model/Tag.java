/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

/**
 *
 * @author Alexandru Podgoreanu
 */
public class Tag {
    private String value;

    public Tag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Tag{" + "Value=" + value + '}';
    }
    
    
}
