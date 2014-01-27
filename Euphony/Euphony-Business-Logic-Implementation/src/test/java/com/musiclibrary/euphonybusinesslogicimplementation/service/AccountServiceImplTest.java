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
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getIsAdmin(), actual.getIsAdmin());
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
    
    /**
     * Test for create.
     */
    @Test
    public void testCreateCorrectAccount() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("verysecretpassword369");
        accountTemp.setUsername("Tomtom369");

        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreateAccountNullUsername() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("verysecretpassword369");
        accountTemp.setUsername(null);

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreateAccountNullPassword() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword(null);
        accountTemp.setUsername("ATribeCalledRed");

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreateAccountEmptyUsername() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("verysecretpassword369");
        accountTemp.setUsername("");

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreateAccountEmptyPassword() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("");
        accountTemp.setUsername("Suede");

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCerateAccountShortPassword() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("shortpw"); // minimum is 8 chars
        accountTemp.setUsername("Suede");

        doThrow(new DataAccessException("") {}).when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test
    public void testCreateAccount8CharsInPassword() {
        AccountDTO accountTemp = new AccountDTO();
        accountTemp.setId(Long.MIN_VALUE);
        accountTemp.setIsAdmin(false);
        accountTemp.setPassword("shortpwd"); // minimum is 8 chars
        accountTemp.setUsername("Suede");

        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
    }
   
    @Test(expected = DataAccessException.class)
    public void testCreateNullAccount() {
        doThrow(new DataAccessException("") {}).when(accountDAO).create(null);
        accountService.create(null);
        verify(accountDAO, times(1)).create(null);
        verifyNoMoreInteractions(accountDAO);
    }
    
    /**
     * Tests for update.
     */
    @Test
    public void tetsUpdateCorrectAccount() {
        AccountDTO accountTemp = new AccountDTO(Long.MIN_VALUE, "Tomasko", "tomaskotajneheslo", false, null);
        AccountDTO accountTempUpdated = new AccountDTO(Long.MIN_VALUE, "Tomas", "tomaskotajneheslo", false, null);

        // creates a new account
        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));

        // set id for updated account
        Long idTemp = accountTemp.getId();
        accountTempUpdated.setId(idTemp);

        // update account
        doNothing().when(accountDAO).update(DTOMapper.toEntity(accountTempUpdated));
        accountService.update(accountTempUpdated);
        verify(accountDAO, times(1)).update(DTOMapper.toEntity(accountTempUpdated));

        verifyNoMoreInteractions(accountDAO);
    }
    
    
    @Test(expected = DataAccessException.class)
    public void testUpdateNullAccount() {
        doThrow(new DataAccessException("") {}).when(accountDAO).update(null);
        accountService.update(null);
        verify(accountDAO, times(1)).update(null);

        verifyNoMoreInteractions(accountDAO);
    }
    
    /**
     * Tests for delete.
     */
    @Test
    public void testDeleteAccountWithId() {
        AccountDTO accountTemp = new AccountDTO(Long.MIN_VALUE, "Tomasko", "tomaskotajneheslo", false, null);
        AccountDTO accountTempToDelete = new AccountDTO(null, "Tomas", "tomaskotajneheslo", false, null);

        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));

        Long idTemp = accountTemp.getId();
        accountTempToDelete.setId(idTemp);

        doNothing().when(accountDAO).delete(DTOMapper.toEntity(accountTempToDelete));
        accountService.delete(accountTempToDelete);
        verify(accountDAO, times(1)).delete(DTOMapper.toEntity(accountTempToDelete));

        when(accountDAO.getByUsername("Tomasko")).thenReturn(null);
        assertEquals(null, accountService.getByUsername("Tomasko"));
        verify(accountDAO, times(1)).getByUsername("Tomasko");

        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testDeleteNullAccount() {
        doThrow(new DataAccessException("") {}).when(accountDAO).delete(null);
        accountService.delete(null);
        verify(accountDAO, times(1)).delete(null);

        verifyNoMoreInteractions(accountDAO);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteAccountWithEmptyName() {
        AccountDTO accountTemp = new AccountDTO(Long.MIN_VALUE, "Tomasko", "tomaskotajneheslo", false, null);
        AccountDTO accountTempUpdated = new AccountDTO(Long.MIN_VALUE, "", "tomaskotajneheslo", false, null);

        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));

        accountTempUpdated.setId(accountTemp.getId());

        doThrow(new DataAccessException("") {
        }).when(accountDAO).delete(DTOMapper.toEntity(accountTempUpdated));
        accountService.delete(accountTempUpdated);
        verify(accountDAO, times(1)).delete(DTOMapper.toEntity(accountTempUpdated));

        verifyNoMoreInteractions(accountDAO);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteAccountWithNoId() {
        AccountDTO accountTemp = new AccountDTO(Long.MIN_VALUE, "Tomasko", "tomaskotajneheslo", false, null);
        AccountDTO accountTempUpdated = new AccountDTO(null, "Tomasko", "tomaskotajneheslo", false, null);

        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));

        doThrow(new DataAccessException("") {}).when(accountDAO).delete(DTOMapper.toEntity(accountTempUpdated));
        accountService.delete(accountTempUpdated);
        verify(accountDAO, times(1)).delete(DTOMapper.toEntity(accountTempUpdated));

        verifyNoMoreInteractions(accountDAO);
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteAccountWhichIsNotInDatabase() {
        AccountDTO accountTemp = new AccountDTO(null, "Tomasko", "tomaskotajneheslo", false, null);
        accountTemp.setId(1L);

        doThrow(new DataAccessException("") {}).when(accountDAO).delete(DTOMapper.toEntity(accountTemp));
        accountService.delete(accountTemp);
        verify(accountDAO, times(1)).delete(DTOMapper.toEntity(accountTemp));

        verifyNoMoreInteractions(accountDAO);
    }
    
    /**
     * Tests for getByUsername.
     */
    @Test
    public void testGetByUsernameAccountWithClsAndId() {
        AccountDTO accountTemp = new AccountDTO(null, "Tomasko", "tomaskotajneheslo", false, null);
        
        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));

        when(accountDAO.getByUsername("Tomasko")).thenReturn(DTOMapper.toEntity(accountTemp));
        assertDeepEquals(accountTemp, accountService.getByUsername("Tomasko"));
        verify(accountDAO, times(1)).getByUsername("Tomasko");

        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testGetByUsernameAccountWithNotExistingUsername() {
        AccountDTO accountTemp = new AccountDTO(null, "Tomasko", "tomaskotajneheslo", false, null);
        
        doNothing().when(accountDAO).create(DTOMapper.toEntity(accountTemp));
        accountService.create(accountTemp);
        verify(accountDAO, times(1)).create(DTOMapper.toEntity(accountTemp));
        
        doThrow(new DataAccessException("") {}).when(accountDAO).getByUsername("Tomas");
        accountService.getByUsername("Tomas");
        verify(accountDAO, times(1)).getByUsername("Tomas");
        
        verifyNoMoreInteractions(accountDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testGetByUsernameAccountWhithNullAccount() {
        doThrow(new DataAccessException("") {}).when(accountDAO).getByUsername(null);
        accountService.getByUsername(null);
        verify(accountDAO, times(1)).getByUsername(null);

        verifyNoMoreInteractions(accountDAO);
    }
    
}
