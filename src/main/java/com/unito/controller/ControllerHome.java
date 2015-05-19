/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private AuthenticationProvider authenticationManager;
    


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
        System.out.println("::::: ");
        return "index";
    }

    public void login(HttpServletRequest request, String userName, String password) {

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userName, password);

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }

    @RequestMapping(value = "/workingarea", method = GET)
    public String working_area() {
        return "working_area";
    }
}
