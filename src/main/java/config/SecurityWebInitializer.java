/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;



/**
 * AbstractSecurityWebApplicationInitializer implements
 * WebApplication-Initializer, It will be discovered by Spring and be used to
 * register Delegating-FilterProxy with the web container
 * 
 * @author Alexandru Podgoreanu
 */
public class SecurityWebInitializer extends AbstractSecurityWebApplicationInitializer {}