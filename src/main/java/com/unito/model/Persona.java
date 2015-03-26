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
public class Persona {
    private int age;
    private String nome;

    public Persona() {
    }

    public Persona(int age, String nome) {
        this.age = age;
        this.nome = nome;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
