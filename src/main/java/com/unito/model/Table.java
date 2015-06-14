/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

/**
 *
 * @author SashaAlexandru
 */
public class Table {
    private int id;
    private String name;
    private String owner;
    private boolean opened;

    public Table() {
    }

    
    public Table(int ID, String name, String owner) {
        this.id = ID;
        this.name = name;
        this.owner = owner;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    @Override
    public String toString() {
        return "Table{" + "ID=" + id + ", name=" + name + ", owner=" + owner + ", opened=" + opened + '}';
    }
    
    
    
    
}
