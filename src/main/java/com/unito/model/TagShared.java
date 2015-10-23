/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.TagView;
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
public class TagShared implements TagView {

    @Autowired
    PropertieRepository pr;
    @Autowired
    TagLiked tl;
    @Autowired
    TagUnLiked tul;

    private String objId;//Object ID
    private String forUserId;//UserDetails ID
    private List<String> userOnTableIds;//UserDetails ID

    public TagShared() {
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getForUserId() {
        return forUserId;
    }

    public void setForUserId(String forUserId) {
        this.forUserId = forUserId;
    }

    public List<String> getUserOnTableIds() {
        return userOnTableIds;
    }

    public void setUserOnTableIds(List<String> userOnTableIds) {
        this.userOnTableIds = userOnTableIds;
    }

    @Override
    public List<Propertie> getTags() {
        List<Propertie> sharedTags = pr.getSharedPropertiesForObj(objId);
        List<Propertie> sharedTags2 = pr.getSharedPropertiesForObj(objId);
        List<Propertie> likedTags = pr.getLikedPropertiesForObj(forUserId, objId);
        List<Propertie> personalTags = pr.getPersonalProperties(forUserId, objId);
        //List<Propertie> unLikedTags = pr.getUnLikedPropertiesForObj(forUserId, objId);
        Iterator<Propertie> i = sharedTags.iterator();
        while (i.hasNext()) {
            for (Propertie l : likedTags) {
                if (i.next().getId() == l.getId()) {
                    i.remove();
                }
            }
        }

        Iterator<Propertie> j = sharedTags2.iterator();
        while (j.hasNext()) {
            System.out.println("shared " + j.next().getId());
            for (Propertie p : personalTags) {
                System.out.println("personal " + p.getId());
                if (j.next().getId() == p.getId()) {
                    j.remove();
                }
            }

        }

        return sharedTags;
    }

}
