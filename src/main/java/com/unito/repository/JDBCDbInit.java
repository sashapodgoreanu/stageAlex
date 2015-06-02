/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.repository;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author SashaAlexandru
 */
@Component
public class JDBCDbInit {

    private final JdbcTemplate jdbcTemplate;

    private final String USER_DETAILS = "UserDetails";
    private final String CREATE_USER_DETAILS = "create table " + USER_DETAILS
            + "                (\n"
            + "                ID varchar(1024) primary key,\n"
            + "                ID_TOKEN varchar (1024),\n"
            + "                ACCESS_TOKEN varchar (1024),\n"
            + "                ROLE_USER varchar (16)\n"
            + "                )";
    private final String DROP_USER_DETAILS = "DROP table " + USER_DETAILS;

    private final String W_TABLE = "W_TABLE";
    private final String CREATE_W_TABLE = "create table " + W_TABLE
            + "                (ID integer not null primary key \n"
            + "                generated always as identity (start with 1, increment by 1),\n"
            + "                NAME varchar (16),\n"
            + "                OWNER varchar (1024)\n"
            + "                )";
    private final String DROP_W_TABLE = "drop table " + W_TABLE;

    private final String USER_W_TABLE = "USER_W_TABLE";
    private final String CREATE_USER_W_TABLE = "create table " + USER_W_TABLE
            + "                    (USERDETAILS_ID VARCHAR(1024),\n"
            + "                    W_TABLE_ID INTEGER,\n"
            + "                    OPENED boolean,\n"
            + "                    PRIMARY KEY(USERDETAILS_ID, W_TABLE_ID),\n"
            + "                    FOREIGN KEY(USERDETAILS_ID) REFERENCES USERDETAILS(ID),\n"
            + "                    FOREIGN KEY(W_TABLE_ID) REFERENCES W_TABLE(ID)\n"
            + "                    )";
    private final String DROP_USER_W_TABLE = "drop table " + USER_W_TABLE;

    @Autowired
    public JDBCDbInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean createDB() {

        if (existsTable(USER_W_TABLE)) {
            jdbcTemplate.update(DROP_USER_W_TABLE);
        }
        if (existsTable(USER_DETAILS)) {
            jdbcTemplate.update(DROP_USER_DETAILS);
        }
        if (existsTable(W_TABLE)) {
            jdbcTemplate.update(DROP_W_TABLE);
        }

        jdbcTemplate.update(CREATE_USER_DETAILS);
        jdbcTemplate.update(CREATE_W_TABLE);
        jdbcTemplate.update(CREATE_USER_W_TABLE);

        return true;

    }

    private boolean existsTable(String sTablename) {
        ResultSet rs;
        try {
            DatabaseMetaData dbmd = jdbcTemplate.getDataSource().getConnection().getMetaData();
            rs = dbmd.getTables(null, null, sTablename.toUpperCase(), null);
            if (rs.next()) {
                LOG.info("Table " + sTablename + "already exists !!");
                return true;
            } else {
                LOG.info(": table doesn't exist: " + sTablename);
                return false;
            }
        } catch (SQLException e) {
            LOG.severe(Thread.currentThread().getStackTrace()[1].getMethodName());
        }
        return true;
    }

    private static final Logger LOG = Logger.getLogger(JDBCDbInit.class.getName());
}
