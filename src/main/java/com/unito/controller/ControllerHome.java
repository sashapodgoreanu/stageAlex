/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.unito.UserDetailsRepository;
import com.unito.model.UserDetails.UserDetails;
import static java.util.logging.Level.INFO;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author SashaAlexandru
 */
@Controller
public class ControllerHome {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    private final String REVOKE_URL = "https://accounts.google.com/o/oauth2/revoke?token=";


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
        //userDetailsRepository.find(5);
        return "index";
    }
    
    
    @RequestMapping(value = {"/logout", "/logoutapp"}, method = GET)
    public String logout(@AuthenticationPrincipal User customUser) {
        UserDetails userDetails;
        RestTemplate restTemplate = new RestTemplate();
        
        LOG.info("logout");
        LOG.info("ustom user = "+customUser);
        
        userDetails = userDetailsRepository.find(customUser.getUsername());
        if (userDetails != null){
            LOG.info("GET:"+REVOKE_URL+userDetails.getAccesstoken());
            restTemplate.getForObject(REVOKE_URL+userDetails.getAccesstoken(), String.class);
        } else return "redirect: logoutws?googleinssuccess"; //was a problem with google session login
        return "redirect: logoutws";
    }
    
    @RequestMapping(value = {"/logoutsuccess"}, method = GET)
    public String logoutsucess(@AuthenticationPrincipal User customUser) {
        LOG.info("logoutsuccess");
        return "index";
    }

    @RequestMapping(value = "/workingarea", method = GET)
    public String working_area(@AuthenticationPrincipal User customUser) {
        LOG.log(INFO, "AuthenticationPrincipal = " + customUser.toString());
        
        return "working_area";
    }
    private static final Logger LOG = Logger.getLogger(ControllerHome.class.getName());
    
}
