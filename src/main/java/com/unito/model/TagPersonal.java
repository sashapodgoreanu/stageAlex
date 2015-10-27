/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.repository.PropertieRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Component
@Scope(value = "request",
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TagPersonal extends TagView {

    @Autowired
    TagLiked tagLiked;
    @Autowired
    TagUnLiked tagUnliked;

    public TagPersonal() {
        super();
    }

    @Override
    public List<Propertie> getTagsForObj() {
        List<Propertie> personal = propertyRepository.getPersonalProperties(getForUserId(), getObjId());
        List<Propertie> liked = tagLiked.getTagsForObj();
        //add to personal tags all liked tags
        for (Propertie l : liked) {
            boolean trovato = false;
            for (Propertie p : personal) {
                if (p.getId() == l.getId()) {
                    trovato = true;
                }
            }
            if (!trovato) {
                personal.add(l);
            }
        }
        return personal;
    }

    @Override
    public List<Propertie> getTagsForTable(String candidate) {
        List<Propertie> liked = tagLiked.getTagsForTable(candidate);
        List<Propertie> personal = propertyRepository.getPersonalCandidateTagsForTable(getForUserId(), getTableId(), getObjId(), candidate);

        //TO DO delete repeated values. 
        for (Propertie l : liked) {
            boolean trovato = false;
            for (Propertie p : personal) {
                if (p.getId() == l.getId()) {
                    trovato = true;
                }
            }
            if (!trovato) {
                personal.add(l);
            }
        }
        return personal;
    }

}
