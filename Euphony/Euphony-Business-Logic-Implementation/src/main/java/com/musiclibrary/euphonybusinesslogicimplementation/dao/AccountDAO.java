/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Account;

/**
 *
 * @author Brano
 */
public interface AccountDAO {
    
    void create (Account account);
 
    Account get (Long id);
    
    void update(Account account);
    
    void delete(Account account);
    
    Account getByUsername(String username);
}
