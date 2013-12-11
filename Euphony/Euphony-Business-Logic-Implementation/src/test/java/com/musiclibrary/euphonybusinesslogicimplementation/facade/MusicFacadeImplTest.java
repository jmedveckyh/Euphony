package com.musiclibrary.euphonybusinesslogicimplementation.facade;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
import com.musiclibrary.euphonybusinesslogicimplementation.facade.impl.MusicFacadeImpl;
import java.util.ArrayList;
import org.joda.time.DateTime;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 * Tests for Music facade layer implementation.
 * 
 * @author Tomas Smetanka #396209
 */
public class MusicFacadeImplTest {

    private MusicFacade facade;
    
    private PlaylistService playlistService;
    private AlbumService albumService;
    private SongService songService;    
    
    @Before
    public void setUp() {   
        facade = new MusicFacadeImpl();
        
//        facade = mock(MusicFacade.class);
        playlistService = mock(PlaylistService.class);
        facade.setPlaylistService(playlistService);
        
        albumService = mock(AlbumService.class);
        facade.setAlbumService(albumService);
        
        songService = mock(SongService.class);
        facade.setSongService(songService);
    }   
    
    private void assertDeepEquals(PlaylistDTO expected, PlaylistDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
    
    /*
     * Tests for playlists.
     */
    
    @Test
    public void testAddSongToPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        playlistTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(playlistService).create(playlistTemp);
        playlistService.create(playlistTemp);
        verify(playlistService, times(1)).create(playlistTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        facade.addSongToPlaylist(songTemp, playlistTemp);
        
        verifyNoMoreInteractions(songService);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddSongToPlaylistWhenTheSongIsAlreadyInPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        playlistTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(playlistService).create(playlistTemp);
        playlistService.create(playlistTemp);
        verify(playlistService, times(1)).create(playlistTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        
        facade.addSongToPlaylist(songTemp, playlistTemp);
        // second inserting throws an exception
        facade.addSongToPlaylist(songTemp, playlistTemp);
        
        verifyNoMoreInteractions(playlistService);        
        verifyNoMoreInteractions(songService);        
    }
    
    @Test
    public void testIsSongInPlaylistWhenSongIsNotInPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        playlistTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(playlistService).create(playlistTemp);
        playlistService.create(playlistTemp);
        verify(playlistService, times(1)).create(playlistTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        assertFalse(facade.isSongInPlaylist(songTemp, playlistTemp));
        
        verifyNoMoreInteractions(playlistService);        
        verifyNoMoreInteractions(songService);   
    }
    
    @Test
    public void testIsSongInPlaylistWhenSongIsAlreadyInPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        playlistTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(playlistService).create(playlistTemp);
        playlistService.create(playlistTemp);
        verify(playlistService, times(1)).create(playlistTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        facade.addSongToPlaylist(songTemp, playlistTemp);
        assertTrue(facade.isSongInPlaylist(songTemp, playlistTemp));
               
        verifyNoMoreInteractions(songService);  
    }
    
    @Test
    public void testRemoveSongFromPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        playlistTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(playlistService).create(playlistTemp);
        playlistService.create(playlistTemp);
        verify(playlistService, times(1)).create(playlistTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        facade.addSongToPlaylist(songTemp, playlistTemp);
        facade.removeSongFromPlaylist(songTemp, playlistTemp);
              
        verifyNoMoreInteractions(songService);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSongFromPlaylistWhenTheSongIsNotInPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        playlistTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(playlistService).create(playlistTemp);
        playlistService.create(playlistTemp);
        verify(playlistService, times(1)).create(playlistTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        facade.removeSongFromPlaylist(songTemp, playlistTemp);
        
        verifyNoMoreInteractions(playlistService);        
        verifyNoMoreInteractions(songService);       
    }
    
    /*
     * Tests for albums.
     */
    
    @Test
    public void testAddSongToAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013", "cover1.jpg", new DateTime(2012,1,1,0,0), 
                new ArrayList<SongDTO>(), "My humble comment", new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        SongDTO songTemp = new SongDTO("The Fox");
        albumTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(albumService).create(albumTemp);
        albumService.create(albumTemp);
        verify(albumService, times(1)).create(albumTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        facade.addSongToAlbum(songTemp, albumTemp);
        
        verifyNoMoreInteractions(songService);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddSongToAlbumWhenTheSongIsAlreadyInAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013", "cover1.jpg", new DateTime(2012,1,1,0,0), 
                new ArrayList<SongDTO>(), "My humble comment", new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        SongDTO songTemp = new SongDTO("The Fox");
        albumTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(albumService).create(albumTemp);
        albumService.create(albumTemp);
        verify(albumService, times(1)).create(albumTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        facade.addSongToAlbum(songTemp, albumTemp);
        facade.addSongToAlbum(songTemp, albumTemp);
        
        verifyNoMoreInteractions(albumService);        
        verifyNoMoreInteractions(songService);         
    }
    
    @Test
    public void testIsSongInAlbumWhenSongIsNotInAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013", "cover1.jpg", new DateTime(2012,1,1,0,0), 
                new ArrayList<SongDTO>(), "My humble comment", new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        SongDTO songTemp = new SongDTO("The Fox");
        albumTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(albumService).create(albumTemp);
        albumService.create(albumTemp);
        verify(albumService, times(1)).create(albumTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        assertFalse(facade.isSongInAlbum(songTemp, albumTemp));
        
        verifyNoMoreInteractions(albumService);        
        verifyNoMoreInteractions(songService);  
    }
    
    @Test
    public void testIsSongInAlbumWhenSongIsAlreadyInAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013", "cover1.jpg", new DateTime(2012,1,1,0,0), 
                new ArrayList<SongDTO>(), "My humble comment", new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        SongDTO songTemp = new SongDTO("The Fox");
        albumTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(albumService).create(albumTemp);
        albumService.create(albumTemp);
        verify(albumService, times(1)).create(albumTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        facade.addSongToAlbum(songTemp, albumTemp);
        assertTrue(facade.isSongInAlbum(songTemp, albumTemp));
              
        verifyNoMoreInteractions(songService);    
    }
    
    @Test
    public void testRemoveSongFromAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013", "cover1.jpg", new DateTime(2012,1,1,0,0), 
                new ArrayList<SongDTO>(), "My humble comment", new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        SongDTO songTemp = new SongDTO("The Fox");
        albumTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(albumService).create(albumTemp);
        albumService.create(albumTemp);
        verify(albumService, times(1)).create(albumTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        facade.addSongToAlbum(songTemp, albumTemp);
        facade.removeSongFromAlbum(songTemp, albumTemp);
            
        verifyNoMoreInteractions(songService);      
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSongFromAlbumWhenTheSongIsNotInAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013", "cover1.jpg", new DateTime(2012,1,1,0,0), 
                new ArrayList<SongDTO>(), "My humble comment", new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        SongDTO songTemp = new SongDTO("The Fox");
        albumTemp.setId(1L);
        songTemp.setId(1L);
        
        doNothing().when(albumService).create(albumTemp);
        albumService.create(albumTemp);
        verify(albumService, times(1)).create(albumTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        facade.removeSongFromAlbum(songTemp, albumTemp);
        
        verifyNoMoreInteractions(albumService);        
        verifyNoMoreInteractions(songService);       
    }
    
}
