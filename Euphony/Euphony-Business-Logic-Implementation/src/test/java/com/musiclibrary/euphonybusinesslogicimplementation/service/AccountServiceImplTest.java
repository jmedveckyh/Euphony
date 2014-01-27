package com.musiclibrary.euphonybusinesslogicimplementation.service;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.services.AccountService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.AccountDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.AccountDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.services.impl.AccountServiceImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;

/**
 * Tests for Account service layer.
 * 
 * @author Tomas Smetanka #396209
 */
public class AccountServiceImplTest {

    private AccountService accountService;
    private AccountDAO accountDAO;

    @Before
    public void setUp() {
        accountService = new AccountServiceImpl();
        accountDAO = mock(AccountDAOImpl.class);
        ((AccountServiceImpl) accountService).setDAO(accountDAO);
    }

    private void assertDeepEquals(AccountDTO expected, AccountDTO actual) {
        assertEquals(expected.getId(), actual.getId());
    }
    
    /**
     * Test for register.
     */
    @Test
    public void testRegisterCorrectAccount() {
        AccountDTO accountTemp = new AccountDTO();
        //accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("verysecretpassword369");
        accountTemp.setUsername("Tomtom369");

        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.register(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
    }
    
    @Test(expected = DataAccessException.class)
    public void testRegisterAccountNullUsername() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("verysecretpassword369");
        accountTemp.setUsername(null);

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.register(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testRegisterAccountNullPassword() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword(null);
        accountTemp.setUsername("ATribeCalledRed");

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.register(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testRegisterAccountEmptyUsername() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("verysecretpassword369");
        accountTemp.setUsername("");

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.register(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testRegisterAccountEmptyPassword() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("");
        accountTemp.setUsername("Suede");

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.register(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testRegisterAccountShortPassword() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("shortpw"); // minimum is 8 chars
        accountTemp.setUsername("Suede");

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.register(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test
    public void testRegisterAccount8CharsInPassword() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("shortpwd"); // minimum is 8 chars
        accountTemp.setUsername("Suede");

        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.register(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
    }
    
    @Test(expected = DataAccessException.class)
    public void testRegisterNullAccount() {
        doThrow(new DataAccessException("") {}).when(accountDAO).create(null);
        accountService.register(null);
        verify(accountDAO, times(1)).create(null);
        verifyNoMoreInteractions(accountDAO);
    }
    
    /**
     * Tests for login.
     */
    @Test
    public void testLoginCorrectAccount() {
        String usernameTemp = "Suede";
        String passwordTemp = "shortpwd";

        doReturn(null).when(accountDAO).getByUsername(usernameTemp);
        accountService.login(usernameTemp, passwordTemp);
        verify(accountDAO, times(1)).getByUsername(usernameTemp);
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testLoginEmptyUsername() {
        String usernameTemp = "";
        String passwordTemp = "shortpwd";

        doThrow(new DataAccessException("") {}).when(accountDAO).getByUsername(usernameTemp);
        accountService.login(usernameTemp, passwordTemp);
        verify(accountDAO, times(1)).getByUsername(usernameTemp);
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testLoginEmptyPassword() {
        String usernameTemp = "Tricky";
        String passwordTemp = "";

        doThrow(new DataAccessException("") {}).when(accountDAO).getByUsername(usernameTemp);
        accountService.login(usernameTemp, passwordTemp);
        verify(accountDAO, times(1)).getByUsername(usernameTemp);
        verifyNoMoreInteractions(accountDAO);
    }
    
    
}
