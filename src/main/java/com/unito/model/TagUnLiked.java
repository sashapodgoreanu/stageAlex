/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

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
public class TagUnLiked extends TagView {

    
    @Autowired PropertieRepository pr; 
    
    public TagUnLiked() {
        super();
    }
    
    @Override
    public List<Propertie> getTagsForObj() {
        return pr.getUnLikedPropertiesForObj(forUserId, objId);
    }

    @Override
    List<Propertie> getTagsForTable(String candidate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
