/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unito;

import com.unito.model.AccountDetails;
import java.util.List;

/**
 *
 * @author Alexandru Podgoreanu
 */
public interface AccountDetailsRep {

    long count();

    AccountDetails save(AccountDetails spitter);

    AccountDetails find(long id);

    AccountDetails findByUsername(String username);

    List<AccountDetails> findAll();
}
