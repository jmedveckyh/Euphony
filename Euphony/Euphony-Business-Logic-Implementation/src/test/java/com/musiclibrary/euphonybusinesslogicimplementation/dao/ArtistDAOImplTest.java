package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.ArtistDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;

/**
 * Unit tests for Artist DAO implementation.
 *
 * @author Jakub Medvecky-Heretik #396373
 */
public class ArtistDAOImplTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private ArtistDAOImpl artistDAOImpl;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        artistDAOImpl = new ArtistDAOImpl(em);
    }

    /**
     * Test of createArtist method, of class artistDAOImpl with wrong
     * attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testCreateArtistWithNull() {
        em.getTransaction().begin();
        artistDAOImpl.create(null);              //artist is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = DataAccessException.class)
    public void testCreateArtistWithNullAttributes() {
        em.getTransaction().begin();
        Artist artist = new Artist();
        artistDAOImpl.create(artist);              //artist has null attributes
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = DataAccessException.class)
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
     * Test of getArtistById method, of class artistDAOImpl with wrong
     * attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testGetArtistByIdWithNull() {
        em.getTransaction().begin();
        artistDAOImpl.getById(null);              //id and class are null
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void testGetArtistByIdWithNotAssignedId() {
        em.getTransaction().begin();
        Artist nullResult = artistDAOImpl.getById(new Long(100));              //getArtist with not assigned id, should return null
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

        Artist result = artistDAOImpl.getById(artistId);              //correct
        assertDeepEquals(expResult, result);
    }

    /**
     * Test of updateArtist method, of class artistDAOImpl with wrong
     * attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testUpdateArtistWithNull() {
        em.getTransaction().begin();
        artistDAOImpl.update(null);              //artist is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateArtistWithNullAttributes() {
        em.getTransaction().begin();
        Artist artist = new Artist();
        artistDAOImpl.update(artist);              //artist has null attributes
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateArtistWithEmptyName() {
        em.getTransaction().begin();
        Artist artist = new Artist();
        artist.setName("");
        artistDAOImpl.update(artist);              //artist has empty name
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateArtistWithNoId() {
        em.getTransaction().begin();
        Artist artist = new Artist();
        artist.setName("Marika Gombitova");           //artist has null id
        artistDAOImpl.update(artist);
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = DataAccessException.class)
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
        assertDeepEquals(updatedArtist, artistDAOImpl.getById(artist.getId()));
        em.clear();

    }

    /**
     * Test of deleteArtist method, of class artistDAOImpl with wrong
     * attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testDeleteArtistWithNull() {
        em.getTransaction().begin();
        artistDAOImpl.delete(null);           //artist is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteArtistWithNullId() {
        Artist artist = new Artist("Marika Gombitova");
        em.getTransaction().begin();
        artistDAOImpl.delete(artist);           //artist has null id
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = DataAccessException.class)
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

    /**
     * Test of getArtistByName method, of class artistDAOImpl with wrong
     * attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testGetArtistByNameWithNull() {
        em.getTransaction().begin();
        artistDAOImpl.getByName(null);              //name is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void testGetArtistByNameWithNotAssignedName() {
        em.getTransaction().begin();
        Artist nullResult = artistDAOImpl.getByName("Nicki Minaj");              //getArtistByName with not assigned name, should return null
        em.getTransaction().commit();
        em.clear();
        assertNull(nullResult);
    }

    /**
     * Test of getArtistById method, of class artistDAOImpl.
     */
    @Test
    public void testGetArtistByName() {
        System.out.println("getArtistByName");

        em.getTransaction().begin();
        Artist expResult = new Artist("Nicki Minaj");
        artistDAOImpl.create(expResult);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());

        Artist result = artistDAOImpl.getByName(expResult.getName());              //correct
        assertDeepEquals(expResult, result);
    }

    /**
     * Test of getArtistById method, of class artistDAOImpl.
     */
    @Test
    public void testGetAllArtists() {
        System.out.println("getAllArtists");

        List<Artist> expResults = new ArrayList<Artist>();
        assertEquals(expResults, artistDAOImpl.getAll());

        em.getTransaction().begin();
        Artist expResult1 = new Artist("Nicki Minaj");
        Artist expResult2 = new Artist("Robo Kazik");
        Artist expResult3 = new Artist("Team");
        artistDAOImpl.create(expResult1);
        artistDAOImpl.create(expResult2);
        artistDAOImpl.create(expResult3);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult1.getId());
        assertNotNull(expResult2.getId());
        assertNotNull(expResult3.getId());

        expResults.add(expResult1);
        expResults.add(expResult2);
        expResults.add(expResult3);

        List<Artist> results = artistDAOImpl.getAll();              //correct
        assertEquals(expResults, results);

    }

    public static void assertDeepEquals(Artist expected, Artist actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
