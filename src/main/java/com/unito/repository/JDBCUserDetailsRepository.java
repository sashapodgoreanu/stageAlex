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
    private static final String INSERT_USERDETAILS = "insert into USERDETAILS (ID, ID_TOKEN) values (?, ?)";
    private static final String SELECT_USERDETAILS = "select * from USERDETAILS (ID, ID_TOKEN) values (?, ?)";
    @Autowired
    public JDBCUserDetailsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetails save(UserDetails userDetails) {
        if (userDetails== null) 
            throw new NullPointerException();
        jdbcTemplate.update(INSERT_USERDETAILS,userDetails.getId(),userDetails.getIdtoken());
        return null;
    }

    @Override
    public UserDetails find(long id) {
        UserDetails userDetails = (UserDetails) this.jdbcTemplate.queryForObject(SELECT_USERDETAILS, (new UserDetailsMapper()), id);
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
    
    class UserDetailsMapper implements RowMapper{

        @Override
        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserDetails userDetails = new UserDetails();
            userDetails.setId(rs.getString("ID"));
            userDetails.setIdtoken(rs.getString("ID_TOKEN"));
            return userDetails;
        }
        
    }
    
    

    
}
