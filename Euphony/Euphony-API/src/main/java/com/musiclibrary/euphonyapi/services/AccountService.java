package com.musiclibrary.euphonyapi.services;

import com.musiclibrary.euphonyapi.dto.AccountDTO;

/**
 *
 * @author Brano
 */
public interface AccountService {
    
    AccountDTO register (AccountDTO account);

    AccountDTO login (String username, String password);
}
