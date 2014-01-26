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
    
    public void create (Account account);
 
    public Account get (Long id);
    
    public void update(Account account);
    
    public void delete(Account account);
    
    public Account getByUsername(String username);
}
