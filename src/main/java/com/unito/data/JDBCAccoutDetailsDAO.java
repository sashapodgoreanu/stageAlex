/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.data;

import com.unito.AccoutDetailsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Repository
public class JDBCAccoutDetailsDAO implements AccoutDetailsDAO  {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
