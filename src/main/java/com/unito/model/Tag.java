/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Alexandru Podgoreanu
 */
public class Tag implements Comparable{
    
    @Size(min = 0, message ="Tag id must be greater then 0")
    private int id;
    @NotNull (message = "Tag value must not be null")
    @Size(min=2, max=240)
    private String value;

    public Tag(int id, String value) {
        this.id = id;
        this.value = value;
    }

    @Override
    public int compareTo(Object o) {
        Tag mioTag;
        if (o instanceof Tag){
            mioTag = (Tag)o;
        } else throw new IllegalArgumentException();
        if (this.id == mioTag.id)
            return 0;
        else return -1;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Tag{" + "id=" + id + ", value=" + value + '}';
    }
    
    
    
    
                
}
