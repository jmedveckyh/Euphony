package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.ArtistDAOImpl;
import com.musiclibrary.euphony.entities.Artist;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Artist DAO implementation.
 *
 * @author Jakub Medvecky-Heretik #396373
 */
public class ArtistDAOImplTest {

    EntityManagerFactory emf;
    EntityManager em;
    ArtistDAOImpl artistDAOImpl;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        artistDAOImpl = new ArtistDAOImpl();
    }

    /**
     * Test of createArtist method, of class artistDAOImpl with wrong attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateArtistWithNull() {
        em.getTransaction().begin();
        artistDAOImpl.create(null);              //artist is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateArtistWithNullAttributes() {
        em.getTransaction().begin();
        Artist artist = new Artist();
        artistDAOImpl.create(artist);              //artist has null attributes
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateArtistWithEmptyName() {
        em.getTransaction().begin();
        Artist artist = new Artist();
        artist.setName("");
        artistDAOImpl.create(artist);              //artist has empty name
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Test of createArtist method, of class artistDAOImpl.
     */
    @Test
    public void testCreateArtist() {
        System.out.println("createArtist");

        em.getTransaction().begin();
        Artist artist = new Artist("Bjork");     //artist has not-empty name and null id
        artistDAOImpl.create(artist);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(artist.getId());
        Long artistId = artist.getId();

        Artist expResult = new Artist("Bjork");
        expResult.setId(artistId);

        assertDeepEquals(expResult, artist);
    }

    /**
     * Test of getArtistById method, of class artistDAOImpl with wrong attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetArtistWithNullBoth() {
        em.getTransaction().begin();
        artistDAOImpl.getById(null, null);              //id and class are null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetArtistmWithNullId() {
        em.getTransaction().begin();
        artistDAOImpl.getById(Artist.class, null);              //id is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetArtistWithNullClass() {
        em.getTransaction().begin();
        artistDAOImpl.getById(null, new Long(100));              //class is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void testGetArtistWithNotAssignedId() {
        em.getTransaction().begin();
        Artist nullResult = artistDAOImpl.getById(Artist.class, new Long(100));              //getArtist with not assigned id, should return null
        em.getTransaction().commit();
        em.clear();
        assertNull(nullResult);
    }

    /**
     * Test of getArtistById method, of class artistDAOImpl.
     */
    @Test
    public void testGetArtistById() {
        System.out.println("getArtistById");

        em.getTransaction().begin();
        Artist expResult = new Artist("Marika Gombitova");
        artistDAOImpl.create(expResult);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());
        Long artistId = expResult.getId();

        Artist result = artistDAOImpl.getById(Artist.class, artistId);              //correct
        assertDeepEquals(expResult, result);
    }

    /**
     * Test of updateArtist method, of class artistDAOImpl with wrong attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateArtistWithNull() {
        em.getTransaction().begin();
        artistDAOImpl.update(null);              //artist is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateArtistWithNullAttributes() {
        em.getTransaction().begin();
        Artist artist = new Artist();
        artistDAOImpl.update(artist);              //artist has null attributes
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateArtistWithEmptyName() {
        em.getTransaction().begin();
        Artist artist = new Artist();
        artist.setName("");
        artistDAOImpl.update(artist);              //artist has empty name
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateArtistWithNoId() {
        em.getTransaction().begin();
        Artist artist = new Artist();
        artist.setName("Marika Gombitova");           //artist has null id
        artistDAOImpl.update(artist);
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateArtistWithNotAssignedId() {
        em.getTransaction().begin();
        Artist artist = new Artist("Marika Gombitova");
        artist.setId(new Long(100));             //artist has not assigned id from db
        artistDAOImpl.update(artist);
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Test of updateArtist method, of class artistDAOImpl.
     */
    @Test
    public void testUpdateArtist() {
        System.out.println("updateArtist");

        Artist artist = new Artist("Marika Gombitova");

        Artist updatedArtist = new Artist("Michal David");

        em.getTransaction().begin();
        artistDAOImpl.create(artist);
        em.getTransaction().commit();
        updatedArtist.setId(artist.getId());

        em.getTransaction().begin();
        artistDAOImpl.update(updatedArtist);                //correct
        em.getTransaction().commit();
        assertDeepEquals(updatedArtist, artistDAOImpl.getById(Artist.class, artist.getId()));
        em.clear();

    }

    /**
     * Test of deleteArtist method, of class artistDAOImpl with wrong attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteArtistWithNull() {
        em.getTransaction().begin();
        artistDAOImpl.delete(null);           //artist is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteArtistWithNullId() {
        Artist artist = new Artist("Marika Gombitova");
        em.getTransaction().begin();
        artistDAOImpl.delete(artist);           //artist has null id
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteArtistWithNotAssignedId() {
        Artist artist = new Artist("Marika Gombitova");
        artist.setId(new Long(100));

        em.getTransaction().begin();
        artistDAOImpl.delete(artist);           //artist has not assigned id from db 
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Test of deleteArtist method, of class artistDAOImpl.
     */
    @Test
    public void testDeleteArtist() {
        System.out.println("deleteArtist");

        Artist artist = new Artist("Marika Gombitova");
        em.getTransaction().begin();
        artistDAOImpl.create(artist);           //correct
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        artistDAOImpl.delete(artist);           
        em.getTransaction().commit();
        em.clear();
    }

    private void assertDeepEquals(Artist expected, Artist actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}