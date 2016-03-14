/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.repository;

import com.unito.UserDetailsRepository;
import com.unito.model.Table;
import com.unito.model.UserDetails.UserDetails;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alexandru Podgoreanu
 */
@Repository
public class UserDetailsRepositoryJDBC implements UserDetailsRepository {
    
    private JdbcTemplate jdbcTemplate;
    
    private final String INSERT_USERDETAILS = "insert into USERDETAILS (ID, ID_TOKEN, ACCESS_TOKEN) values (?, ?, ?)";
    private final String SELECT_USERDETAILS = "select * from USERDETAILS where ID =?";
    private final String UPDATE_USERDETAILS = "update USERDETAILS set ID_TOKEN = ?, ACCESS_TOKEN = ? where ID = ?";
    private final String SELECT_ALL_USER_ON_TABLE ="select u.ID as ID, u.ID_TOKEN as ID_TOKEN, u.ACCESS_TOKEN as ACCESS_TOKEN  from USERDETAILS u join USER_W_TABLE uwt on (u.id = uwt.userdetails_id) "
            + "                               where uwt.w_table_id = ?";

    @Autowired
    public UserDetailsRepositoryJDBC(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        //SELECT_USERDETAILS = env.getProperty("select");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetails save(UserDetails userDetails) {
        LOG.info(INSERT_USERDETAILS);
        if (userDetails== null) 
            throw new NullPointerException();
        jdbcTemplate.update(INSERT_USERDETAILS,userDetails.getId(),userDetails.getIdtoken(), userDetails.getAccesstoken());
        return null;
    }

    @Override
    public UserDetails find(String id) {
        LOG.info(SELECT_USERDETAILS);
        if (id == null) throw new NullPointerException("find: id of userdetails is null");
        UserDetails userDetails = null;
        try {
            userDetails = (UserDetails) jdbcTemplate.queryForObject(SELECT_USERDETAILS, (new UserDetailsMapper()), id);
        } catch (EmptyResultDataAccessException e) {
        }
        //jdbcTemplate.queryForMap(SELECT_USERDETAILS, id);
        LOG.info(userDetails != null? "find: selected from db: "+userDetails.toString(): "NULL");
        return userDetails;
    }

    @Override
    public UserDetails findByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserDetails> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(long id) {
       /* Map<String,Object> map =  jdbcTemplate.queryForObject(SELECT_USERDETAILS,id);
        String id_d = map.get("ID");
        this:: String a;*/
        return false;
    }

    @Override
    public boolean update(UserDetails user) {
        LOG.info("update user");
        if (user == null)
            throw new NullPointerException("user is null");
        return jdbcTemplate.update(this.UPDATE_USERDETAILS,user.getIdtoken(),user.getAccesstoken(),user.getId()) != 0;
    }

    @Override
    public List<UserDetails> getUsersOnTable(int tableId) {
        LOG.info("Getting users on table "+tableId);
        List<UserDetails> retVal = jdbcTemplate.query(SELECT_ALL_USER_ON_TABLE, (new UserDetailsMapper<>()), tableId);
        return retVal;
    }
    
    class UserDetailsMapper<T> implements RowMapper{

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDetails userDetails = new UserDetails();
            userDetails.setId(rs.getString("ID"));
            userDetails.setIdtoken(rs.getString("ID_TOKEN"));
            userDetails.setAccesstoken(rs.getString("ACCESS_TOKEN"));
            return userDetails;
        }
    }
   
    private static final Logger LOG = Logger.getLogger(UserDetailsRepositoryJDBC.class.getName());
}
