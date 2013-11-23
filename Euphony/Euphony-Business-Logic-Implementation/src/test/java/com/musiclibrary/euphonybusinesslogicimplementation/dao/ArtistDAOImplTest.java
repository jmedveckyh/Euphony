package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit tests for Artist DAO implementation.
 *
 * @author Jakub Medvecky-Heretik #396373
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testApplicationContext.xml" ) 
@Transactional
public class ArtistDAOImplTest {

    @Autowired
    private ArtistDAO artistDAO;

    public void setArtistDAO(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }
    
    /**
     * Test of createArtist method, of class artistDAO with wrong attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testCreateArtistWithNull() {
        
        artistDAO.create(null);              //artist is null
        
        
    }

    @Test(expected = DataAccessException.class)
    public void testCreateArtistWithNullAttributes() {
        
        Artist artist = new Artist();
        artistDAO.create(artist);              //artist has null attributes
        
        
    }

    @Test(expected = DataAccessException.class)
    public void testCreateArtistWithEmptyName() {
        
        Artist artist = new Artist();
        artist.setName("");
        artistDAO.create(artist);              //artist has empty name
        
        
    }

    /**
     * Test of createArtist method, of class artistDAO.
     */
    @Test
    public void testCreateArtist() {
        System.out.println("createArtist");

        
        Artist artist = new Artist("Bjork");     //artist has not-empty name and null id
        artistDAO.create(artist);
        
        

        assertNotNull(artist.getId());
        Long artistId = artist.getId();

        Artist expResult = new Artist("Bjork");
        expResult.setId(artistId);

        assertDeepEquals(expResult, artist);
    }

    /**
     * Test of getArtistById method, of class artistDAO with wrong attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testGetArtistByIdWithNull() {
        
        artistDAO.getById(null);              //id and class are null
        
        
    }

    @Test
    public void testGetArtistByIdWithNotAssignedId() {
        
        Artist nullResult = artistDAO.getById(new Long(100));              //getArtist with not assigned id, should return null
        
        
        assertNull(nullResult);
    }

    /**
     * Test of getArtistById method, of class artistDAO.
     */
    @Test
    public void testGetArtistById() {
        System.out.println("getArtistById");

        
        Artist expResult = new Artist("Marika Gombitova");
        artistDAO.create(expResult);
        
        

        assertNotNull(expResult.getId());
        Long artistId = expResult.getId();

        Artist result = artistDAO.getById(artistId);              //correct
        assertDeepEquals(expResult, result);
    }

    /**
     * Test of updateArtist method, of class artistDAO with wrong attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testUpdateArtistWithNull() {
        
        artistDAO.update(null);              //artist is null
        
        
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateArtistWithNullAttributes() {
        
        Artist artist = new Artist();
        artistDAO.update(artist);              //artist has null attributes
        
        
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateArtistWithEmptyName() {
        
        Artist artist = new Artist();
        artist.setName("");
        artistDAO.update(artist);              //artist has empty name
        
        
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateArtistWithNoId() {
        
        Artist artist = new Artist();
        artist.setName("Marika Gombitova");           //artist has null id
        artistDAO.update(artist);
        
        
    }

    @Test(expected = DataAccessException.class)
    public void testUpdateArtistWithNotAssignedId() {
        
        Artist artist = new Artist("Marika Gombitova");
        artist.setId(new Long(100));             //artist has not assigned id from db
        artistDAO.update(artist);
        
        
    }

    /**
     * Test of updateArtist method, of class artistDAO.
     */
    @Test
    public void testUpdateArtist() {
        System.out.println("updateArtist");

        Artist artist = new Artist("Marika Gombitova");

        Artist updatedArtist = new Artist("Michal David");

        
        artistDAO.create(artist);
        
        updatedArtist.setId(artist.getId());

        
        artistDAO.update(updatedArtist);                //correct
        
        assertDeepEquals(updatedArtist, artistDAO.getById(artist.getId()));
        

    }

    /**
     * Test of deleteArtist method, of class artistDAO with wrong attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testDeleteArtistWithNull() {
        
        artistDAO.delete(null);           //artist is null
        
        
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteArtistWithNullId() {
        Artist artist = new Artist("Marika Gombitova");
        
        artistDAO.delete(artist);           //artist has null id
        
        
    }

    @Test(expected = DataAccessException.class)
    public void testDeleteArtistWithNotAssignedId() {
        Artist artist = new Artist("Marika Gombitova");
        artist.setId(new Long(100));

        
        artistDAO.delete(artist);           //artist has not assigned id from db 
        
        
    }

    /**
     * Test of deleteArtist method, of class artistDAO.
     */
    @Test
    public void testDeleteArtist() {
        System.out.println("deleteArtist");

        Artist artist = new Artist("Marika Gombitova");
        
        artistDAO.create(artist);           //correct
        
        
        
        
        artistDAO.delete(artist);           
        
        
    }

    /**
     * Test of getArtistByName method, of class artistDAO with wrong attributes.
     */

    @Test(expected = DataAccessException.class)
    public void testGetArtistByNameWithNull() {
        
        artistDAO.getByName(null);              //name is null
        
        
    }
    
    @Test
    public void testGetArtistByNameWithNotAssignedName() {
        
        Artist nullResult = artistDAO.getByName("Nicki Minaj");              //getArtistByName with not assigned name, should return null
        
        
        assertNull(nullResult);
    }
    
    /**
     * Test of getArtistById method, of class artistDAO.
     */
    @Test
    public void testGetArtistByName() {
        System.out.println("getArtistByName");

        
        Artist expResult = new Artist("Nicki Minaj");
        artistDAO.create(expResult);
        
        

        assertNotNull(expResult.getId());

        Artist result = artistDAO.getByName(expResult.getName());              //correct
        assertDeepEquals(expResult, result);
    }
    
    /**
     * Test of getArtistById method, of class artistDAO.
     */
    @Test
    public void testGetAllArtists() {
        System.out.println("getAllArtists");

        List<Artist> expResults = new ArrayList<Artist>();
        assertEquals(expResults, artistDAO.getAll());
        
        
        Artist expResult1 = new Artist("Nicki Minaj");
        Artist expResult2 = new Artist("Robo Kazik");
        Artist expResult3 = new Artist("Team");
        artistDAO.create(expResult1);
        artistDAO.create(expResult2);
        artistDAO.create(expResult3);
        
        

        assertNotNull(expResult1.getId());
        assertNotNull(expResult2.getId());
        assertNotNull(expResult3.getId());
        
        expResults.add(expResult1);
        expResults.add(expResult2);
        expResults.add(expResult3);

        List<Artist> results = artistDAO.getAll();              //correct
        assertEquals(expResults, results);
        
    }
    
    private void assertDeepEquals(Artist expected, Artist actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
