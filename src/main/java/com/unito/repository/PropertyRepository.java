/*
 * To change this license header, choose License Headers in Project Propertie.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.repository;

import com.unito.model.Propertie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SashaAlexandru
 */
@Repository
public class PropertyRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String SELECT_ALL_PERSONAL_PROPERTIES_OF_OBJECT
            = " select * "
            + " from PROPERTIES  "
            + " where (ID_USERDETAILS = ? and ID_OBJECT = ?)";

    private final String SELECT_LIKED_PROPERTIES_OF_OBJECT_OF_USER
            = "select p.*, "
            + "pp.ID_USERDETAILS as ID_USERDETAILS_ACTION, pp.LIKED as LIKED\n"
            + "from PROPERTIES p join PROPERTIES_PREFERENCE pp on (p.id = pp.id_propertie)\n"
            + "where pp.ID_USERDETAILS = ? and p.ID_OBJECT = ? and pp.LIKED = 1";

    private final String SELECT_UNLIKED_PROPERTIES_OF_OBJECT_OF_USER
            = "select p.*, pp.ID_USERDETAILS as ID_USERDETAILS_ACTION, pp.LIKED as LIKED\n"
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
            + "and p.Value like ?\n"
            + "and p.DELETED = 0";
    private final String SELECT_ALL_CANDIDATES_PROPERTIES_liked_OF_USER_OF_TABLE
            = "select p.*, pp.ID_USERDETAILS as ID_USERDETAILS_ACTION, pp.LIKED as LIKED\n"
            + "from PROPERTIES p join PROPERTIES_PREFERENCE pp join semtelems_on_table sot \n"
            + "on (p.id = pp.id_propertie and p.id_object = sot.url)\n"
            + "where pp.ID_USERDETAILS = ? and \n"
            + "p.ID_OBJECT <> ? and \n"
            + "pp.LIKED = 1 and \n"
            + "sot.ID_TABLE = ? and \n"
            + "p.value like ?";

    private final String SELECT_ALL_CANDIDATES_PROPERTIES_shared_OF_TABLE
            = "select *\n"
            + "from PROPERTIES p join SEMTELEMS_ON_TABLE sot on(p.ID_OBJECT = sot.URL)\n"
            + "where ID_OBJECT <> ? \n"
            + "and sot.ID_TABLE = ? \n"
            + "and VALUE like ?\n"
            + "and SHARED = 1  \n"
            + "and DELETED = 0";

    private final String SELECT_ALL_CANDIDATES_PROPERTIES_UNLIKED_OF_TABLE_FOR_USER
            = "select p.*, pp.ID_USERDETAILS as ID_USERDETAILS_ACTION, pp.LIKED as LIKED\n"
            + "from PROPERTIES p join PROPERTIES_PREFERENCE pp join SEMTELEMS_ON_TABLE sot \n"
            + "on (p.id = pp.id_propertie and p.ID_OBJECT = sot.URL)\n"
            + "where pp.ID_USERDETAILS = ?\n"
            + "and p.ID_OBJECT = ?\n"
            + "and sot.ID_TABLE = ?\n"
            + "and p.VALUE like ?\n"
            + "and pp.LIKED = 0";

    private final String SELECT_ALL_TAGS_BY_VALUE_AND_BY_OBJECT_ID
            = "select *\n"
            + "from PROPERTIES\n"
            + "where ID_OBJECT = ?\n"
            + "and UPPER(VALUE) = UPPER(?)";

    private final String INSERT_TAG
            = "insert into PROPERTIES(VALUE,ID_USERDETAILS,ID_OBJECT,SHARED) "
            + "values (?,?,?,?)";

    private final String RESTORE_TAG
            = "update PROPERTIES \n"
            + "set DELETED = 0\n"
            + "where id = ?";
    private final String DELETE_TAG
            = "update PROPERTIES \n"
            + "set DELETED = 1\n"
            + "where id = ?";
    private final String SHARE_TAG
            = "update PROPERTIES \n"
            + "set SHARED = 1\n"
            + "where id = ?";

    private final String SELECT_LIKE_TAG
            = "select * from PROPERTIES_PREFERENCE \n"
            + "where ID_PROPERTIE = ? and ID_USERDETAILS = ?";

    private final String UPDATE_LIKE_TAG
            = "update PROPERTIES_PREFERENCE \n"
            + "set LIKED = ?\n"
            + "where ID_PROPERTIE = ? and ID_USERDETAILS = ?";

    private final String INSERT_LIKE_TAG
            = "insert into PROPERTIES_PREFERENCE(LIKED, ID_PROPERTIE, ID_USERDETAILS) \n"
            + "values(?,?,?)";
    
    private final String DELETE_LIKE_TAG
            = "delete from PROPERTIES_PREFERENCE\n"
            + "where ID_PROPERTIE = ? and ID_USERDETAILS = ?";

    public PropertyRepository() {
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
                (new PropertyRepository.PropertieMapper<>()),
                idUser, idObj);
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getSharedPropertiesForObj(String idObj) {
        System.out.format(SELECT_ALL_SHARED_PROPERTIES_OF_OBJECT, idObj);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_ALL_SHARED_PROPERTIES_OF_OBJECT,
                (new PropertyRepository.PropertieMapper<>()), idObj);
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getLikedPropertiesForObj(String idUser, String idObj) {
        System.out.format(SELECT_LIKED_PROPERTIES_OF_OBJECT_OF_USER, idUser, idObj);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_LIKED_PROPERTIES_OF_OBJECT_OF_USER,
                (new PropertyRepository.PropertieMapper<>()),
                idUser, idObj);
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getUnLikedPropertiesForObj(String idUser, String idObj) {
        System.out.format(SELECT_UNLIKED_PROPERTIES_OF_OBJECT_OF_USER, idUser, idObj);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_UNLIKED_PROPERTIES_OF_OBJECT_OF_USER,
                (new PropertyRepository.PropertieMapper<>()),
                idUser, idObj);
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getPersonalCandidateTagsForTable(String idUser, int tableId, String objectId, String candidate) {
        LOG.info(SELECT_ALL_CANDIDATES_PROPERTIES_OF_USER_OF_TABLE_NOT_OF_OBJECT_NOT_DELETED);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_ALL_CANDIDATES_PROPERTIES_OF_USER_OF_TABLE_NOT_OF_OBJECT_NOT_DELETED,
                (new PropertyRepository.PropertieMapper<>()), idUser, tableId, objectId, "%" + candidate + "%");
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getLikedCandidateTagsForTable(String idUser, int tableId, String objId, String candidate) {
        LOG.info(SELECT_ALL_CANDIDATES_PROPERTIES_liked_OF_USER_OF_TABLE);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_ALL_CANDIDATES_PROPERTIES_liked_OF_USER_OF_TABLE,
                (new PropertyRepository.PropertieMapper<>()), idUser, objId, tableId, "%" + candidate + "%");
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getSharedCandidateTagsForTable(int tableId, String objId, String candidate) {
        LOG.info(SELECT_ALL_CANDIDATES_PROPERTIES_shared_OF_TABLE);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_ALL_CANDIDATES_PROPERTIES_shared_OF_TABLE,
                (new PropertyRepository.PropertieMapper<>()), objId, tableId, "%" + candidate + "%");
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> getUnlikedCandidateTagsForTable(String idUser, int tableId, String objId, String candidate) {
        LOG.info(SELECT_ALL_CANDIDATES_PROPERTIES_UNLIKED_OF_TABLE_FOR_USER);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_ALL_CANDIDATES_PROPERTIES_UNLIKED_OF_TABLE_FOR_USER,
                (new PropertyRepository.PropertieMapper<>()), idUser, objId, tableId, "%" + candidate + "%");
        LOG.info(retVal.toString());
        return retVal;
    }

    public List<Propertie> findTag(String lastObjectOpened, String tag) {
        LOG.info(SELECT_ALL_TAGS_BY_VALUE_AND_BY_OBJECT_ID);
        List<Propertie> retVal = jdbcTemplate.query(SELECT_ALL_TAGS_BY_VALUE_AND_BY_OBJECT_ID,
                (new PropertyRepository.PropertieMapper<>()), lastObjectOpened, tag);
        LOG.info(retVal.toString());
        return retVal;
    }

    public boolean addTag(String idUser, String lastObjectOpened, String tag, int tagType) {
        return jdbcTemplate.update(INSERT_TAG, tag, idUser, lastObjectOpened, tagType) == 1;
    }

    public boolean doRestore(int idTag) {
        return jdbcTemplate.update(RESTORE_TAG, idTag) == 1;
    }

    public boolean doDelete(int idTag) {
        return jdbcTemplate.update(DELETE_TAG, idTag) == 1;
    }

    public boolean doShare(int idTag) {
        return jdbcTemplate.update(SHARE_TAG, idTag) == 1;
    }

    public boolean doLike(int idTag, String idUser) {
        LOG.info(SELECT_LIKE_TAG);
        Map retSel = new HashMap();
        try {
            retSel = jdbcTemplate.queryForMap(SELECT_LIKE_TAG, idTag, idUser);
        } catch (EmptyResultDataAccessException e) {
        }
        if (retSel.isEmpty()) {
            return jdbcTemplate.update(INSERT_LIKE_TAG, 1, idTag, idUser) == 1;
        } else {
            return jdbcTemplate.update(UPDATE_LIKE_TAG, 1, idTag, idUser) == 1;
        }
    }

    public boolean doUnLike(int idTag, String idUser) {
        LOG.info(SELECT_LIKE_TAG);
        Map retSel = new HashMap();
        try {
            retSel = jdbcTemplate.queryForMap(SELECT_LIKE_TAG, idTag, idUser);
        } catch (EmptyResultDataAccessException e) {
        }
        if (retSel.isEmpty()) {
            return jdbcTemplate.update(INSERT_LIKE_TAG, 0, idTag, idUser) == 1;
        } else {
            return jdbcTemplate.update(UPDATE_LIKE_TAG, 0, idTag, idUser) == 1;
        }
    }

    public boolean doDefault(int idTag, String idUser) {
        return jdbcTemplate.update(DELETE_LIKE_TAG, idTag, idUser) == 1;
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
                p.setLiked(rs.getInt("LIKED"));
            } catch (SQLException e) {
                LOG.info(e.getMessage());
            }

            p.setShared(rs.getBoolean("SHARED"));
            p.setDeleted(rs.getBoolean("DELETED"));
            return p;
        }
    }

    private static final Logger LOG = Logger.getLogger(PropertyRepository.class.getName());

}
