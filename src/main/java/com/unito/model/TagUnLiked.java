/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.repository.PropertyRepository;
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

    
    public TagUnLiked() {
        super();
    }
    
    @Override
    public List<Propertie> getTagsForObj() {
        return propertyRepository.getUnLikedPropertiesForObj(forUserId, objId);
    }

    @Override
    List<Propertie> getTagsForTable(String candidate) {
        return propertyRepository.getUnlikedCandidateTagsForTable(forUserId, tableId, objId, candidate);
    }
        


    
}
