/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.unito.UserDetailsRepository;
import com.unito.model.UserDetails.UserDetails;
import com.unito.model.UserSession;
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
 * UsserSession is a session bean so i dont need to put the userSession in a session object
 * @author SashaAlexandru
 */
@Controller
public class PageController {
    
    private String title;
    private ModelAndView mvc;
    
    @Autowired private UserDetailsRepository userDetailsRepository;
    @Autowired private UserSession userSession;
    private final String REVOKE_URL = "https://accounts.google.com/o/oauth2/revoke?token=";

    @RequestMapping(value = {"/index", "/login","/"}, method = GET)
    public ModelAndView index(HttpServletRequest request, @AuthenticationPrincipal Object customUser) {
        mvc = new ModelAndView("index");
        title = "Login Page";
        mvc.addObject("title",title);
        return mvc;
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
    public ModelAndView working_area(@AuthenticationPrincipal User customUser) {
        mvc = new ModelAndView("working_area");
        title = "Working Area";
        LOG.info(userSession.toString());
        LOG.info(userSession.getOpenedTables().toString());
        
        mvc.addObject("openedTables",userSession.getOpenedTables());
        mvc.addObject("title",title);
        
        return mvc;
    }
    private static final Logger LOG = Logger.getLogger(PageController.class.getName());
    
}
