package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.PlaylistDAOImpl;
import com.musiclibrary.euphony.entities.Playlist;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;

/**
 * Unit tests for Playlist DAO implementation.
 * 
 * @author Tomas Smetanka #396209
 */
public class PlaylistDAOImplTest extends TestCase {

    EntityManagerFactory emf;

    public PlaylistDAOImplTest(String name) {
        super(name);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.emf = Persistence.createEntityManagerFactory("testEuphonyPU");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCreatePlaylist() {
        
        EntityManager em = emf.createEntityManager();
        PlaylistDAOImpl playlistDAOImpl = new PlaylistDAOImpl();
    
        Playlist playlist = new Playlist("prvy");
        em.getTransaction().begin();
        playlistDAOImpl.create(playlist);
        em.getTransaction().commit();
        em.clear();
        
        Long id = playlist.getId();
        assertNotNull(id);
        Playlist playlist2 = playlistDAOImpl.getById(Playlist.class, id);
        assertEquals(playlist, playlist2);
        
    }
    
    
    
}
