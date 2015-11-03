/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.model.repository;

import com.unito.model.Table;
import com.unito.model.UserDetails.UserDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SashaAlexandru
 */
@Repository
public class TableRepository {

    private JdbcTemplate jdbcTemplate;
    private final String INSERT_TABLE = "insert into W_TABLE(NAME, OWNER) values(?,?)";
    private final String INSERT_USER_ON_TABLE = "insert into USER_W_TABLE(USERDETAILS_ID,W_TABLE_ID, OPENED) values(?,?,?)";
    private final String SELECT_ALL = "select wt.ID as ID, wt.NAME as NAME, wt.OWNER as OWNER, uwt.opened as OPENED\n"
            + "from W_TABLE wt join USER_W_TABLE uwt on(wt.id = uwt.W_TABLE_ID) \n"
            + "where uwt.USERDETAILS_ID = ?";
    private final String SELECT_ALL_OPENED = "select wt.ID as ID, wt.NAME as NAME, wt.OWNER as OWNER, uwt.opened as OPENED\n"
            + "from W_TABLE wt join USER_W_TABLE uwt on(wt.id = uwt.W_TABLE_ID) \n"
            + "where uwt.OPENED = TRUE and uwt.USERDETAILS_ID = ?";
    private final String UPDATE_OPENED_TABLE = "update USER_W_TABLE set opened = ? where USERDETAILS_ID = ? and W_TABLE_ID =?";
    private final String UPDATE_OBJECT_TABLE = "update semtelems_on_table set IN_WARDROBE = ? where URL = ? and ID_TABLE = ?";
    
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
        List<Table> retVal = jdbcTemplate.query(SELECT_ALL_OPENED, (new TableRowMapper<>()), userDetails.getId());
        return retVal;
    }

    public int saveTable(Table tableToSave) {
        System.out.println("saving");
        KeyHolder keyHolder = new GeneratedKeyHolder();
        System.out.println(keyHolder.getKey());
        //http://docs.spring.io/spring/docs/2.5.x/reference/jdbc.html#jdbc-auto-genereted-keys
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT_TABLE, new String[]{"id"});
                        ps.setString(1, tableToSave.getName());
                        ps.setString(2, tableToSave.getOwner());
                        return ps;
                    }
                },
                keyHolder);
        return keyHolder.getKey().intValue() >= 0 ? keyHolder.getKey().intValue() : -1;
    }

    public boolean addUserOnTable(Table wtable, UserDetails userDetails) {
        return jdbcTemplate.update(INSERT_USER_ON_TABLE, userDetails.getId(),wtable.getID(), wtable.isOpened()) == 1;
    }

    public boolean addUserOnTable(UserDetails userDetails, Table table) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean closeTable(UserDetails userDetails, Table closeTable) {
        return jdbcTemplate.update(UPDATE_OPENED_TABLE,false,userDetails.getId(),closeTable.getID()) == 1;
    }

    public boolean toWardrobe(String idObject, int openTableId) {
        return jdbcTemplate.update(UPDATE_OBJECT_TABLE, 1 ,idObject , openTableId) == 1;
    }
    
    public boolean toWorkingTable(String idObject, int openTableId) {
        return jdbcTemplate.update(UPDATE_OBJECT_TABLE, 0 ,idObject , openTableId) == 1;
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
