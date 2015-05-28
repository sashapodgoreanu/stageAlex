/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.unito.UserDetailsRepository;
import static java.util.logging.Level.INFO;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author SashaAlexandru
 */
@Controller
public class ControllerHome {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    


    @RequestMapping(value = "helloWorld", method = GET)
    public ModelAndView thiWillNotBeHello() {
        ModelAndView mv = new ModelAndView("displayName");
        mv.addObject("hello", "Hello");
        return mv;
    }

    /*@RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
     public String indexR(){
     return "redirect: index";
     }*/
    @RequestMapping(value = {"/index", "/login"}, method = GET)
    public String index(HttpServletRequest request, @AuthenticationPrincipal Object customUser) {
        //System.out.println("::::: "+jDBCAccountDetailsRep.toString());
        userDetailsRepository.find(5);
        return "index";
    }
    
    @RequestMapping(value = {"/logout"}, method = GET)
    public String logout() {
        //save user data
        //to do
        return "index";
    }

    @RequestMapping(value = "/workingarea", method = GET)
    public String working_area(@AuthenticationPrincipal User customUser) {
        LOG.log(INFO, "AuthenticationPrincipal = " + customUser.toString());
        
        return "working_area";
    }
    private static final Logger LOG = Logger.getLogger(ControllerHome.class.getName());
    
}
