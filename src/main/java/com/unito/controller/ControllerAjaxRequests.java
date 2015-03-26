/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.google.gson.Gson;
import com.unito.model.Persona;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author SashaAlexandru
 */
@Controller

public class ControllerAjaxRequests {

    @RequestMapping(value = "url/{name}", method = RequestMethod.GET)
    public @ResponseBody
    String getShopInJSON(@PathVariable String name) {
        Persona persona = new Persona(18, "Sasha");

        Gson gson = new Gson();

        // convert java object to JSON format,  
        // and returned as JSON formatted string  
        String json = gson.toJson(persona);
        System.out.println(json);
        return json;

    }

}
