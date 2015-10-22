/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.TagView;
import com.unito.model.repository.PropertieRepository;
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
public class TagLiked implements TagView {
    
    @Autowired PropertieRepository pr; 
    private String objId;//Object ID
    private String forUserId;//UserDetails ID

    public TagLiked() {
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
    
    

    
    @Override
    public List<Propertie> getTags() {
        return pr.getLikedPropertiesForObj(forUserId, objId);
    }
    
}
