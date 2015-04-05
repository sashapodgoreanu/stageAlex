/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.google.gson.Gson;
import com.unito.model.Tag;
import com.unito.model.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired TagRepository tagRep;
    
    @RequestMapping(value = "tag", method = RequestMethod.POST)
    public String getShopInJSON(@RequestBody String data) {
        
        System.out.println("Ho ricevuto "+ data);
        Gson gson = new Gson();
        System.out.println(data);
        Tag myTag = gson.fromJson(data, Tag.class);
        
        String json = gson.toJson(tagRep.getChilds(myTag.getValue()));
        System.out.println("JSON:  "+ json);
        return json;
    }
            

    @RequestMapping(value = "tagPost", method = RequestMethod.POST, consumes = "application/json")
    public
            String postShopInJSON(@RequestBody /*@Valid Persona*/ String data, BindingResult bindingResult) {
                /*if(bindingResult.hasErrors()){
                 System.out.println("HAS ERRORS"+bindingResult.getAllErrors());
                 }*/
                System.out.println(data);
                Gson gson = new Gson();

        //Persona aPerson = gson.fromJson(data, Persona.class);
                System.out.println(data);
                return "";//json;
            }
}
