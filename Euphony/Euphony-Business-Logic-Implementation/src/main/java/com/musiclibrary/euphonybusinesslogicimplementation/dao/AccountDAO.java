package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Account;

/**
 *
 * @author Brano
 */
public interface AccountDAO {
    
    void create(Account account);
 
    void update(Account account);
    
    void delete(Account account);
    
    Account getByUsername(String username);
    
     Account getById(Long id);

}
