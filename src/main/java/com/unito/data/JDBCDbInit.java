/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.data;

import com.unito.model.Assoc;
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

    public  Assoc createDB() {
        Assoc retVal = new Assoc();
        boolean ok = true;
        String error = "";
                
        int status = jdbcTemplate.update(""
                + " CREATE TABLE table_name"
                + " ("
                + " column_name1 VARCHAR(32),"
                + " column_name2 VARCHAR(32),"
                + " column_name3 VARCHAR(32)"
                + " )");
        
        if (status == 0){
            ok = false;
            error = "Error creating tables";
        }
        
        retVal.ok = ok;
        retVal.errMsg = error;
        retVal.val = status;
        
        return retVal;
    }
}
