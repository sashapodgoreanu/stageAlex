/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.repository;

import com.unito.model.repository.UserDetailsRepositoryJDBC;
import com.unito.UserDetailsRepository;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Configuration
@Profile("development")
public class JDBCConfigDev {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driverManager = new DriverManagerDataSource();
        driverManager.setDriverClassName("com.mysql.jdbc.Driver");
        driverManager.setUrl("jdbc:mysql://localhost:3306/stage01");
        driverManager.setUsername("root");
        driverManager.setPassword("645128");
        return driverManager;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean 
    public UserDetailsRepository userDetailsRepository(JdbcTemplate jdbcTemplate) {
        return new UserDetailsRepositoryJDBC(jdbcTemplate);
    }
    
    
}
