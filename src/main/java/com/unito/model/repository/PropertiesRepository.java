/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model.repository;

import com.unito.model.Properties;
import com.unito.model.SemTElem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SashaAlexandru
 */
@Repository
public class PropertiesRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SELECT_ALL_PERSONAL_PROPERTIES_OF_OBJECT =   " select * "
                                                                    + " from PROPERTIES  "
                                                                    + " where ID_USERDETAILS = ? and ID_OBJECT = ?";
    private final String SELECT_ALL_SHARED_PROPERTIES_OF_OBJECT = " select * "
                                                                + " from PROPERTIES  "
                                                                + " where ID_USERDETAILS = ? and ID_OBJECT = ? and SHARED = 1";

    public PropertiesRepository() {
    }

    /**
     * Gets all Personal properties of an object
     * 
     * @param idUser id of Logged in user
     * @param idObj id of current opened table
     * @return list of properties
     */
    public List<Properties> getPersonalPropertiesForObj(String idUser, String idObj) {
        List<Properties> retVal = jdbcTemplate.query(
                SELECT_ALL_PERSONAL_PROPERTIES_OF_OBJECT,
                (new PropertiesRepository.PropertiesMapper<>()),
                idUser, idObj);
        System.out.println(retVal.toString());
        return retVal;
    }

    public List<Properties> getSharedPropertiesForObj(String idUser, String idObj) {
        List<Properties> retVal = jdbcTemplate.query(
                SELECT_ALL_PERSONAL_PROPERTIES_OF_OBJECT,
                (new PropertiesRepository.PropertiesMapper<>()),
                idUser, idObj);
        System.out.println(retVal.toString());
        return retVal;
    }

    class PropertiesMapper<T> implements RowMapper {

        @Override
        public Properties mapRow(ResultSet rs, int rowNum) throws SQLException {
            Properties p = new Properties();
            p.setId(rs.getInt("ID"));
            p.setOwnerId(rs.getString("ID_USERDETAILS"));
            p.setValue(rs.getString("VALUE"));
            p.setIn_r_bin(rs.getBoolean("IN_R_BIN"));
            p.setShared(rs.getBoolean("SHARED"));
            return p;
        }
    }

    private static final Logger LOG = Logger.getLogger(PropertiesRepository.class.getName());

}
