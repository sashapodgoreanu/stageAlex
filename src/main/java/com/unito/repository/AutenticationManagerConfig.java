/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author SashaAlexandru
 */

@Configuration
public class AutenticationManagerConfig {
     
    @Bean 
    public AuthenticationManager authenticationManager() {
        return new GoogleAuthenticationManager();
    }
    
}
