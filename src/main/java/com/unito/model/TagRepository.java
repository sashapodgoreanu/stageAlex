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

    private ArrayList<Tag> child;

    Tag pie = new Tag(2, "Piemonte");
    Tag lomb = new Tag(9, "Lombardia");
    Tag ver = new Tag(3, "Verceli");
    Tag ales = new Tag(6, "Alessandria");
    Tag viaN = new Tag(4, "Via Napoli");
    Tag viaM = new Tag(5, "Via Moncalieri");
    Tag viaVen = new Tag(7, "Via Venezia");
    Tag ciaVer = new Tag(8, "Via Verona");

    Tag berg = new Tag(11, "Bergamo");
    Tag mil = new Tag(14, "Milano");

    Tag vm = new Tag(12, "Via Milano");
    Tag vb = new Tag(13, "Via Torino");
    Tag vb2 = new Tag(15, "Via Bergamo");
    Tag viato = new Tag(16, "Via Torino");

    Tag cast = new Tag(18, "Castiglia de la Mancha");
    Tag mad = new Tag(19, "Madrid");
    Tag car1 = new Tag(21, "Caretera 1");
    Tag car2 = new Tag(22, "Caretera Toledo");
    Tag tol = new Tag(23, "Toledo");
    Tag carv = new Tag(24, "Caretera Valencia");
    Tag carm = new Tag(25, "Caretera Madrid");

    Tag gal = new Tag(26, "Galizia");
    Tag sdc = new Tag(27, "Santiago De Compostella");
    Tag carv1 = new Tag(28, "Caretera Valencia");
    Tag carm2 = new Tag(29, "Caretera Madrid");
    Tag lugo = new Tag(31, "Lugo");
    Tag carv2 = new Tag(32, "Caretera Valencia");
    Tag carm1 = new Tag(33, "Caretera Madrid");
    Tag ita = new Tag(1, "Italia");
    Tag spa = new Tag(17, "Spagna");

    public TagRepository() {
        child = new ArrayList<>();
    }

    public ArrayList<Tag> getChilds(Tag tag) {
        child.clear();
        if (tag.getId() == 1) {
            child.add(pie);
            child.add(lomb);
        } else if (tag.getId() == 17) {
            child.add(cast);
            child.add(mad);

        } else if (tag.getId() == 2) {
            child.add(ver);
            child.add(ales);

        } else if (tag.getId() == 9) {
            child.add(berg);
            child.add(mil);

        } else if (tag.getId() == 3) {
            child.add(viaN);
            child.add(viaM);

        } else if (tag.getId() == 6) {
            child.add(viaVen);
            child.add(ciaVer);

        } else if (tag.getId() == 11) {
            child.add(vm);
            child.add(vb);

        } else if (tag.getId() == 14) {
            child.add(vb2);
            child.add(viato);

        } else if (tag.getId() == 18) {
            child.add(mad);
            child.add(tol);

        } else if (tag.getId() == 19) {
            child.add(car1);
            child.add(car2);

        } else if (tag.getId() == 23) {
            child.add(carv);
            child.add(carm);

        } else if (tag.getId() == 26) {
            child.add(sdc);
            child.add(lugo);

        } else if (tag.getId() == 27) {
            child.add(carv1);
            child.add(carm2);

        } else if (tag.getId() == 31) {
            child.add(carv2);
            child.add(carv2);

        } else if (tag.getId() == 0) {
            child.add(ita);
            child.add(spa);
        }
        return child;
    }

}
