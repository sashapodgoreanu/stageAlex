/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model.repository;

import com.unito.model.SemTElem;
import com.unito.model.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
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
public class SemTElemRepository {

    private JdbcTemplate jdbcTemplate;

    private final String SELECT_ALL_ELEMENTS = "select * from SEMTELEMS";
    private final String SELECT_ALL_ELEMENTS_ON_TABLE = "select * from SEMTELEMS s join SEMTELEMS_ON_TABLE sot "
            + "on(s.URL = sot.URL) "
            + "where sot.ID_TABLE = ?";
    private final String INSERT_ELEMENT = "insert into SEMTELEMS(NAME, URL) values(?,?)";
    private final String INSERT_ELEMENT_ON_TABLE = "insert into SEMTELEMS_ON_TABLE(URL, ID_TABLE, IN_WARDROBE) values(?,?,?)";

    @Autowired
    public SemTElemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SemTElem> getAllElements() {
        List<SemTElem> retVal = jdbcTemplate.query(SELECT_ALL_ELEMENTS, (new SemTElemRepository.SemTElemMapper<>()));
        LOG.info("getAllElements: " + retVal == null ? "null" : retVal.toString());
        return retVal;
    }

    public boolean addElement(SemTElem semTElem) {
        LOG.info("addElement ");
        return jdbcTemplate.update(INSERT_ELEMENT, semTElem.getName(), semTElem.getUrl()) == 1;
    }

    public boolean addElementinWardrobe(SemTElem semTElem, Table table) {
        LOG.info("addElementinWardrobe ");
        return jdbcTemplate.update(INSERT_ELEMENT_ON_TABLE, semTElem.getUrl(), table.getID(), true) == 1;
    }

    public List<SemTElem> getElements() {
        List<SemTElem> retVal = jdbcTemplate.query(SELECT_ALL_ELEMENTS, (new SemTElemRepository.SemTElemMapper<>()));
        return retVal;
    }

    public List<SemTElem> getElementsOnTable(int idTable) {
        List<SemTElem> retVal = jdbcTemplate.query(SELECT_ALL_ELEMENTS_ON_TABLE, (new SemTElemRepository.SemTElemMapper<>()), idTable);
        LOG.info(retVal == null ? "null" : retVal.toString());
        return retVal;
    }

    /**
     * Add a new element in wardrobe if doesnt exist.
     */
    public boolean addelementOnTable(SemTElem semTElem, Table table) {
        boolean insert = true;
        boolean insert2 = true;
        boolean ok = false;
        LOG.info("addelementOnTable:");
        for (SemTElem semTElemtemp : getAllElements()) {
            if (semTElemtemp.equals(semTElem)) {
                insert = false;
                break;
            }
        }
        LOG.info("insert1: " + insert);
        if (insert) {
            ok = addElement(semTElem) && addElementinWardrobe(semTElem, table);
        } else {
            for (SemTElem semTElemtemp : getElementsOnTable(table.getID())) {
                if (semTElemtemp.equals(semTElem)) {
                    insert2 = false;
                    break;
                }
            }
            LOG.info("insert2: " + insert);
            if (insert2) {
                ok = addElementinWardrobe(semTElem, table);
            }
        }
        return ok;
    }

    class SemTElemMapper<T> implements RowMapper {

        @Override
        public SemTElem mapRow(ResultSet rs, int rowNum) throws SQLException {
            SemTElem c = new SemTElem();
            c.setUrl(rs.getString("URL"));
            c.setName(rs.getString("NAME"));
            return c;
        }
    }

    private static final Logger LOG = Logger.getLogger(SemTElemRepository.class.getName());

}
