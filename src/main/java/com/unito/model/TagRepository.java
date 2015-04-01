/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Component
public class TagRepository {

    private ArrayList<String> child;


    public TagRepository() {
        child = new ArrayList<>();
        child.add("Italia");
        child.add("Spagna");
    }

    public ArrayList<String> getChilds(String tag) {
        child.clear();int a= 1;
        int b =2;
        switch (tag.toLowerCase()) {
            case "italia" :  child.add("Lombardia"); child.add("Piemonte");
                     break;
            case "spagna" :  child.add("Castiglia de la Mancha"); child.add("Toledo");
                     break;
            case "lombardia" :  child.add("Milano"); child.add("Bergamo");
                     break;
            case "piemonte" :  child.add("Verceli"); child.add("Torino");
                     break;
            case "milano" :  child.add("Via Torino"); child.add("Corso Romania");
                     break;
            case "bergamo" :  child.add("Via Torino"); child.add("Corso Romania");
                     break;
            case "verceli" :  child.add("Via Torino"); child.add("Corso Romania");
                     break;
            case "torino" :  child.add("Via Torino"); child.add("Corso Romania");
                     break;
            case "toledo" :  child.add("Tolledo"); child.add("VillaFranca");
                     break;
            case "tolledo" :  child.add("carretera1"); child.add("carretera2");
                     break;
            case "Spagna" :  child.add("Toledo"); child.add("Madrid");
                     break;
            case "Spagna" :  child.add("Toledo"); child.add("Madrid");
                     break;
            case "Spagna" :  child.add("Toledo"); child.add("Madrid");
                     break;
            case "Spagna" :  child.add("Toledo"); child.add("Madrid");
                     break;
            case "Spagna" :  child.add("Toledo"); child.add("Madrid");
                     break;
            case "Spagna" :  child.add("Toledo"); child.add("Madrid");
                     break;
            case "Spagna" :  child.add("Toledo"); child.add("Madrid");
                     break;
            case "Spagna" :  child.add("Toledo"); child.add("Madrid");
                     break;
            
        }
        
        return child;
    }

}
