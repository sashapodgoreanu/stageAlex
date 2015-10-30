/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author SashaAlexandru
 */
@Controller
public class TestImplementationControler {
    
    @ResponseBody @RequestMapping(value="/test1.js",headers="Accept=application/json" ,produces= MediaType.APPLICATION_JSON_VALUE)
    
    public String hiddenMenu(){
        return "/test/test1";
    }
}
