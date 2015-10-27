/*
 * To change this license header, choose License Headers in Project Propertie.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model;

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

}
