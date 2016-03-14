/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.unito.repository.config.JDBCDbInit;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
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
public class ControllerJDBC {

    @Autowired
    private JDBCDbInit jDBCDbInit;
    private static final Logger LOG = Logger.getLogger(ControllerJDBC.class.getName());
    HashMap<String,String> a;
    @RequestMapping(value = "/adminjdbc", method = GET)
    public ModelAndView working_area(@RequestParam(required = false) Map<String, String> params) {
        LOG.info("/adminjdbc");
        ModelAndView mvc = new ModelAndView("adminjdbc");
        String action = params.get("action");
        boolean result = false;
        LOG.info("params " + action);
        
        if (action == null) {
            return mvc;
        } else {
            switch (action) {
                //create bd
                case "0":
                    result = jDBCDbInit.createDB();
            }
        }
        mvc.addObject("result",result);
        return mvc;
    }
}
