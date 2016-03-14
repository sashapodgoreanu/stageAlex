/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Alexandru Podgoreanu
 * 
 *  @Configuration  
 *   Indicates that a class declares one or more @Bean methods and may be processed
 *   by the Spring container to generate bean definitions and service requests for those
 *   beans at runtime.
 * 
 *   @ComponentScan
 *   An equivalent for Spring XML's <context:component-scan/>
 */
@Configuration
@ComponentScan(
        basePackages={"com"},
        excludeFilters={
            @Filter(type=FilterType.ANNOTATION, 
            value=EnableWebMvc.class)
        })
public class RootConfig {
}
