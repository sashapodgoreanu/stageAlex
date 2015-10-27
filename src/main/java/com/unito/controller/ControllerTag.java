/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.google.gson.Gson;
import com.unito.model.Propertie;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alexandru Podgoreanu
 */
@RestController
@RequestMapping(value = "tag-manager")
public class ControllerTag {

    Gson gson = new Gson();

    @RequestMapping(value = "/get-tags/{tagType}/{objectId}")
    public String getTags(@PathVariable String tagType, @PathVariable String objectId) {
        LOG.info("Received" + tagType + " " + objectId);
        List<Propertie> tags = new ArrayList<>();
        Propertie a1 = new Propertie(); 
        a1.setId(1);
        a1.setValue("LALAL");
        Propertie a2 = new Propertie(); 
        a2.setId(2);
        a2.setValue("LALAL2222222");
        Propertie a3 = new Propertie(); 
        a3.setId(3);
        a3.setValue("LALAL333333");
        tags.add(a1);
        tags.add(a2);
        tags.add(a3);
        return gson.toJson(tags);

    }

    private static final Logger LOG = Logger.getLogger(ControllerTag.class.getName());

}
