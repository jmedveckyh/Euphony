//package com.musiclibrary.euphonybusinesslogicimplementation.dao;
//
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.TreeMap;
//import org.joda.time.DateTime;
//import static org.junit.Assert.*;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Unit tests for Playlist DAO implementation.
// * 
// * @author Tomas Smetanka #396209
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/testApplicationContext.xml" ) 
//@Transactional
//public class PlaylistDAOImplTest {
//
//    @Autowired
//    private SongDAO songDAO;
//    
//    @Autowired
//    private ArtistDAO artistDAO;
//    
//    @Autowired
//    private GenreDAO genreDAO;
//    
//    @Autowired
//    private AlbumDAO albumDAO;
//
//    public void setSongDAO(SongDAO songDAO) {
//        this.songDAO = songDAO;
//    }
//
//    public void setArtistDAO(ArtistDAO artistDAO) {
//        this.artistDAO = artistDAO;
//    }
//
//    public void setGenreDAO(GenreDAO genreDAO) {
//        this.genreDAO = genreDAO;
//    }
//
//    public void setAlbumDAO(AlbumDAO albumDAO) {
//        this.albumDAO = albumDAO;
//    }
//    
//    @Autowired
//    private PlaylistDAO playlistDAO;
//
//    public void setPlaylistDAO(PlaylistDAO playlistDAO) {
//        this.playlistDAO = playlistDAO;
//    }
//    
//    private void assertDeepEquals(Playlist expected, Playlist actual) {
//        assertEquals(expected.getId(), actual.getId());
//        assertEquals(expected.getName(), actual.getName());
//    }
//    
//    /**
//     * Tests for Create.
//     */
//    
//    @Test
//    public void testCreatePlaylistWithName() {    
//        Playlist playlistR = new Playlist("My favourite songs");
//        
//        playlistDAO.create(playlistR);
//        
//        
//        
//        Long id = playlistR.getId();
//        assertNotNull(id);
//        Playlist playlistE = new Playlist(id, "My favourite songs");
//        assertDeepEquals(playlistR, playlistE);
//    }
//    
//    @Test
//    public void testCreatePlaylistWithNameAndSongs() {
//        Map<Integer, Song> songs = new TreeMap<>();
//        Genre genreTemp = new Genre("Pop");
//        Album albumTemp = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        Artist artistTemp = new Artist("Ylvis");
//        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
//        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
//        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
//        
//        Playlist playlistR = new Playlist("Funny", songs);
//        
//        genreDAO.create(genreTemp);
//        albumDAO.create(albumTemp);
//        artistDAO.create(artistTemp);
//        playlistDAO.create(playlistR);
//        
//        
//        
//        Long id = playlistR.getId();
//        assertNotNull(id);
//        Playlist playlistE = new Playlist(id, "Funny", songs);
//        assertDeepEquals(playlistR, playlistE);
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testCreateNullPlaylist() {
//        
//        playlistDAO.create(null);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testCreatePlaylistWithNoName() {
//        Playlist playlistR = new Playlist();
//        
//        playlistDAO.create(playlistR);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testCreatePlaylistWithEmptyName() {
//        Playlist playlistR = new Playlist("");
//        
//        playlistDAO.create(playlistR);
//        
//        
//    }
//    
//    /**
//     * Tests for Update.
//     */
//      
//    @Test
//    public void testUpdatePlaylistWithIdAndName() {
//        Map<Integer, Song> songs = new TreeMap<>();
//        Genre genreTemp = new Genre("Pop");
//        Album albumTemp = new Album("The Fox");
//        Artist artistTemp = new Artist("Ylvis");
//        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
//        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
//        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
//        
//        Playlist playlistR = new Playlist("Funny", songs);
//        
//        playlistDAO.create(playlistR);
//        
//        
//        
//        Long id = playlistR.getId();
//        assertNotNull(id);
//        
//        Map<Integer, Song> songsUpdated = new TreeMap<>();
//        songsUpdated.put(1, new Song("Jan Egenland", 180, 1, "", genreTemp, albumTemp, artistTemp));
//        Playlist playlistUpdated = new Playlist("Not funny at all", songsUpdated);
//        playlistUpdated.setId(id);
//        
//        
//        playlistDAO.update(playlistUpdated);
//        
//        assertDeepEquals(playlistUpdated, playlistDAO.getById(playlistR.getId()));
//        
//    } 
//    
//    @Test(expected = DataAccessException.class)
//    public void testUpdateNullPlaylist() {
//        
//        playlistDAO.update(null);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testUpdatePlaylistWithEmptyName() {
//        Playlist playlistR = new Playlist("");
//        
//        playlistDAO.update(playlistR);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testUpdatePlaylistWithNoId() {
//        Playlist playlistR = new Playlist(null, "New");
//        
//        playlistDAO.update(playlistR);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testUpdatePlaylistWhichIsNotInDatabase() {
//        Map<Integer, Song> songs = new TreeMap<>();
//        Genre genreTemp = new Genre("Pop");
//        Album albumTemp = new Album("The Fox");
//        Artist artistTemp = new Artist("Ylvis");
//        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
//        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
//        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
//        
//        Playlist playlistR = new Playlist("Funny", songs);
//        
//        playlistDAO.update(playlistR);
//        
//        
//    }
//    
//    /**
//     * Tests for Delete.
//     */
//    
//    @Test
//    public void testDeletePlaylistWithIdAndName() {
//        Map<Integer, Song> songs = new TreeMap<>();
//        Genre genreTemp = new Genre("Pop");
//        Album albumTemp = new Album("The Fox");
//        Artist artistTemp = new Artist("Ylvis");
//        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
//        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
//        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
//        
//        Playlist playlistR = new Playlist("Funny", songs);
//        
//        playlistDAO.create(playlistR);
//        
//        
//        
//        Long id = playlistR.getId();
//        assertNotNull(id);
//        
//        Playlist playlistToDelete = new Playlist("Funny", songs);
//        playlistToDelete.setId(id);
//        
//        
//        playlistDAO.delete(playlistToDelete);
//        
//        
//        
//        if (playlistDAO.getById(id) != null) {
//            throw new DataAccessException("Delete failed.") {};
//        }
//        
//    } 
//    
//    @Test(expected = DataAccessException.class)
//    public void testDeleteNullPlaylist() {
//        
//        playlistDAO.delete(null);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testDeletePlaylistWithEmptyName() {
//        Playlist playlistR = new Playlist("");
//        
//        playlistDAO.delete(playlistR);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testDeletePlaylistWithNoId() {
//        Playlist playlistR = new Playlist(null, "New");
//        
//        playlistDAO.delete(playlistR);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testDeletePlaylistWhichIsNotInDatabase() {
//        Map<Integer, Song> songs = new TreeMap<>();
//        Genre genreTemp = new Genre("Pop");
//        Album albumTemp = new Album("The Fox");
//        Artist artistTemp = new Artist("Ylvis");
//        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
//        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
//        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
//        
//        Playlist playlistR = new Playlist("Funny", songs);
//        
//        playlistDAO.delete(playlistR);
//        
//        
//    }
//    
//    /**
//     * Tests for GetById.
//     */
//    
//    @Test
//    public void testGetByIdPlaylistWithClsAndId() {
//        Map<Integer, Song> songs = new TreeMap<>();
//        Genre genreTemp = new Genre("Pop");
//        Album albumTemp = new Album("The Fox");
//        Artist artistTemp = new Artist("Ylvis");
//        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
//        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
//        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
//        
//        Playlist playlistR = new Playlist("Funny", songs);
//        
//        playlistDAO.create(playlistR);
//        
//        
//        
//        Long id = playlistR.getId();
//        assertNotNull(id);
//        
//        assertDeepEquals(playlistR, playlistDAO.getById(id));
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testGetByIdPlaylistWhithNoId() {
//        
//        playlistDAO.getById(null);
//        
//        
//    }
//    
//}
