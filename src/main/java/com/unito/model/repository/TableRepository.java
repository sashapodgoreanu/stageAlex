/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model.repository;

import com.unito.model.Table;
import com.unito.model.UserDetails.UserDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SashaAlexandru
 */
@Repository
public class TableRepository {

    
    private JdbcTemplate jdbcTemplate;
    private final String INSERT_TABLE = "insert into W_TABLE(NAME, OWNER) values(?,?)";
    private final String SELECT_ALL = "select wt.ID as ID, wt.NAME as NAME, wt.OWNER as OWNER, uwt.opened as OPENED\n"
            + "from W_TABLE wt join USER_W_TABLE uwt on(wt.id = uwt.W_TABLE_ID) \n"
            + "where uwt.USERDETAILS_ID = ?";
    private final String SELECT_ALL_OPENED = "select wt.ID as ID, wt.NAME as NAME, wt.OWNER as OWNER, uwt.opened as OPENED\n"
            + "from W_TABLE wt join USER_W_TABLE uwt on(wt.id = uwt.W_TABLE_ID) \n"
            + "where uwt.OPENED = TRUE and uwt.USERDETAILS_ID = ?";

    
    @Autowired
    public TableRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Table getTable(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Table> getAllTables(UserDetails userDetails) {
        List<Table> retVal = jdbcTemplate.query(SELECT_ALL, (new TableRowMapper<>()), userDetails.getId());
        return retVal;
    }

    public List<Table> getOpenedTables(UserDetails userDetails) {
        List<Table> retVal = jdbcTemplate.query(SELECT_ALL_OPENED, (new TableRowMapper<>()),userDetails.getId());
        return retVal;
    }

    public boolean saveTable(Table tableToSave) {
        return jdbcTemplate.update(INSERT_TABLE, tableToSave.getName(), tableToSave.getOwner()) == 1;
        //to do aggiungere una riga nella working table con gli utenti collegati
    }
    
    public boolean addUserOnTable(Table wtable, UserDetails userDetails){
        //todo
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean addUserOnTable(UserDetails userDetails, Table table) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    class TableRowMapper<T> implements RowMapper {

        @Override
        public Table mapRow(ResultSet rs, int rowNum) throws SQLException {
            Table c = new Table();
            c.setID(rs.getInt("ID"));
            c.setName(rs.getString("NAME"));
            c.setOwner(rs.getString("OWNER"));
            c.setOpened(rs.getBoolean("OPENED"));
            return c;
        }
    }
}
