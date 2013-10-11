package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.ArtistDAOImpl;
import com.musiclibrary.euphony.entities.Artist;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;

/**
 * Unit tests for Artist DAO implementation.
 * 
 * @author Jakub Medvecky-Heretik #396373
 */
public class ArtistDAOImplTest extends TestCase {

    EntityManagerFactory emf;

    public ArtistDAOImplTest(String name) {
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
    
    public void testCreateArtist() {
        
        EntityManager em = emf.createEntityManager();
        ArtistDAOImpl artistDAOImpl = new ArtistDAOImpl();
    
        Artist artist = new Artist("prvy");
        em.getTransaction().begin();
        artistDAOImpl.create(artist);
        em.getTransaction().commit();
        em.clear();
        
        Long id = artist.getId();
        assertNotNull(id);
        Artist artist2 = artistDAOImpl.getById(Artist.class, id);
        assertEquals(artist, artist2);
        
    }
}
