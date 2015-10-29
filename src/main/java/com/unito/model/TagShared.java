/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.repository.PropertyRepository;
import java.util.Iterator;
import java.util.List;
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
public class TagShared extends TagView {

    @Autowired
    TagUnLiked tul;
    @Autowired
    TagPersonal tp;


    public TagShared() {
        super();
    }


    @Override
    public List<Propertie> getTagsForObj() {
        List<Propertie> sharedTags = propertyRepository.getSharedPropertiesForObj(objId);
        List<Propertie> personalTags = tp.getTagsForObj();
        //List<Propertie> unLikedTags = propertyRepository.getUnLikedPropertiesForObj(forUserId, objId);
        Iterator<Propertie> i = personalTags.iterator();
        while (i.hasNext()) {
            Propertie shared = i.next();
            Iterator<Propertie> j = sharedTags.iterator();
            while (j.hasNext()){
                Propertie personal = j.next();
                if(shared.getId() == personal.getId() || shared.getValue().equalsIgnoreCase(personal.getValue()))
                    j.remove();
            }

        }

        return sharedTags;
    }

    @Override
    List<Propertie> getTagsForTable(String candidate) {
        List<Propertie> sharedTags = propertyRepository.getSharedCandidateTagsForTable(getTableId(), getObjId(), candidate);
        List<Propertie> unLikedTags = tul.getTagsForTable(candidate);
        
        Iterator<Propertie> i = sharedTags.iterator();
        while (i.hasNext()) {
            int idShared = i.next().getId();
            for (Propertie l : unLikedTags) {
                if (idShared == l.getId()) {
                    i.remove();
                }
            }
        }
        
        return sharedTags;
    }


}
