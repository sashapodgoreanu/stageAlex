/*
 * To change this license header, choose License Headers in Project Propertie.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

/**
 *
 * @author Alexandru Podgoreanu
 */
public class Propertie {
    private int id;
    private String value;
    private boolean in_r_bin;
    private boolean shared;
    private String ownerId;

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public boolean isIn_r_bin() {
        return in_r_bin;
    }

    public void setIn_r_bin(boolean in_r_bin) {
        this.in_r_bin = in_r_bin;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public Propertie() {
    }

    @Override
    public String toString() {
        return "Properties{" + "id=" + id + ", value=" + value + ", in_r_bin=" + in_r_bin + ", shared=" + shared + '}';
    }
    
    
    
}
