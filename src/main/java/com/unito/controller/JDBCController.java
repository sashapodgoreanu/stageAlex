/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.unito.data.JDBCDbInit;
import com.unito.model.Assoc;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author SashaAlexandru
 */
@Controller
public class JDBCController {
    
    @Autowired
    private JDBCDbInit jDBCDbInit;
    
    @RequestMapping(value = "/adminjdbc", method = GET)
    public ModelAndView working_area(@RequestParam Map<String, Integer> params) {
        String error;
        ModelAndView mvc = new ModelAndView("adminjdbc");
        Assoc retVal = new Assoc();
        System.out.println("hello "+params.get("action"));
        switch(params.get("action")){
            //create bd
            case 1: retVal = jDBCDbInit.createDB();
        }
        if (!retVal.ok){}
            //error
        else mvc.addObject("val", retVal.val);
        return mvc;
    }    
}
