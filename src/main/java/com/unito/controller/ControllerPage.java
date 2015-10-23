/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.unito.UserDetailsRepository;
import com.unito.model.UserDetails.UserDetails;
import com.unito.model.TableManager;
import java.security.Principal;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.servlet.ModelAndView;

/**
 * UsserSession is a session bean so i dont need to put the userSession in a
 * session object
 *
 * @author SashaAlexandru
 */
@Controller
public class ControllerPage {
    org.springframework.security.authentication.AnonymousAuthenticationToken a;
    Principal b;
    private String title;
    private ModelAndView mvc;

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private TableManager tableManager;
    private final String REVOKE_URL = "https://accounts.google.com/o/oauth2/revoke?token=";
    
    
    @RequestMapping(value = {"/index", "/login", "/"}, method = GET)
    public ModelAndView index(HttpServletRequest request, @AuthenticationPrincipal Object customUser) {
        mvc = new ModelAndView("index");
        title = "Login Page";
        mvc.addObject("title", title);
        return mvc;
    }

    @RequestMapping(value = {"/processlogin"}, method = GET)
    public String processlogin(HttpServletRequest request, @AuthenticationPrincipal User customUser) {
        //get UserDetails from Db 
        //Set TableManager with UserDetails
        System.out.println("userDetailsRepository"+userDetailsRepository);
        System.out.println("tableManager"+tableManager);
        UserDetails ud = userDetailsRepository.find(customUser.getUsername());
        request.getSession().setAttribute("UserDetails", ud);
        tableManager.setUserdetails(ud);
        //return "redirect:/workingarea/0";
        return "redirect:/workingarea/32";
    }

    @RequestMapping(value = {"/logout", "/logoutapp"}, method = GET)
    public String logout(@AuthenticationPrincipal User customUser) {
        /*UserDetails userDetails;
         RestTemplate restTemplate = new RestTemplate();
        
         LOG.info("logout");
         LOG.info("ustom user = "+customUser);
        
         userDetails = userDetailsRepository.find(customUser.getUsername());
         if (userDetails != null){
         LOG.info("GET:"+REVOKE_URL+userDetails.getAccesstoken());
         restTemplate.getForObject(REVOKE_URL+userDetails.getAccesstoken(), String.class);
         } else return "redirect: logoutws?googleinssuccess"; //was a problem with google session login*/
        return "redirect: logoutws";
    }

    @RequestMapping(value = {"/logoutsuccess"}, method = GET)
    public String logoutsucess(@AuthenticationPrincipal User customUser) {
        LOG.info("logoutsuccess");
        return "index";
    }

    @RequestMapping(value = "/workingarea/{idTable}", method = GET)
    public ModelAndView working_area(@AuthenticationPrincipal User customUser, @PathVariable int idTable) {

        mvc = new ModelAndView("working_area");
        title = "Working Area";
        tableManager.setOpenTableId(idTable);
        LOG.info("Id table: " + idTable);
        LOG.info(tableManager.toString());
        //LOG.info(tableManager.getOpenedTables().toString());

        mvc.addObject("openedTables", tableManager.getOpenedTables());
        mvc.addObject("elementsOnTable", tableManager.getElementsOnTable());
        mvc.addObject("usersOnTable", tableManager.getUsersOnTable());
        mvc.addObject("idTable", idTable);
        mvc.addObject("title", title);

        return mvc;
    }
    private static final Logger LOG = Logger.getLogger(ControllerPage.class.getName());

    
}
