/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.google.gson.Gson;
import com.unito.model.Propertie;
import com.unito.model.TableManager;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alexandru Podgoreanu
 */
@RestController
@RequestMapping(value = "table-manager")
public class ControllerTable {

    Gson gson = new Gson();
    @Autowired
    TableManager tableManager;

    @RequestMapping(value = "change/{place}/{idObject}")
    public String getTags(@PathVariable String place, @PathVariable String idObject) {
        LOG.info("Received idObject:" + idObject+" place:"+place);
        return gson.toJson(tableManager.toPlace(place, idObject) );

    }
    
    @RequestMapping(value = "get-obj-candidates")
    public String getObjCandidates() {
        LOG.info("Received candidate string:");
        Propertie a = new Propertie();
        a.setId(2);
        a.setValue("AAAA");
        
        return gson.toJson( a );

    }
    
    private static final Logger LOG = Logger.getLogger(ControllerTable.class.getName());

}
