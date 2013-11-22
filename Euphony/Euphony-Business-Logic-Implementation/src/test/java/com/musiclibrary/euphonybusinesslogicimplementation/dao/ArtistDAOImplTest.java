//package com.musiclibrary.euphonybusinesslogicimplementation.dao;
//
//import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.ArtistDAOImpl;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
//import java.util.ArrayList;
//import java.util.List;
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
// * Unit tests for Artist DAO implementation.
// *
// * @author Jakub Medvecky-Heretik #396373
// */
//public class ArtistDAOImplTest {
//
//    private ArtistDAO artistDAOImpl;
//
//    @Before
//    public void setUp() {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testApplicationContext.xml");
//        artistDAOImpl = (ArtistDAO) applicationContext.getBean("artistDAO");
//    }
//    
//    /**
//     * Test of createArtist method, of class artistDAOImpl with wrong attributes.
//     */
//    @Test(expected = DataAccessException.class)
//    public void testCreateArtistWithNull() {
//        
//        artistDAOImpl.create(null);              //artist is null
//        
//        
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void testCreateArtistWithNullAttributes() {
//        
//        Artist artist = new Artist();
//        artistDAOImpl.create(artist);              //artist has null attributes
//        
//        
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void testCreateArtistWithEmptyName() {
//        
//        Artist artist = new Artist();
//        artist.setName("");
//        artistDAOImpl.create(artist);              //artist has empty name
//        
//        
//    }
//
//    /**
//     * Test of createArtist method, of class artistDAOImpl.
//     */
//    @Test
//    public void testCreateArtist() {
//        System.out.println("createArtist");
//
//        
//        Artist artist = new Artist("Bjork");     //artist has not-empty name and null id
//        artistDAOImpl.create(artist);
//        
//        
//
//        assertNotNull(artist.getId());
//        Long artistId = artist.getId();
//
//        Artist expResult = new Artist("Bjork");
//        expResult.setId(artistId);
//
//        assertDeepEquals(expResult, artist);
//    }
//
//    /**
//     * Test of getArtistById method, of class artistDAOImpl with wrong attributes.
//     */
//    @Test(expected = DataAccessException.class)
//    public void testGetArtistByIdWithNull() {
//        
//        artistDAOImpl.getById(null);              //id and class are null
//        
//        
//    }
//
//    @Test
//    public void testGetArtistByIdWithNotAssignedId() {
//        
//        Artist nullResult = artistDAOImpl.getById(new Long(100));              //getArtist with not assigned id, should return null
//        
//        
//        assertNull(nullResult);
//    }
//
//    /**
//     * Test of getArtistById method, of class artistDAOImpl.
//     */
//    @Test
//    public void testGetArtistById() {
//        System.out.println("getArtistById");
//
//        
//        Artist expResult = new Artist("Marika Gombitova");
//        artistDAOImpl.create(expResult);
//        
//        
//
//        assertNotNull(expResult.getId());
//        Long artistId = expResult.getId();
//
//        Artist result = artistDAOImpl.getById(artistId);              //correct
//        assertDeepEquals(expResult, result);
//    }
//
//    /**
//     * Test of updateArtist method, of class artistDAOImpl with wrong attributes.
//     */
//    @Test(expected = DataAccessException.class)
//    public void testUpdateArtistWithNull() {
//        
//        artistDAOImpl.update(null);              //artist is null
//        
//        
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void testUpdateArtistWithNullAttributes() {
//        
//        Artist artist = new Artist();
//        artistDAOImpl.update(artist);              //artist has null attributes
//        
//        
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void testUpdateArtistWithEmptyName() {
//        
//        Artist artist = new Artist();
//        artist.setName("");
//        artistDAOImpl.update(artist);              //artist has empty name
//        
//        
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void testUpdateArtistWithNoId() {
//        
//        Artist artist = new Artist();
//        artist.setName("Marika Gombitova");           //artist has null id
//        artistDAOImpl.update(artist);
//        
//        
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void testUpdateArtistWithNotAssignedId() {
//        
//        Artist artist = new Artist("Marika Gombitova");
//        artist.setId(new Long(100));             //artist has not assigned id from db
//        artistDAOImpl.update(artist);
//        
//        
//    }
//
//    /**
//     * Test of updateArtist method, of class artistDAOImpl.
//     */
//    @Test
//    public void testUpdateArtist() {
//        System.out.println("updateArtist");
//
//        Artist artist = new Artist("Marika Gombitova");
//
//        Artist updatedArtist = new Artist("Michal David");
//
//        
//        artistDAOImpl.create(artist);
//        
//        updatedArtist.setId(artist.getId());
//
//        
//        artistDAOImpl.update(updatedArtist);                //correct
//        
//        assertDeepEquals(updatedArtist, artistDAOImpl.getById(artist.getId()));
//        
//
//    }
//
//    /**
//     * Test of deleteArtist method, of class artistDAOImpl with wrong attributes.
//     */
//    @Test(expected = DataAccessException.class)
//    public void testDeleteArtistWithNull() {
//        
//        artistDAOImpl.delete(null);           //artist is null
//        
//        
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void testDeleteArtistWithNullId() {
//        Artist artist = new Artist("Marika Gombitova");
//        
//        artistDAOImpl.delete(artist);           //artist has null id
//        
//        
//    }
//
//    @Test(expected = DataAccessException.class)
//    public void testDeleteArtistWithNotAssignedId() {
//        Artist artist = new Artist("Marika Gombitova");
//        artist.setId(new Long(100));
//
//        
//        artistDAOImpl.delete(artist);           //artist has not assigned id from db 
//        
//        
//    }
//
//    /**
//     * Test of deleteArtist method, of class artistDAOImpl.
//     */
//    @Test
//    public void testDeleteArtist() {
//        System.out.println("deleteArtist");
//
//        Artist artist = new Artist("Marika Gombitova");
//        
//        artistDAOImpl.create(artist);           //correct
//        
//        
//        
//        
//        artistDAOImpl.delete(artist);           
//        
//        
//    }
//
//    /**
//     * Test of getArtistByName method, of class artistDAOImpl with wrong attributes.
//     */
//
//    @Test(expected = DataAccessException.class)
//    public void testGetArtistByNameWithNull() {
//        
//        artistDAOImpl.getByName(null);              //name is null
//        
//        
//    }
//    
//    @Test
//    public void testGetArtistByNameWithNotAssignedName() {
//        
//        Artist nullResult = artistDAOImpl.getByName("Nicki Minaj");              //getArtistByName with not assigned name, should return null
//        
//        
//        assertNull(nullResult);
//    }
//    
//    /**
//     * Test of getArtistById method, of class artistDAOImpl.
//     */
//    @Test
//    public void testGetArtistByName() {
//        System.out.println("getArtistByName");
//
//        
//        Artist expResult = new Artist("Nicki Minaj");
//        artistDAOImpl.create(expResult);
//        
//        
//
//        assertNotNull(expResult.getId());
//
//        Artist result = artistDAOImpl.getByName(expResult.getName());              //correct
//        assertDeepEquals(expResult, result);
//    }
//    
//    /**
//     * Test of getArtistById method, of class artistDAOImpl.
//     */
//    @Test
//    public void testGetAllArtists() {
//        System.out.println("getAllArtists");
//
//        List<Artist> expResults = new ArrayList<Artist>();
//        assertEquals(expResults, artistDAOImpl.getAll());
//        
//        
//        Artist expResult1 = new Artist("Nicki Minaj");
//        Artist expResult2 = new Artist("Robo Kazik");
//        Artist expResult3 = new Artist("Team");
//        artistDAOImpl.create(expResult1);
//        artistDAOImpl.create(expResult2);
//        artistDAOImpl.create(expResult3);
//        
//        
//
//        assertNotNull(expResult1.getId());
//        assertNotNull(expResult2.getId());
//        assertNotNull(expResult3.getId());
//        
//        expResults.add(expResult1);
//        expResults.add(expResult2);
//        expResults.add(expResult3);
//
//        List<Artist> results = artistDAOImpl.getAll();              //correct
//        assertEquals(expResults, results);
//        
//    }
//    
//    private void assertDeepEquals(Artist expected, Artist actual) {
//        assertEquals(expected.getId(), actual.getId());
//        assertEquals(expected.getName(), actual.getName());
//    }
//}
