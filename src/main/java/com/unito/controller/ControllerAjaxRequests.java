/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.google.gson.Gson;
import com.unito.model.Tag;
import com.unito.model.TagRepository;
import com.unito.model.TokenValidateResponse;
import com.unito.model.UserDetails.UserDetails;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Alexandru Podgoreanu
 */
// è un Controller + ResponseBody
@RestController

/*@ResponseBody
 @Controller*/
public class ControllerAjaxRequests {

    private static final String VALIDATE_HTTPS = "https://www.googleapis.com/oauth2/v1/tokeninfo?id_token=";
    @EJB
    TagRepository tagRep;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "tag", method = RequestMethod.POST)
    public String getShopInJSON(@RequestBody String data) {

        Gson gson = new Gson();
        Tag myTag = gson.fromJson(data, Tag.class);

        String json = gson.toJson(tagRep.getChilds(myTag.getValue()));
        System.out.println(tagRep.getChilds(myTag.getValue()).toString() + "   " + json);
        return json;
    }

    @RequestMapping(value = "tagPost", method = RequestMethod.POST, consumes = "application/json")
    public String postShopInJSON(@RequestBody /*@Valid Persona*/ String data, BindingResult bindingResult) {
        /*if(bindingResult.hasErrors()){
         System.out.println("HAS ERRORS"+bindingResult.getAllErrors());
         }*/
        Gson gson = new Gson();

        //Persona aPerson = gson.fromJson(data, Persona.class);
        System.out.println(data);
        return "";//json;
    }

    /*@RequestMapping(value = "/verifyLogin", method = RequestMethod.POST, consumes = "application/json")
     public String verifyLogin(@RequestBody String data) {
     System.out.println("sssssssssssssssss ");
     Gson gson = new Gson();
     UserDetails userLoging = gson.fromJson(data, UserDetails.class);
     //Persona aPerson = gson.fromJson(data, Persona.class);
     System.out.println(userLoging);
     return "";//json;
     }*/
    @RequestMapping(value = "/verifyLogin", method = RequestMethod.POST, consumes = "application/json")
    public String verifyLogin(@RequestBody String data, HttpServletRequest request) {
        System.out.println(data);

        Gson gson = new Gson();
        UserDetails userLogin = gson.fromJson(data, UserDetails.class);
        
        String idToken = userLogin.getIdtoken();
        
         RestTemplate restTemplate = new RestTemplate();
         TokenValidateResponse tokenValidateResponse = restTemplate.getForObject(VALIDATE_HTTPS+idToken, TokenValidateResponse.class);
         
         System.out.println(tokenValidateResponse.toString());

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken("user", "password");
        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(authRequest);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        return "";//json;
    }
}
