package com.musiclibrary.euphony.facade;

import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.PlaylistDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.services.AlbumService;
import com.musiclibrary.euphony.services.PlaylistService;
import com.musiclibrary.euphony.services.SongService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        facade = (MusicFacade) ctx.getBean("musicFacadeImpl");
        
        facade.setPlaylistService(playlistService);
        facade.setAlbumService(albumService);

        facade = mock(MusicFacade.class);
        playlistService = mock(PlaylistService.class);
        albumService = mock(AlbumService.class);
        songService = mock(SongService.class);
        
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
        
        doNothing().when(playlistService).create(playlistTemp);
        playlistService.create(playlistTemp);
        verify(playlistService, times(1)).create(playlistTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        doNothing().when(facade).addSongToPlaylist(songTemp, playlistTemp);
        facade.addSongToPlaylist(songTemp, playlistTemp);
        verify(facade, times(1)).addSongToPlaylist(songTemp, playlistTemp);
        
        verifyNoMoreInteractions(playlistService);
        verifyNoMoreInteractions(songService);
        verifyNoMoreInteractions(facade);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddSongToPlaylistWhenTheSongIsAlreadyInPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        
        playlistService.create(playlistTemp);
        songService.create(songTemp);
        
        doNothing().when(facade).addSongToPlaylist(songTemp, playlistTemp);
        facade.addSongToPlaylist(songTemp, playlistTemp);
        verify(facade, times(1)).addSongToPlaylist(songTemp, playlistTemp);
        
        doThrow(new IllegalArgumentException()).when(facade).addSongToPlaylist(songTemp, playlistTemp);
        facade.addSongToPlaylist(songTemp, playlistTemp);
        verify(facade, times(1)).addSongToPlaylist(songTemp, playlistTemp);
        
        verifyNoMoreInteractions(facade);        
    }
    
    @Test
    public void testIsSongInPlaylistWhenSongIsNotInPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        
        playlistService.create(playlistTemp);
        songService.create(songTemp);
        
        doReturn(false).when(facade).isSongInPlaylist(songTemp, playlistTemp);
        facade.isSongInPlaylist(songTemp, playlistTemp);
        verify(facade, times(1)).isSongInPlaylist(songTemp, playlistTemp);
        
        verifyNoMoreInteractions(facade);  
    }
    
    @Test
    public void testIsSongInPlaylistWhenSongIsAlreadyInPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        
        playlistService.create(playlistTemp);
        songService.create(songTemp);
        facade.addSongToPlaylist(songTemp, playlistTemp);
        verify(facade, times(1)).addSongToPlaylist(songTemp, playlistTemp);
        
        doReturn(true).when(facade).isSongInPlaylist(songTemp, playlistTemp);
        facade.isSongInPlaylist(songTemp, playlistTemp);
        verify(facade, times(1)).isSongInPlaylist(songTemp, playlistTemp);
        
        verifyNoMoreInteractions(facade);  
    }
    
    @Test
    public void testRemoveSongFromPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        
        playlistService.create(playlistTemp);
        songService.create(songTemp);
        
        doNothing().when(facade).addSongToPlaylist(songTemp, playlistTemp);
        facade.addSongToPlaylist(songTemp, playlistTemp);
        verify(facade, times(1)).addSongToPlaylist(songTemp, playlistTemp);
        
        doNothing().when(facade).removeSongFromPlaylist(songTemp, playlistTemp);
        facade.removeSongFromPlaylist(songTemp, playlistTemp);
        verify(facade, times(1)).removeSongFromPlaylist(songTemp, playlistTemp);
        
        verifyNoMoreInteractions(facade);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSongFromPlaylistWhenTheSongIsNotInPlaylist() {
        PlaylistDTO playlistTemp = new PlaylistDTO("My favourite songs");
        SongDTO songTemp = new SongDTO("The Fox");
        
        playlistService.create(playlistTemp);
        songService.create(songTemp);
        
        doThrow(new IllegalArgumentException()).when(facade).removeSongFromPlaylist(songTemp, playlistTemp);
        facade.removeSongFromPlaylist(songTemp, playlistTemp);
        verify(facade, times(1)).removeSongFromPlaylist(songTemp, playlistTemp);
        
        verifyNoMoreInteractions(facade);        
    }
    
    /*
     * Tests for albums.
     */
    
    @Test
    public void testAddSongToAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013");
        SongDTO songTemp = new SongDTO("The Fox");
        
        doNothing().when(albumService).create(albumTemp);
        albumService.create(albumTemp);
        verify(albumService, times(1)).create(albumTemp);
        
        doNothing().when(songService).create(songTemp);
        songService.create(songTemp);
        verify(songService, times(1)).create(songTemp);
        
        doNothing().when(facade).addSongToAlbum(songTemp, albumTemp);
        facade.addSongToAlbum(songTemp, albumTemp);
        verify(facade, times(1)).addSongToAlbum(songTemp, albumTemp);
        
        verifyNoMoreInteractions(playlistService);
        verifyNoMoreInteractions(songService);
        verifyNoMoreInteractions(facade);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddSongToAlbumWhenTheSongIsAlreadyInAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013");
        SongDTO songTemp = new SongDTO("The Fox");
        
        albumService.create(albumTemp);
        songService.create(songTemp);
        
        doNothing().when(facade).addSongToAlbum(songTemp, albumTemp);
        facade.addSongToAlbum(songTemp, albumTemp);
        verify(facade, times(1)).addSongToAlbum(songTemp, albumTemp);
        
        doThrow(new IllegalArgumentException()).when(facade).addSongToAlbum(songTemp, albumTemp);
        facade.addSongToAlbum(songTemp, albumTemp);
        verify(facade, times(1)).addSongToAlbum(songTemp, albumTemp);
        
        verifyNoMoreInteractions(facade);        
    }
    
    @Test
    public void testIsSongInAlbumWhenSongIsNotInAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013");
        SongDTO songTemp = new SongDTO("The Fox");
        
        albumService.create(albumTemp);
        songService.create(songTemp);
        
        doReturn(false).when(facade).isSongInAlbum(songTemp, albumTemp);
        facade.isSongInAlbum(songTemp, albumTemp);
        verify(facade, times(1)).isSongInAlbum(songTemp, albumTemp);
        
        verifyNoMoreInteractions(facade);  
    }
    
    @Test
    public void testIsSongInAlbumWhenSongIsAlreadyInAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013");
        SongDTO songTemp = new SongDTO("The Fox");
        
        albumService.create(albumTemp);
        songService.create(songTemp);
        facade.addSongToAlbum(songTemp, albumTemp);
        verify(facade, times(1)).addSongToAlbum(songTemp, albumTemp);
        
        doReturn(true).when(facade).isSongInAlbum(songTemp, albumTemp);
        facade.isSongInAlbum(songTemp, albumTemp);
        verify(facade, times(1)).isSongInAlbum(songTemp, albumTemp);
        
        verifyNoMoreInteractions(facade);  
    }
    
    @Test
    public void testRemoveSongFromAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013");
        SongDTO songTemp = new SongDTO("The Fox");
        
        albumService.create(albumTemp);
        songService.create(songTemp);
        
        doNothing().when(facade).addSongToAlbum(songTemp, albumTemp);
        facade.addSongToAlbum(songTemp, albumTemp);
        verify(facade, times(1)).addSongToAlbum(songTemp, albumTemp);
        
        doNothing().when(facade).removeSongFromAlbum(songTemp, albumTemp);
        facade.removeSongFromAlbum(songTemp, albumTemp);
        verify(facade, times(1)).removeSongFromAlbum(songTemp, albumTemp);
        
        verifyNoMoreInteractions(facade);        
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveSongFromAlbumWhenTheSongIsNotInAlbum() {
        AlbumDTO albumTemp = new AlbumDTO("Singles 2013");
        SongDTO songTemp = new SongDTO("The Fox");
        
        albumService.create(albumTemp);
        songService.create(songTemp);
        
        doThrow(new IllegalArgumentException()).when(facade).removeSongFromAlbum(songTemp, albumTemp);
        facade.removeSongFromAlbum(songTemp, albumTemp);
        verify(facade, times(1)).removeSongFromAlbum(songTemp, albumTemp);
        
        verifyNoMoreInteractions(facade);        
    }
    
}
