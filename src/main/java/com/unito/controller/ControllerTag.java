/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.google.gson.Gson;
import com.unito.model.Propertie;
import com.unito.model.TagManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    TagManager tagManager;

    @RequestMapping(value = "get-tags/{tagType}/{candidate}")
    public String getTags(@PathVariable String tagType, @PathVariable String candidate) {
        LOG.info("Received" + tagType + " " + candidate);
        List<Propertie> retVal = tagManager.getCandidatesTags(tagType,candidate);
        return gson.toJson(retVal );

    }
    @RequestMapping(value = "add-tag/{tagType}/{tag}")
    public String addTag(@PathVariable String tagType, @PathVariable String tag) {
        LOG.info("Received" + tagType + " " + tag);
        //List<Propertie> retVal = tagManager.getCandidatesTags(tagType,tag);
        return gson.toJson(null);

    }

    private static final Logger LOG = Logger.getLogger(ControllerTag.class.getName());

}
