package com.musiclibrary.euphony.service;

import com.musiclibrary.euphony.dao.impl.PlaylistDAOImpl;
import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.ArtistDTO;
import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.dto.PlaylistDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.services.AlbumService;
import com.musiclibrary.euphony.services.ArtistService;
import com.musiclibrary.euphony.services.GenreService;
import com.musiclibrary.euphony.services.PlaylistService;
import com.musiclibrary.euphony.services.SongService;
import com.musiclibrary.euphony.services.impl.AlbumServiceImpl;
import com.musiclibrary.euphony.services.impl.ArtistServiceImpl;
import com.musiclibrary.euphony.services.impl.GenreServiceImpl;
import com.musiclibrary.euphony.services.impl.PlaylistServiceImpl;
import com.musiclibrary.euphony.services.impl.SongServiceImpl;
import com.musiclibrary.euphony.util.DTOMapper;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;


/**
 * Tests for Playlist service layer.
 * 
 * @author Tomas Smetanka #396209
 */
public class PlaylistServiceImplTest {

    private PlaylistService playlistService;
    private PlaylistDAOImpl playlistDAO;

    @Before
    public void setUp() {        
        playlistService = new PlaylistServiceImpl();        
        playlistDAO = mock(PlaylistDAOImpl.class);
        ((PlaylistServiceImpl) playlistService).setPlaylistDAO(playlistDAO);
    }
    
    private void assertDeepEquals(PlaylistDTO expected, PlaylistDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
    
    /*
     * Tests for create.
     */
    
    @Test
    public void testCreatePlaylistWithName() {    
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        
        doNothing().when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        verifyNoMoreInteractions(playlistDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreateNullPlaylist() {
        doThrow(new IllegalArgumentException()).when(playlistDAO).create(null);
        playlistService.create(null);
        verify(playlistDAO, times(1)).create(null);
        verifyNoMoreInteractions(playlistDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreatePlaylistWithNoName() {
        PlaylistDTO playlistTemp = new PlaylistDTO();
        
        doThrow(new IllegalArgumentException()).when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        verifyNoMoreInteractions(playlistDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreatePlaylistWithEmptyName() {
        PlaylistDTO playlistTemp = new PlaylistDTO("");
        
        doThrow(new IllegalArgumentException()).when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        verifyNoMoreInteractions(playlistDAO);
    }
    
    /*
     * Tests for update.
     */
    
    @Test
    public void tetsUpdatePlaylistWithIdAndName() {
        PlaylistDTO playlistTemp = new PlaylistDTO("Funny");
        PlaylistDTO playlistTempUpdated = new PlaylistDTO("Not Funny");
        
        // creates a new playlist
        doNothing().when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        
        // set id for updated playlist
        Long idTemp = playlistTemp.getId();
        playlistTempUpdated.setId(idTemp);
        
        // update playlist
        doNothing().when(playlistDAO).update(DTOMapper.toEntity(playlistTempUpdated));
        playlistService.update(playlistTempUpdated);
        verify(playlistDAO, times(1)).update(DTOMapper.toEntity(playlistTempUpdated));
        
        // check if updated playlists are equal
        when(playlistDAO.getById(idTemp)).thenReturn(DTOMapper.toEntity(playlistTempUpdated));
        assertDeepEquals(playlistTempUpdated, playlistService.getById(idTemp));
        verify(playlistDAO, times(1)).getById(idTemp);
        
        verifyNoMoreInteractions(playlistDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testUpdateNullPlaylist() {
       doThrow(new IllegalArgumentException()).when(playlistDAO).update(null);
       playlistService.update(null);
       verify(playlistDAO, times(1)).update(null);
       
       verifyNoMoreInteractions(playlistDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testUpdatePlaylistWithEmptyName() {
        PlaylistDTO playlistTemp = new PlaylistDTO("Not empty name");
        PlaylistDTO playlistTempUpdated = new PlaylistDTO("");
        
        doNothing().when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        
        playlistTempUpdated.setId(playlistTemp.getId());
        
        doThrow(new IllegalArgumentException()).when(playlistDAO).update(DTOMapper.toEntity(playlistTempUpdated));
        playlistService.update(playlistTempUpdated);
        verify(playlistDAO, times(1)).update(DTOMapper.toEntity(playlistTempUpdated));
        
        verifyNoMoreInteractions(playlistDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testUpdatePlaylistWithNoId() {
        PlaylistDTO playlistTemp = new PlaylistDTO("Not empty name");
        PlaylistDTO playlistTempUpdated = new PlaylistDTO("Still not empty name, but id is empty!");
        
        doNothing().when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        
        doThrow(new IllegalArgumentException()).when(playlistDAO).update(DTOMapper.toEntity(playlistTempUpdated));
        playlistService.update(playlistTempUpdated);
        verify(playlistDAO, times(1)).update(DTOMapper.toEntity(playlistTempUpdated));
        
        verifyNoMoreInteractions(playlistDAO);        
    }
    
    @Test(expected = DataAccessException.class)
    public void testUpdatePlaylistWhichIsNotInDatabase() {
        PlaylistDTO playlistTemp = new PlaylistDTO("Not empty name");        
        playlistTemp.setId(1L);

        doThrow(new IllegalArgumentException()).when(playlistDAO).update(DTOMapper.toEntity(playlistTemp));
        playlistService.update(playlistTemp);
        verify(playlistDAO, times(1)).update(DTOMapper.toEntity(playlistTemp));
        
        verifyNoMoreInteractions(playlistDAO);
    }
    
    /*
     * Tests for delete.
     */
    
    @Test
    public void testDeletePlaylistWithId() {
        PlaylistDTO playlistTemp = new PlaylistDTO("Funny");
        PlaylistDTO playlistTempToDelete = new PlaylistDTO("Not Funny");
        
        doNothing().when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        
        Long idTemp = playlistTemp.getId();
        playlistTempToDelete.setId(idTemp);
        
        doNothing().when(playlistDAO).delete(DTOMapper.toEntity(playlistTempToDelete));
        playlistService.delete(playlistTempToDelete);
        verify(playlistDAO, times(1)).delete(DTOMapper.toEntity(playlistTempToDelete));
        
        when(playlistDAO.getById(idTemp)).thenReturn(null);
        assertEquals(null, playlistService.getById(idTemp));
        verify(playlistDAO, times(1)).getById(idTemp);
        
        verifyNoMoreInteractions(playlistDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testDeleteNullPlaylist() {
        doThrow(new IllegalArgumentException()).when(playlistDAO).delete(null);
        playlistService.delete(null);
        verify(playlistDAO, times(1)).delete(null);

        verifyNoMoreInteractions(playlistDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testDeletePlaylistWithEmptyName() {
        PlaylistDTO playlistTemp = new PlaylistDTO("Not empty name");
        PlaylistDTO playlistTempUpdated = new PlaylistDTO("");
        
        doNothing().when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        
        playlistTempUpdated.setId(playlistTemp.getId());
        
        doThrow(new IllegalArgumentException()).when(playlistDAO).delete(DTOMapper.toEntity(playlistTempUpdated));
        playlistService.delete(playlistTempUpdated);
        verify(playlistDAO, times(1)).delete(DTOMapper.toEntity(playlistTempUpdated));
        
        verifyNoMoreInteractions(playlistDAO);
    }
    
    @Test(expected = DataAccessException.class)
    public void testDeletePlaylistWithNoId() {
        PlaylistDTO playlistTemp = new PlaylistDTO("Not empty name");
        PlaylistDTO playlistTempUpdated = new PlaylistDTO("Still not empty name, but id is empty!");
        
        doNothing().when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        
        doThrow(new IllegalArgumentException()).when(playlistDAO).delete(DTOMapper.toEntity(playlistTempUpdated));
        playlistService.delete(playlistTempUpdated);
        verify(playlistDAO, times(1)).delete(DTOMapper.toEntity(playlistTempUpdated));
        
        verifyNoMoreInteractions(playlistDAO);       
    }
    
    @Test(expected = DataAccessException.class)
    public void testDeletePlaylistWhichIsNotInDatabase() {
        PlaylistDTO playlistTemp = new PlaylistDTO("Not empty name");        
        playlistTemp.setId(1L);

        doThrow(new IllegalArgumentException()).when(playlistDAO).delete(DTOMapper.toEntity(playlistTemp));
        playlistService.delete(playlistTemp);
        verify(playlistDAO, times(1)).delete(DTOMapper.toEntity(playlistTemp));
        
        verifyNoMoreInteractions(playlistDAO);        
    }
    
    /*
     * Tests for getById.
     */
    
    @Test
    public void testGetByIdPlaylistWithClsAndId() {
        PlaylistDTO playlistTemp = new PlaylistDTO("Funny");
        
        doNothing().when(playlistDAO).create(DTOMapper.toEntity(playlistTemp));
        playlistService.create(playlistTemp);
        verify(playlistDAO, times(1)).create(DTOMapper.toEntity(playlistTemp));
        
        Long idTemp = playlistTemp.getId();
        
        when(playlistDAO.getById(idTemp)).thenReturn(DTOMapper.toEntity(playlistTemp));
        assertDeepEquals(playlistTemp, playlistService.getById(idTemp));
        verify(playlistDAO, times(1)).getById(idTemp);
        
        verifyNoMoreInteractions(playlistDAO);        
    }
    
    @Test
    public void testGetByIdPlaylistWhithNoId() {
        when(playlistDAO.getById(1L)).thenReturn(null);
        assertEquals(null, playlistService.getById(1L));
        verify(playlistDAO, times(1)).getById(1L);
        
        verifyNoMoreInteractions(playlistDAO);    
    }
    
    @Test(expected = DataAccessException.class)
    public void testGetByIdPlaylistWhithNotExistingId() {
        doThrow(new IllegalArgumentException()).when(playlistDAO).getById(null);
        playlistService.getById(null);
        verify(playlistDAO, times(1)).getById(null);
        
        verifyNoMoreInteractions(playlistDAO);    
    }
    
}
