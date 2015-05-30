/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito.repository;

import com.unito.UserDetailsRepository;
import com.unito.model.UserDetails.UserDetails;
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
 * @author Alexandru Podgoreanu
 */
@Repository
public class JDBCUserDetailsRepository implements UserDetailsRepository {
    
    private JdbcTemplate jdbcTemplate;
    
    private String INSERT_USERDETAILS = "insert into USERDETAILS (ID, ID_TOKEN) values (?, ?)";
    private String SELECT_USERDETAILS = "select * from USERDETAILS where ID =?";
    private String UPDATE_USERDETAILS = "update USERDETAILS set ID_TOKEN = ? where ID = ?";

    @Autowired
    public JDBCUserDetailsRepository(JdbcTemplate jdbcTemplate) {
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
        jdbcTemplate.update(INSERT_USERDETAILS,userDetails.getId(),userDetails.getIdtoken());
        return null;
    }

    @Override
    public UserDetails find(String id) {
        LOG.info(SELECT_USERDETAILS);
        if (id == null) throw new NullPointerException("id of userdetails is null");
        UserDetails userDetails = (UserDetails) jdbcTemplate.queryForObject(SELECT_USERDETAILS, (new UserDetailsMapper()), id);
        LOG.info("selected from db "+userDetails.toString());
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
        return jdbcTemplate.update(this.UPDATE_USERDETAILS,user.getIdtoken(),user.getId()) != 0;
    }
    
    class UserDetailsMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDetails userDetails = new UserDetails();
            userDetails.setId(rs.getString("ID"));
            userDetails.setIdtoken(rs.getString("ID_TOKEN"));
            return userDetails;
        }
    }
    private static final Logger LOG = Logger.getLogger(JDBCUserDetailsRepository.class.getName());
    
    
}
