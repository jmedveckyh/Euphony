package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.PlaylistDAOImpl;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Playlist DAO implementation.
 * 
 * @author Tomas Smetanka #396209
 */
public class PlaylistDAOImplTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private PlaylistDAOImpl playlistDAOImpl;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        playlistDAOImpl = new PlaylistDAOImpl(em);
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
        Album albumTemp = new Album("The Fox");
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp));
        
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
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullPlaylist() {
        em.getTransaction().begin();
        playlistDAOImpl.create(null);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlaylistWithNoName() {
        Playlist playlistR = new Playlist();
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
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
        Album albumTemp = new Album("The Fox");
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp));
        
        Playlist playlistR = new Playlist("Funny", songs);
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
        
        Long id = playlistR.getId();
        assertNotNull(id);
        
        Map<Integer, Song> songsUpdated = new TreeMap<>();
        songsUpdated.put(1, new Song("Jan Egenland", 180, 1, "", genreTemp, albumTemp));
        Playlist playlistUpdated = new Playlist("Not funny at all", songsUpdated);
        playlistUpdated.setId(id);
        
        em.getTransaction().begin();
        playlistDAOImpl.update(playlistUpdated);
        em.getTransaction().commit();
        assertDeepEquals(playlistUpdated, playlistDAOImpl.getById(Playlist.class, playlistR.getId()));
        em.clear();
    } 
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateNullPlaylist() {
        em.getTransaction().begin();
        playlistDAOImpl.update(null);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdatePlaylistWithEmptyName() {
        Playlist playlistR = new Playlist("");
        em.getTransaction().begin();
        playlistDAOImpl.update(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdatePlaylistWithNoId() {
        Playlist playlistR = new Playlist(null, "New");
        em.getTransaction().begin();
        playlistDAOImpl.update(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdatePlaylistWhichIsNotInDatabase() {
        Map<Integer, Song> songs = new TreeMap<>();
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox");
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp));
        
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
        Album albumTemp = new Album("The Fox");
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp));
        
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
        
        if (playlistDAOImpl.getById(Playlist.class, id) != null) {
            throw new IllegalArgumentException("Delete failed.");
        }
        
    } 
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteNullPlaylist() {
        em.getTransaction().begin();
        playlistDAOImpl.delete(null);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeletePlaylistWithEmptyName() {
        Playlist playlistR = new Playlist("");
        em.getTransaction().begin();
        playlistDAOImpl.delete(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeletePlaylistWithNoId() {
        Playlist playlistR = new Playlist(null, "New");
        em.getTransaction().begin();
        playlistDAOImpl.delete(playlistR);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testDeletePlaylistWhichIsNotInDatabase() {
        Map<Integer, Song> songs = new TreeMap<>();
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox");
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp));
        
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
        Album albumTemp = new Album("The Fox");
        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp));
        
        Playlist playlistR = new Playlist("Funny", songs);
        em.getTransaction().begin();
        playlistDAOImpl.create(playlistR);
        em.getTransaction().commit();
        em.clear();
        
        Long id = playlistR.getId();
        assertNotNull(id);
        
        assertDeepEquals(playlistR, playlistDAOImpl.getById(Playlist.class, id));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdPlaylistWhithNoId() {
        em.getTransaction().begin();
        playlistDAOImpl.getById(Playlist.class, null);
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdPlaylistWhithNoCls() {
        em.getTransaction().begin();
        playlistDAOImpl.getById(null, new Long(1));
        em.getTransaction().commit();
        em.clear();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetByIdPlaylistWhithNotExistingCls() {        
        em.getTransaction().begin();
        playlistDAOImpl.getById(PlaylistDAOImpl.class, new Long(1));
        em.getTransaction().commit();
        em.clear();
    }
    
}
