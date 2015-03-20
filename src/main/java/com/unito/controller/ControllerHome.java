/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author SashaAlexandru
 */
@Controller
public class ControllerHome {
    
    @RequestMapping(value = "helloWorld", method = RequestMethod.GET)
    public ModelAndView thiWillNotBeHello(){
        ModelAndView mv = new ModelAndView("displayName");
        mv.addObject("hello","Hello");
        return mv;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }
}
