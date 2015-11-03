/*
 * To change this license header, choose License Headers in Project Propertie.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

import com.unito.model.repository.PropertyRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session",
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TagManager implements Serializable {

    @Autowired
    private TagPersonal tagPersonal;
    @Autowired
    private TagShared tagShared;
    @Autowired
    private TableManager tableManager;
    @Autowired
    PropertyRepository propertyRepository;

    public TagManager() {
    }

    public List<Propertie> getCandidatesTags(String type, String candidate) {
        List<Propertie> retVal = new ArrayList<>();
        switch (type) {
            case "personal":
                retVal = tagPersonal.getTagsForTable(candidate);
                break;
            case "shared":
                retVal = tagShared.getTagsForTable(candidate);
                break;
        }
        return retVal;
    }

    public boolean addTag(String tagType, String tag) {
        if (propertyRepository.findTag(tableManager.getLastObjectOpened(), tag).isEmpty()) {
            return propertyRepository.addTag(tableManager.getUserdetails().getId(),
                    tableManager.getLastObjectOpened(),tag, ("shared".equals(tagType)) ? 1 : 0);
        } else return false;
    }

    public boolean doAction(String action, int idTag) {
        boolean retVal = false;
        switch (action) {
            case "restore":
                retVal = propertyRepository.doRestore(idTag);
                break;
            case "delete":
                retVal = propertyRepository.doDelete(idTag);
                break;
            case "share":
                retVal = propertyRepository.doShare(idTag);
                break;
            case "like":
                retVal = propertyRepository.doLike(idTag, tableManager.getUserdetails().getId());
                break;
            case "unlike":
                retVal = propertyRepository.doUnLike(idTag, tableManager.getUserdetails().getId());
                break;
            default:
                retVal = propertyRepository.doDefault(idTag, tableManager.getUserdetails().getId());
                break;
            
        }
        return retVal;
    }

}
