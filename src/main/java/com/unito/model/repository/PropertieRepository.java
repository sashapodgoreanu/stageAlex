/*
 * To change this license header, choose License Headers in Project Propertie.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model.repository;

import com.unito.model.Propertie;
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
public class PropertieRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SELECT_ALL_PERSONAL_PROPERTIES_OF_OBJECT
            = " select * "
            + " from PROPERTIES  "
            + " where (ID_USERDETAILS = ? and ID_OBJECT = ?)";

    private final String SELECT_LIKED_PROPERTIES_OF_OBJECT_OF_USER
            = "select p.ID as ID, p.ID_USERDETAILS as ID_USERDETAILS, p.value as VALUE,"
            + " p.ID_OBJECT as ID_OBJECT, p.shared as SHARED,"
            + " p.DELETED as DELETED,"
            + " pp.ID_USERDETAILS as ID_USERDETAILS_ACTION, pp.LIKED as LIKED\n"
            + "from PROPERTIES p join PROPERTIES_PREFERENCE pp on (p.id = pp.id_propertie)\n"
            + "where pp.ID_USERDETAILS = ? and p.ID_OBJECT = ? and pp.LIKED = 1";

    private final String SELECT_UNLIKED_PROPERTIES_OF_OBJECT_OF_USER
            = "select p.ID as ID, p.ID_USERDETAILS as ID_USERDETAILS, p.value as VALUE, p.ID_OBJECT as ID_OBJECT, p.shared as SHARED, pp.ID_USERDETAILS as ID_USERDETAILS_ACTION, pp.LIKED as LIKED\n"
            + "from PROPERTIES p join PROPERTIES_PREFERENCE pp on (p.id = pp.id_propertie)\n"
            + "where pp.ID_USERDETAILS = ? and p.ID_OBJECT = ? and pp.LIKED = 0";

    private final String SELECT_ALL_SHARED_PROPERTIES_OF_OBJECT
            = " select * "
            + " from PROPERTIES  "
            + " where ID_OBJECT = ? and SHARED = 1";

    private final String SELECT_ALL_CANDIDATES_PROPERTIES_OF_USER_OF_TABLE_NOT_OF_OBJECT_NOT_DELETED
            = "select p.*\n"
            + "from PROPERTIES p join SEMTELEMS_ON_TABLE sot on (p.ID_OBJECT = sot.URL)\n"
            + "where p.ID_USERDETAILS = ? \n"
            + "and sot.ID_TABLE = ? \n"
            + "and p.ID_OBJECT <> ? \n"
            + "and p.Value like '%?%'\n"
            + "and p.DELETED = 0";

    public PropertieRepository() {
    }

    /**
     * Gets all Personal properties of an object
     *
     * @param idUser id of Logged in user
     * @param idObj id of current opened table
     * @return list of properties
     */
    public List<Propertie> getPersonalProperties(String idUser, String idObj) {
        List<Propertie> retVal = jdbcTemplate.query(SELECT_ALL_PERSONAL_PROPERTIES_OF_OBJECT,
                (new PropertieRepository.PropertieMapper<>()),
                idUser, idObj);
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getSharedPropertiesForObj(String idObj) {
        System.out.format(SELECT_ALL_SHARED_PROPERTIES_OF_OBJECT, idObj);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_ALL_SHARED_PROPERTIES_OF_OBJECT,
                (new PropertieRepository.PropertieMapper<>()), idObj);
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getLikedPropertiesForObj(String idUser, String idObj) {
        System.out.format(SELECT_LIKED_PROPERTIES_OF_OBJECT_OF_USER, idUser, idObj);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_LIKED_PROPERTIES_OF_OBJECT_OF_USER,
                (new PropertieRepository.PropertieMapper<>()),
                idUser, idObj);
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getUnLikedPropertiesForObj(String idUser, String idObj) {
        System.out.format(SELECT_UNLIKED_PROPERTIES_OF_OBJECT_OF_USER, idUser, idObj);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_UNLIKED_PROPERTIES_OF_OBJECT_OF_USER,
                (new PropertieRepository.PropertieMapper<>()),
                idUser, idObj);
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getPersonalCandidateTagsForTable(String idUser, int tableId, String objectId, String candidate) {
        LOG.info(SELECT_ALL_CANDIDATES_PROPERTIES_OF_USER_OF_TABLE_NOT_OF_OBJECT_NOT_DELETED);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_ALL_CANDIDATES_PROPERTIES_OF_USER_OF_TABLE_NOT_OF_OBJECT_NOT_DELETED,
                (new PropertieRepository.PropertieMapper<>()), idUser, tableId, objectId, candidate);
        LOG.info(retVal.toString());
        return retVal;
    }

    class PropertieMapper<T> implements RowMapper {

        @Override
        public Propertie mapRow(ResultSet rs, int rowNum) throws SQLException {
            Propertie p = new Propertie();
            p.setId(rs.getInt("ID"));
            p.setValue(rs.getString("VALUE"));
            p.setOwnerId(rs.getString("ID_USERDETAILS"));

            //Questo serve per liked and unliked 
            try {
                p.setOwnerActionId(rs.getString("ID_USERDETAILS_ACTION"));
                p.setLiked(rs.getBoolean("LIKED"));
            } catch (SQLException e) {
                LOG.info(e.getMessage());
            }

            p.setShared(rs.getBoolean("SHARED"));
            p.setDeleted(rs.getBoolean("DELETED"));
            return p;
        }
    }

    private static final Logger LOG = Logger.getLogger(PropertieRepository.class.getName());

}
