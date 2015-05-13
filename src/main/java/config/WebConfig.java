/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;


import com.unito.model.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.unito")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Debug debug;
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setExposeContextBeansAsAttributes(true);
        //for debug
        resolver.setCache(debug.getValue());
        return resolver;
    }
    
    // equivalents for <mvc:resources/> tags
    //<-- RESOURCES -->
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("WEB-INF/web-resources/css/").setCachePeriod(0);
        registry.addResourceHandler("/images/**").addResourceLocations("WEB-INF/web-resources/images/").setCachePeriod(0);
        registry.addResourceHandler("/js/**").addResourceLocations("WEB-INF/web-resources/js/").setCachePeriod(0);
        registry.addResourceHandler("/fonts/**").addResourceLocations("WEB-INF/web-resources/fonts/").setCachePeriod(0);
        registry.addResourceHandler("/jquery-ui/**").addResourceLocations("WEB-INF/web-resources/jquery-ui/").setCachePeriod(0);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
