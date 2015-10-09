/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author SashaAlexandru
 */
@Controller
public class TestImplementationControler {
    
    @RequestMapping(value="/hidden-menu")
    public String hiddenMenu(){
        return "/template/hidden_menu";
    }
}
