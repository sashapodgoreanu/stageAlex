/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.repository.PropertyRepository;
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
    TagLiked tl;
    @Autowired
    TagPersonal tp;


    public TagShared() {
        super();
    }


    @Override
    public List<Propertie> getTagsForObj() {
        List<Propertie> sharedTags = propertyRepository.getSharedPropertiesForObj(objId);
        List<Propertie> personalTags = tp.getTagsForObj();
        List<Propertie> unlikedTags = tul.getTagsForObj();
        List<Propertie> likedTags = tl.getTagsForObj();
        //List<Propertie> unLikedTags = propertyRepository.getUnLikedPropertiesForObj(forUserId, objId);
        Iterator<Propertie> i = personalTags.iterator();
        while (i.hasNext()) {
            Propertie personal = i.next();
            Iterator<Propertie> j = sharedTags.iterator();
            while (j.hasNext()){
                Propertie shared = j.next();
                if(shared.getId() == personal.getId() || shared.getValue().equalsIgnoreCase(personal.getValue()))
                    j.remove();
            }

        }
        
        i = unlikedTags.iterator();
        while (i.hasNext()) {
            Propertie unliked = i.next();
            Iterator<Propertie> j = sharedTags.iterator();
            while (j.hasNext()){
                Propertie shared = j.next();
                if(shared.getId() == unliked.getId() )
                    shared.setLiked(0);
            }

        }
        
        i = likedTags.iterator();
        while (i.hasNext()) {
            Propertie liked = i.next();
            Iterator<Propertie> j = sharedTags.iterator();
            while (j.hasNext()){
                Propertie shared = j.next();
                if(shared.getId() == liked.getId() )
                    shared.setLiked(1);
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
