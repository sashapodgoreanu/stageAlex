/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito;

import com.unito.model.Table;
import com.unito.model.UserDetails.UserDetails;
import java.util.List;

/**
 *
 * @author Alexandru Podgoreanu
 */
public interface UserDetailsRepository {

    long count();

    UserDetails save(UserDetails user);
    
    boolean update(UserDetails user);
    
    boolean exists(long id);

    UserDetails find(String id);

    UserDetails findByUsername(String username);

    List<UserDetails> findAll();
    
    List<UserDetails> getUsersOnTable(int id);
}
