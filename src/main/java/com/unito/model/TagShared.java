/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.repository.PropertieRepository;
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
    PropertieRepository pr;
    @Autowired
    TagLiked tl;
    @Autowired
    TagUnLiked tul;


    public TagShared() {
        super();
    }


    @Override
    public List<Propertie> getTagsForObj() {
        List<Propertie> sharedTags = pr.getSharedPropertiesForObj(objId);
        List<Propertie> likedTags = pr.getLikedPropertiesForObj(forUserId, objId);
        List<Propertie> personalTags = pr.getPersonalProperties(forUserId, objId);
        //List<Propertie> unLikedTags = pr.getUnLikedPropertiesForObj(forUserId, objId);
        Iterator<Propertie> i = sharedTags.iterator();
        while (i.hasNext()) {
            int idShared = i.next().getId();
            for (Propertie l : likedTags) {
                if (idShared == l.getId()) {
                    i.remove();
                }
            }
        }

        i = sharedTags.iterator();
        while (i.hasNext()) {
            int idShared = i.next().getId();
            for (Propertie p : personalTags) {
                //System.out.println("personal " + p.getId());
                if (idShared == p.getId()) {
                    i.remove();
                }
            }

        }

        return sharedTags;
    }

    @Override
    List<Propertie> getTagsForTable(String candidate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
