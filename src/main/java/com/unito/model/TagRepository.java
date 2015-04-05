/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Component
public class TagRepository {

    private ArrayList<String> padre;

    public TagRepository() {
        padre = new ArrayList<>();
        padre.add("Italia");
        padre.add("Lombardia");
        padre.add("Piemonte");
        padre.add("Castiglia de la Mancha");
        padre.add("Toledo");
        padre.add("Milano");
        padre.add("Bergamo");
        padre.add("Verceli");
        padre.add("Torino");
        padre.add("Corso Romania");
        padre.add("Via Torino");
        padre.add("VillaFranca");
        padre.add("carretera1");
        padre.add("carretera2");
        padre.add("Spagna");
        padre.add("Francia");
        padre.add("Leon");
        padre.add("Aosta");
        padre.add("Bergamo");
        padre.add("Mazze");
        padre.add("Moncalieri");
        padre.add("Dipatimento Unito");
        padre.add("Firenze");
        padre.add("Vila Nova");
        padre.add("Grosseto");
        padre.add("Vila Franca");
        padre.add("Romania");
        padre.add("Ungheria");
        padre.add("Germania");
        padre.add("Inghiltera");
        padre.add("Holland");
        padre.add("Luxemburg");
        padre.add("Bogota");
        padre.add("Medellin");
        padre.add("Rio Grande");
        padre.add("Mexico");
        padre.add("America");
        padre.add("Argentina");
        padre.add("Falavela");
        padre.add("Brazil");
    }

    public ArrayList<Tag> getChilds(String tag) {
        ArrayList<Tag> retVal = new ArrayList<>();
        for (String i: padre) {
            if(i.toLowerCase().contains(tag.toLowerCase()))
                retVal.add(new Tag(i));
        }
        return retVal;
    }

    public ArrayList<String> getPadre() {
        return padre;
    }

    public void setPadre(ArrayList<String> padre) {
        this.padre = padre;
    }
    
    

}
