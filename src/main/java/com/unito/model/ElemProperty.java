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
public class ElemProperty {
    private int id;
    private String value;
    private boolean privato;
    private boolean shared;

    public ElemProperty(int id, String value, boolean privato) {
        this.id = id;
        this.value = value;
        this.privato = privato;
    }

    public ElemProperty() {
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

    public boolean isPrivato() {
        return privato;
    }

    public void setPrivato(boolean privato) {
        this.privato = privato;
    }

    @Override
    public String toString() {
        return "ElemProperty{" + "id=" + id + ", value=" + value + ", privato=" + privato + '}';
    }
    
    
}
