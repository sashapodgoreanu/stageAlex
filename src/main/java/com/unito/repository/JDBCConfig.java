/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.repository;

import com.unito.UserDetailsRepository;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Configuration
public class JDBCConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        //driverManager.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
        //driverManager.setDriverClassName(new org.apache.derby.jdbc.EmbeddedDriver());
        //driverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        driverManager.setDriverClassName("org.apache.derby.jdbc.ClientDriver");
        //create=true - start db derby server
        driverManager.setUrl("jdbc:derby://localhost:1527/semtt;create=true");
        driverManager.setUsername("semuser");
        driverManager.setPassword("password");
        return driverManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean 
    public UserDetailsRepository userDetailsRepository(JdbcTemplate jdbcTemplate) {
        return new JDBCUserDetailsRepository(jdbcTemplate);
    }

}
