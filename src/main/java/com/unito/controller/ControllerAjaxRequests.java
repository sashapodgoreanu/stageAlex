/*
 * To change this license header, choose License Headers in Project Propertie.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unito.UserDetailsRepository;
import com.unito.model.Propertie;
import com.unito.model.SemTElem;
import com.unito.model.Table;
import com.unito.model.Tag;
import com.unito.model.TagRepository;
import com.unito.model.UserSession;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alexandru Podgoreanu
 */
// è un Controller + ResponseBody
@RestController

/*@ResponseBody
 @Controller*/
public class ControllerAjaxRequests {

    private static final Logger LOG = Logger.getLogger(ControllerAjaxRequests.class.getName());

    private static final String VALIDATE_HTTPS = "https://www.googleapis.com/oauth2/v1/tokeninfo?id_token=";
    @EJB
    TagRepository tagRep;
    @Autowired
    private UserSession userSession;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @RequestMapping(value = "tag", method = RequestMethod.POST)
    public String getShopInJSON(@RequestBody String data) {

        Gson gson = new Gson();
        Tag myTag = gson.fromJson(data, Tag.class);

        String json = gson.toJson(tagRep.getChilds(myTag.getValue()));
        System.out.println(tagRep.getChilds(myTag.getValue()).toString() + "   " + json);
        return json;
    }

    @RequestMapping(value = "getPersonalTagsForObj", method = RequestMethod.POST)
    public String getPersonalTagsForObjJSON(@RequestBody String data) {
        LOG.info("received "+data);
        Gson gson = new Gson();
        HashMap<String,String> myTag = gson.fromJson(data, HashMap.class);
        List<Propertie> retVal = userSession.getPersonalTagsForObj(myTag.get("id"));

        System.out.println(myTag.toString());
        return gson.toJson(retVal);
    }
    
    @RequestMapping(value = "getSharedTagsForObj", method = RequestMethod.POST)
    public String getSharedTagsForObjJSON(@RequestBody String data) {
        LOG.info("received "+data);
        Gson gson = new Gson();
        HashMap<String,String> myTag = gson.fromJson(data, HashMap.class);
        List<Propertie> retVal = userSession.getSharedTagsForObj(myTag.get("id"));

        System.out.println(myTag.toString());
        return gson.toJson(retVal);
    }

    @RequestMapping(value = "tagPost", method = RequestMethod.POST, consumes = "application/json")
    public String postShopInJSON(@RequestBody /*@Valid Persona*/ String data, BindingResult bindingResult) {
        /*if(bindingResult.hasErrors()){
         System.out.println("HAS ERRORS"+bindingResult.getAllErrors());
         }*/
        Gson gson = new Gson();

        //Persona aPerson = gson.fromJson(data, Persona.class);
        System.out.println(data);
        return "";//json;
    }

    @RequestMapping(value = "save-table", method = RequestMethod.POST, consumes = "application/json")
    public String saveTable(@RequestBody /*@Valid Persona*/ String data) {
        LOG.info("saveTable: " + data);
        boolean ok = true;
        Gson gson = new Gson();
        Table newTable = gson.fromJson(data, Table.class);

        LOG.info("new table to register: " + newTable.toString());
        int newTableId = userSession.registerNewTable(newTable);
        LOG.info("new table id: " + newTableId);
        System.out.println(data);
        Assoc response = new Assoc(newTableId != -1, newTableId);
        LOG.info("response " + response.toString());
        LOG.info("response json" + gson.toJson(response));
        return gson.toJson(response);//json;
    }

    @RequestMapping(value = "addElement", method = RequestMethod.POST, consumes = "application/json")
    public String addElement(@RequestBody String data) {
        LOG.info("Add element " + data);
        boolean ok = true;
        Gson gson = new Gson();
        SemTElem semTElem = gson.fromJson(data, SemTElem.class);

        //esclude fields from json data
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Table table = gson.fromJson(data, Table.class);

        LOG.info("New Element: " + semTElem.toString());

        LOG.info("new Element to register: " + semTElem.toString());
        LOG.info("On table with id" + table.toString());
        userSession.addElement(semTElem, table);
        /*LOG.info("new table id: "+newTableId);
         System.out.println(data);
         Assoc response = new Assoc(newTableId != -1,newTableId);
         LOG.info("response "+response.toString());
         LOG.info("response json"+gson.toJson(response));*/
        return gson.toJson("");//json;
    }

    @RequestMapping(value = "close-table", method = RequestMethod.POST, consumes = "application/json")
    public String closeTable(@RequestBody /*@Valid Persona*/ String data) {
        boolean ok = true;
        Gson gson = new Gson();
        Table closeTable = gson.fromJson(data, Table.class);

        LOG.info("table to close: " + closeTable.toString());
        userSession.closeTable(closeTable);
        System.out.println(data);
        return gson.toJson(ok);//json;
    }

}

class Assoc {

    boolean ok;
    int idTable;

    public Assoc(boolean ok, int idTable) {
        this.ok = ok;
        this.idTable = idTable;
    }

    @Override
    public String toString() {
        return "Assoc{" + "ok=" + ok + ", idTable=" + idTable + '}';
    }

}
