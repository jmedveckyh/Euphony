/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.services.AccountService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.AccountDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Account;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Brano
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public void setAccountDAO(AccountDAO dao) {
        this.accountDAO = dao;
    }

    @Override
    public AccountDTO register(AccountDTO acc) {
        
        if (acc == null) {
           throw new IllegalArgumentException("account is null");
        }
        
        if (acc.getIsAdmin() == null || acc.getPassword() == null || acc.getUsername() == null) {
            throw new IllegalArgumentException("account isAdmin or pass or username is null");
        }

        if (acc.getUsername().isEmpty() || acc.getPassword().isEmpty()) {
            throw new IllegalArgumentException("account username or password is empty");
        }

        Account account = accountDAO.getByUsername(acc.getUsername());
        if (account != null) {
            return null;
        } else {
            accountDAO.create(DTOMapper.accountDTOtoEntity(acc));
            account = accountDAO.getByUsername(acc.getUsername());
            return (DTOMapper.accountEntityToDTOAccount(account));
        }
    }

    @Override
    public AccountDTO login(String username, String password) {
        if (username == null || password == null) {
            throw new IllegalArgumentException("account username or password is nullt");
        }

        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("account username or password is empty");
        }

        Account account = accountDAO.getByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            return DTOMapper.accountEntityToDTOAccount(account);
        }
        return null;
    }
}
