//package com.musiclibrary.euphonybusinesslogicimplementation.dao;
//
//import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.PlaylistDAOImpl;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
//import java.util.Map;
//import java.util.TreeMap;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.dao.DataAccessException;
//
///**
// * Unit tests for Playlist DAO implementation.
// * 
// * @author Tomas Smetanka #396209
// */
//public class PlaylistDAOImplTest {
//
//    private PlaylistDAO playlistDAOImpl;
//
//    @Before
//    public void setUp() {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testApplicationContext.xml");
//        playlistDAOImpl = (PlaylistDAO) applicationContext.getBean("playlistDAO");
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
//        playlistDAOImpl.create(playlistR);
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
//        Album albumTemp = new Album("The Fox");
//        Artist artistTemp = new Artist("Ylvis");
//        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
//        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
//        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
//        
//        Playlist playlistR = new Playlist("Funny", songs);
//        
//        playlistDAOImpl.create(playlistR);
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
//        playlistDAOImpl.create(null);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testCreatePlaylistWithNoName() {
//        Playlist playlistR = new Playlist();
//        
//        playlistDAOImpl.create(playlistR);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testCreatePlaylistWithEmptyName() {
//        Playlist playlistR = new Playlist("");
//        
//        playlistDAOImpl.create(playlistR);
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
//        playlistDAOImpl.create(playlistR);
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
//        playlistDAOImpl.update(playlistUpdated);
//        
//        assertDeepEquals(playlistUpdated, playlistDAOImpl.getById(playlistR.getId()));
//        
//    } 
//    
//    @Test(expected = DataAccessException.class)
//    public void testUpdateNullPlaylist() {
//        
//        playlistDAOImpl.update(null);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testUpdatePlaylistWithEmptyName() {
//        Playlist playlistR = new Playlist("");
//        
//        playlistDAOImpl.update(playlistR);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testUpdatePlaylistWithNoId() {
//        Playlist playlistR = new Playlist(null, "New");
//        
//        playlistDAOImpl.update(playlistR);
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
//        playlistDAOImpl.update(playlistR);
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
//        playlistDAOImpl.create(playlistR);
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
//        playlistDAOImpl.delete(playlistToDelete);
//        
//        
//        
//        if (playlistDAOImpl.getById(id) != null) {
//            throw new DataAccessException("Delete failed.") {};
//        }
//        
//    } 
//    
//    @Test(expected = DataAccessException.class)
//    public void testDeleteNullPlaylist() {
//        
//        playlistDAOImpl.delete(null);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testDeletePlaylistWithEmptyName() {
//        Playlist playlistR = new Playlist("");
//        
//        playlistDAOImpl.delete(playlistR);
//        
//        
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testDeletePlaylistWithNoId() {
//        Playlist playlistR = new Playlist(null, "New");
//        
//        playlistDAOImpl.delete(playlistR);
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
//        playlistDAOImpl.delete(playlistR);
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
//        playlistDAOImpl.create(playlistR);
//        
//        
//        
//        Long id = playlistR.getId();
//        assertNotNull(id);
//        
//        assertDeepEquals(playlistR, playlistDAOImpl.getById(id));
//    }
//    
//    @Test(expected = DataAccessException.class)
//    public void testGetByIdPlaylistWhithNoId() {
//        
//        playlistDAOImpl.getById(null);
//        
//        
//    }
//    
//}
