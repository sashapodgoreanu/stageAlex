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
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


/**
 *
 * @author Alexandru Podgoreanu
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.unito")
@Import({SecurityConfig.class})
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    Debug debug;

    @Bean

    /**
     * jsp location (prefix and sufix) file Resolver
     */
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
        registry.addResourceHandler("/css/**").addResourceLocations("WEB-INF/web-resources/css/")
                .setCachePeriod(debug.getValue() ? 0 : 600000);
        registry.addResourceHandler("/images/**").addResourceLocations("WEB-INF/web-resources/images/")
                .setCachePeriod(debug.getValue() ? 0 : 600000);
        registry.addResourceHandler("/js/**").addResourceLocations("WEB-INF/web-resources/js/")
                .setCachePeriod(debug.getValue() ? 0 : 600000);
        registry.addResourceHandler("/fonts/**").addResourceLocations("WEB-INF/web-resources/fonts/")
                .setCachePeriod(debug.getValue() ? 0 : 600000);
        registry.addResourceHandler("/jquery-ui/**").addResourceLocations("WEB-INF/web-resources/jquery-ui/")
                .setCachePeriod(debug.getValue() ? 0 : 600000);
        
        
    }
    
    /**
     * Static content handling Forward requests for static resources to the
     * servlet container’s defaultservlet
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }
    
    
}
