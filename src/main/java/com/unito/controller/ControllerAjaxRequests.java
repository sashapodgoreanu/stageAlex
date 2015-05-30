/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.controller;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.unito.UserDetailsRepository;
import com.unito.model.Tag;
import com.unito.model.TagRepository;
import com.unito.model.TokenValidateResponse;
import com.unito.model.UserDetails.UserDetails;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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

    private static final Logger LOG = Logger.getLogger(ControllerAjaxRequests.class.getName());

    private static final String VALIDATE_HTTPS = "https://www.googleapis.com/oauth2/v1/tokeninfo?id_token=";
    @EJB
    TagRepository tagRep;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsRepository userDetailsRepository;

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

        Gson gson = new Gson();
        UserDetails userLogin = null;
        UserDetails dbUser = null;
        String idToken;
        String json;
        boolean ok = false;
        try {
            userLogin = gson.fromJson(data, UserDetails.class);
        } catch (JsonSyntaxException e) {
        }
        //validate the user
        RestTemplate restTemplate = new RestTemplate();
        TokenValidateResponse tokenValidateResponse = restTemplate.getForObject(VALIDATE_HTTPS + userLogin.getIdtoken(), TokenValidateResponse.class);
        if (tokenValidateResponse.getUser_id().equals(userLogin.getId())) {
            //  recover the user form db
            dbUser = userDetailsRepository.find(userLogin.getId());
            if (dbUser != null){
                LOG.info("result update: "+userDetailsRepository.update(userLogin));
            }
            
            ok = true;
            
            //idToken = userLogin.getIdtoken();

            //do login
            // if userLogin.id doesn't exist in DB, register the user
            // else get info from db
            // authenticate user
            /*UsernamePasswordAuthenticationToken authRequest
             = new UsernamePasswordAuthenticationToken("user", "password");
             // Authenticate the user
             Authentication authentication = authenticationManager.authenticate(authRequest);
             SecurityContext securityContext = SecurityContextHolder.getContext();
             securityContext.setAuthentication(authentication);

             // Create a new session and add the security context.
             HttpSession session = request.getSession(true);
             session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);*/
        } else {
            //error login
            ok = false;
        }
        return json = gson.toJson(ok);//json;
    }
}
