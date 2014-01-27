package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.services.AccountService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.AccountDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Account;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public void setDAO(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public AccountDTO register(AccountDTO acc) throws DataAccessException {

        if (acc == null) {
            throw new DataAccessException("Account is null.") {
            };
        }

        Account account = accountDAO.getByUsername(acc.getUsername());
        if (account != null) {
            return null;
        } else {
            accountDAO.create(DTOMapper.toEntity(acc));
            account = accountDAO.getByUsername(acc.getUsername());
            return (DTOMapper.ToDTO(account));
        }
    }

    @Override
    public AccountDTO login(String username, String password) throws DataAccessException {

        if (username == null) {
            throw new DataAccessException("Account username is null") {
            };
        }

        if (password == null) {
            throw new DataAccessException("Account password is null") {
            };
        }

        if (username.isEmpty() || password.isEmpty()) {
            throw new DataAccessException("Account username or password is empty") {
            };
        }

        Account account = accountDAO.getByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            return DTOMapper.ToDTO(account);
        }
        return null;
    }

    @Override
    public AccountDTO get(Long id) {
        if (id == null) {
            throw new DataAccessException("id is null") {
            };
        }
        return DTOMapper.ToDTO(accountDAO.get(id));
    }

    @Override
    public AccountDTO getByUsername(String username) {
        if (username == null) {
            throw new DataAccessException("username is null") {
            };
        }
        return DTOMapper.ToDTO(accountDAO.getByUsername(username));
    }

    @Override
    public void update(AccountDTO account) {
        Account accountEntity = DTOMapper.toEntity(account);
        accountDAO.update(accountEntity);
        account = DTOMapper.ToDTO(accountEntity);
    }
}
