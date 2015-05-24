/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.unito.data.GoogleAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/workingarea/**")
                    .hasRole("USER")
                    .anyRequest()
                    .authenticated()
                .anyRequest()
                    .authenticated()
                    .and()
                .formLogin().loginPage("/login").permitAll()
                    .usernameParameter("username").passwordParameter("password")
                    .failureUrl("/login?error")
                    .and()
                .logout()
                    /*.logoutUrl("/login")*/
                    .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();
                   /* .and()
                .httpBasic();*/

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/images/**", "/js/**", "/jquery-ui/**","/index","/verifyLogin");
    }
    
    @Bean
    public AuthenticationProvider authenticator() {
        return new TestingAuthenticationProvider();
    }
}
