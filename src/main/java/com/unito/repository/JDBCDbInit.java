/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author SashaAlexandru
 */
@Component
public class JDBCDbInit {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCDbInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public  boolean createDB() {
        boolean ok;
        int status = jdbcTemplate.update(""
                + " CREATE TABLE UserDetails"
                + " ("
                + " ID VARCHAR(1024) PRIMARY KEY,"
                + " ID_TOKEN VARCHAR(1024)"
                + ""
                + " )");
        return true;
    }
}
