package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.ArtistDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.GenreDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.PlaylistDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.joda.time.DateTime;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

/**
 * Unit tests for Playlist DAO implementation.
 * 
 * @author Tomas Smetanka #396209
 */
public class PlaylistDAOImplTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private PlaylistDAOImpl playlistDAOImpl;
    private ArtistDAOImpl artistDao;
    private GenreDAOImpl genreDao;
    private AlbumDAOImpl albumDao;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        playlistDAOImpl = new PlaylistDAOImpl(em);
        artistDao = new ArtistDAOImpl(em);
        genreDao = new GenreDAOImpl(em);
        albumDao = new AlbumDAOImpl(em);
    }
    
    private void assertDeepEquals(Playlist expected, Playlist actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
    
    /**
     * Tests for Create.
     */
    
    @Test
    public void testCreatePlaylistWithName() {    
        Playlist playlistR = new Playlist("My favourite songs");
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
        
        Long id = playlistR.getId();
        assertNotNull(id);
        Playlist playlistE = new Playlist(id, "My favourite songs");
        assertDeepEquals(playlistR, playlistE);
    }
    
    @Test
    public void testCreatePlaylistWithNameAndSongs() {
        Map<Integer, Song> songs = new TreeMap<>();
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), "nehehe");
        Artist artistTemp = new Artist("Ylvis");
        
        em.getTransaction().begin();
        genreDao.create(genreTemp);
        albumDao.create(albumTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();
        
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
        
        Playlist playlistR = new Playlist("Funny", songs);
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
        
        Long id = playlistR.getId();
        assertNotNull(id);
        Playlist playlistE = new Playlist(id, "Funny", songs);
        assertDeepEquals(playlistR, playlistE);
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreateNullPlaylist() {
        em.getTransaction().begin();
        playlistDAOImpl.create(null);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreatePlaylistWithNoName() {
        Playlist playlistR = new Playlist();
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = DataAccessException.class)
    public void testCreatePlaylistWithEmptyName() {
        Playlist playlistR = new Playlist("");
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    /**
     * Tests for Update.
     */
      
    @Test
    public void testUpdatePlaylistWithIdAndName() {
        Map<Integer, Song> songs = new TreeMap<>();
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), "nehehe");
        Artist artistTemp = new Artist("Ylvis");
        
        em.getTransaction().begin();
        genreDao.create(genreTemp);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        albumDao.create(albumTemp);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        artistDao.create(artistTemp);
        em.getTransaction().commit();
        
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
        Playlist playlistR = new Playlist("Funny", songs);
        
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
        
        Long id = playlistR.getId();
        assertNotNull(id);
        
        Map<Integer, Song> songsUpdated = new TreeMap<>();
        songsUpdated.put(1, new Song("Jan Egenland", 180, 1, "", genreTemp, albumTemp, artistTemp));
        Playlist playlistUpdated = new Playlist("Not funny at all", songsUpdated);
        playlistUpdated.setId(id);
        
        em.getTransaction().begin();
        playlistDAOImpl.update(playlistUpdated);
        em.getTransaction().commit();
        assertDeepEquals(playlistUpdated, playlistDAOImpl.getById(playlistR.getId()));
        em.clear();
    } 
    
    @Test(expected = DataAccessException.class)
    public void testUpdateNullPlaylist() {
        em.getTransaction().begin();
        playlistDAOImpl.update(null);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = DataAccessException.class)
    public void testUpdatePlaylistWithEmptyName() {
        Playlist playlistR = new Playlist("");
        em.getTransaction().begin();
        playlistDAOImpl.update(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = DataAccessException.class)
    public void testUpdatePlaylistWithNoId() {
        Playlist playlistR = new Playlist(null, "New");
        em.getTransaction().begin();
        playlistDAOImpl.update(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = DataAccessException.class)
    public void testUpdatePlaylistWhichIsNotInDatabase() {
        Map<Integer, Song> songs = new TreeMap<>();
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox");
        Artist artistTemp = new Artist("Ylvis");
        
        em.getTransaction().begin();
        genreDao.create(genreTemp);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        albumDao.create(albumTemp);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        artistDao.create(artistTemp);
        em.getTransaction().commit();
        
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
        
        Playlist playlistR = new Playlist("Funny", songs);
        em.getTransaction().begin();
        playlistDAOImpl.update(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    /**
     * Tests for Delete.
     */
    
    @Test
    public void testDeletePlaylistWithIdAndName() {
        Map<Integer, Song> songs = new TreeMap<>();
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), "nehehe");
        Artist artistTemp = new Artist("Ylvis");
        
        em.getTransaction().begin();
        genreDao.create(genreTemp);
        albumDao.create(albumTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();
        
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
        
        Playlist playlistR = new Playlist("Funny", songs);
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
        
        Long id = playlistR.getId();
        assertNotNull(id);
        
        Playlist playlistToDelete = new Playlist("Funny", songs);
        playlistToDelete.setId(id);
        
        em.getTransaction().begin();
        playlistDAOImpl.delete(playlistToDelete);
        em.getTransaction().commit();
        em.clear();
        
        if (playlistDAOImpl.getById(id) != null) {
            fail("Delete failed.");
        }
        
    } 
    
    @Test(expected = DataAccessException.class)
    public void testDeleteNullPlaylist() {
        em.getTransaction().begin();
        playlistDAOImpl.delete(null);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = DataAccessException.class)
    public void testDeletePlaylistWithEmptyName() {
        Playlist playlistR = new Playlist("");
        em.getTransaction().begin();
        playlistDAOImpl.delete(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = DataAccessException.class)
    public void testDeletePlaylistWithNoId() {
        Playlist playlistR = new Playlist(null, "New");
        em.getTransaction().begin();
        playlistDAOImpl.delete(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = DataAccessException.class)
    public void testDeletePlaylistWhichIsNotInDatabase() {
        Map<Integer, Song> songs = new TreeMap<>();
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox");
        Artist artistTemp = new Artist("Ylvis");
        
        em.getTransaction().begin();
        genreDao.create(genreTemp);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        albumDao.create(albumTemp);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        artistDao.create(artistTemp);
        em.getTransaction().commit();
        
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
        
        Playlist playlistR = new Playlist("Funny", songs);
        em.getTransaction().begin();
        playlistDAOImpl.delete(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    /**
     * Tests for GetById.
     */
    
    @Test
    public void testGetByIdPlaylistWithClsAndId() {
        Map<Integer, Song> songs = new TreeMap<>();
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), "nehehe");
        Artist artistTemp = new Artist("Ylvis");
        
        em.getTransaction().begin();
        genreDao.create(genreTemp);
        albumDao.create(albumTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();
        
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));
        
        Playlist playlistR = new Playlist("Funny", songs);
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
        
        Long id = playlistR.getId();
        assertNotNull(id);
        
        assertDeepEquals(playlistR, playlistDAOImpl.getById(id));
    }
    
    @Test(expected = DataAccessException.class)
    public void testGetByIdPlaylistWhithNoId() {
        em.getTransaction().begin();
        playlistDAOImpl.getById(null);
        em.getTransaction().commit();
        em.clear();
    }
    
}
