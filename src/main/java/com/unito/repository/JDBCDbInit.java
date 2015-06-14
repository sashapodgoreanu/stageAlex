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
            + "                ("
            + "                ID varchar(1024) primary key,"
            + "                ID_TOKEN varchar (1024),"
            + "                ACCESS_TOKEN varchar (1024),"
            + "                ROLE_USER varchar (16)"
            + "                )";
    private final String DROP_USER_DETAILS = "DROP table " + USER_DETAILS;

    private final String W_TABLE = "W_TABLE";
    private final String CREATE_W_TABLE = "create table " + W_TABLE
            + "                (ID integer not null primary key"
            + "                generated always as identity (start with 1, increment by 1),"
            + "                NAME varchar (64) unique,"
            + "                OWNER varchar (1024)"
            + "                )";
    private final String DROP_W_TABLE = "drop table " + W_TABLE;

    private final String USER_W_TABLE = "USER_W_TABLE";
    private final String CREATE_USER_W_TABLE = "create table " + USER_W_TABLE
            + "                    (USERDETAILS_ID VARCHAR(1024),"
            + "                    W_TABLE_ID INTEGER,"
            + "                    OPENED boolean,"
            + "                    PRIMARY KEY(USERDETAILS_ID, W_TABLE_ID),"
            + "                    FOREIGN KEY(USERDETAILS_ID) REFERENCES USERDETAILS(ID),"
            + "                    FOREIGN KEY(W_TABLE_ID) REFERENCES W_TABLE(ID)"
            + "                    )";
    private final String DROP_USER_W_TABLE = "drop table " + USER_W_TABLE;

    private final String OBJECTS = "OBJECTS";
    private final String CREATE_OBJECTS = "create table " + OBJECTS
            + "                    ("
            + "                     URL varchar(1024) not null,"
            + "                     ID_PROPERTY integer"
            + "                    )";
    private final String DROP_OBJECTS = "drop table " + OBJECTS;

    private final String OBJECTS_ON_TABLE = "OBJECTS_ON_TABLE";
    private final String CREATE_OBJECTS_ON_TABLE = "create table " + OBJECTS_ON_TABLE
            + "                    ("
            + "                     URL varchar(1024) not null,"
            + "                     ID_TABLE integer,"
            + "                     IN_WARDROBE BOOLEAN,"
            + "                     primary key(ID_TABLE, URL)"
            + "                    )";
    private final String DROP_OBJECTS_ON_TABLE = "drop table " + OBJECTS_ON_TABLE;

    private final String PROPERTIES = "PROPERTIES";
    private final String CREATE_PROPERTIES = "create table " + PROPERTIES
            + "                    ("
            + "                     ID integer not null primary key\n"
            + "                     generated always as identity (start with 1, increment by 1),"
            + "                     VALUE varchar(64) not null,"
            + "                     ID_USERDETAILS varchar(1024),"
            + "                     PRIVATE boolean,"
            + "                     foreign key(ID_USERDETAILS) references USERDETAILS(ID)"
            + "                    )";
    private final String DROP_PROPERTIES = "drop table " + PROPERTIES;

    @Autowired
    public JDBCDbInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean createDB() {
        boolean success = true;
        if (existsTable(PROPERTIES)) {
            jdbcTemplate.update(DROP_PROPERTIES);

        }
        if (existsTable(OBJECTS_ON_TABLE)) {
            jdbcTemplate.update(DROP_OBJECTS_ON_TABLE);

        }
        if (existsTable(USER_W_TABLE)) {
            jdbcTemplate.update(DROP_USER_W_TABLE);

        }
        if (existsTable(USER_DETAILS)) {
            jdbcTemplate.update(DROP_USER_DETAILS);

        }
        if (existsTable(W_TABLE)) {
            jdbcTemplate.update(DROP_W_TABLE);

        }
        if (existsTable(OBJECTS)) {
            jdbcTemplate.update(DROP_OBJECTS);
        }

        int res = jdbcTemplate.update(CREATE_USER_DETAILS);
        success = success && res == 1;
        res = jdbcTemplate.update(CREATE_W_TABLE);
        success = success && res == 1;
        res = jdbcTemplate.update(CREATE_OBJECTS);
        success = success && res == 1;
        res = jdbcTemplate.update(CREATE_USER_W_TABLE);
        success = success && res == 1;
        res = jdbcTemplate.update(CREATE_OBJECTS_ON_TABLE);
        success = success && res == 1;
        res = jdbcTemplate.update(CREATE_PROPERTIES);
        success = success && res == 1;

        return success;

    }

    private boolean existsTable(String sTablename) {
        ResultSet rs;
        try {
            DatabaseMetaData dbmd = jdbcTemplate.getDataSource().getConnection().getMetaData();
            rs = dbmd.getTables(null, null, sTablename.toUpperCase(), null);
            if (rs.next()) {
                LOG.info("Table " + sTablename + " already exists !!");
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
