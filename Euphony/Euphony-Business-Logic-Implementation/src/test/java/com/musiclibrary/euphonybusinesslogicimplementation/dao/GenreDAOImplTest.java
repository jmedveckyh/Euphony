package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
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
 * Unit tests for Genre DAO implementation.
 *
 * @author Jakub Medvecky-Heretik #396373
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testApplicationContext.xml" ) 
@Transactional
public class GenreDAOImplTest {

    @Autowired
    private GenreDAO genreDAO;

    public void setGenreDAO(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }
    
    /**
     * Test of createGenre method, of class genreDAO with wrong attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testCreateGenreWithNull() {

        genreDAO.create(null);              //genre is null

    }

    @Test(expected = DataAccessException.class)
    public void testCreateGenreWithNullAttributes() {

        Genre genre = new Genre();
        genreDAO.create(genre);              //genre has null attributes


    }

    @Test(expected = DataAccessException.class)
    public void testCreateGenreWithEmptyName() {

        Genre genre = new Genre();
        genre.setName("");
        genreDAO.create(genre);              //genre has empty name


    }

    /**
     * Test of createGenre method, of class genreDAO.
     */
    @Test
    public void testCreateGenre() {
        System.out.println("createGenre");


        Genre genre = new Genre("Hip Hop");     //genre has not-empty name and null id
        genreDAO.create(genre);



        assertNotNull(genre.getId());
        Long genreId = genre.getId();

        Genre expResult = new Genre("Hip Hop");
        expResult.setId(genreId);

        assertDeepEquals(expResult, genre);
    }

    /**
     * Test of getGenreById method, of class genreDAO with wrong attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testGetGenreByIdWithNull() {

        genreDAO.getById(null);              //id is null


    }

    @Test
    public void testGetGenreByIdWithNotAssignedId() {

        Genre nullResult = genreDAO.getById(new Long(100));              //getGenre with not assigned id, should return null

        assertNull(nullResult);
    }

    /**
     * Test of getGenreById method, of class genreDAO.
     */
    @Test
    public void testGetGenreById() {
        System.out.println("getGenreById");


        Genre expResult = new Genre("Heavy metal");
        genreDAO.create(expResult);

        assertNotNull(expResult.getId());
        Long genreId = expResult.getId();

        Genre result = genreDAO.getById(genreId);              //correct
        assertDeepEquals(expResult, result);
    }

    /**
     * Test of updateGenre method, of class genreDAO with wrong attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testUpdateGenreWithNull() {

        genreDAO.update(null);              //genre is null


    }

    @Test(expected = DataAccessException.class)
    public void testUpdateGenreWithNullAttributes() {

        Genre genre = new Genre();
        genreDAO.update(genre);              //genre has null attributes


    }

    @Test(expected = DataAccessException.class)
    public void testUpdateGenreWithEmptyName() {

        Genre genre = new Genre();
        genre.setName("");
        genreDAO.update(genre);              //genre has empty name


    }

    @Test(expected = DataAccessException.class)
    public void testUpdateGenreWithNoId() {

        Genre genre = new Genre();
        genre.setName("Death metal");           //genre has null id
        genreDAO.update(genre);


    }

    @Test(expected = DataAccessException.class)
    public void testUpdateGenreWithNotAssignedId() {

        Genre genre = new Genre("Death metal");
        genre.setId(new Long(100));             //genre has not assigned id from db
        genreDAO.update(genre);


    }

    /**
     * Test of updateGenre method, of class genreDAO.
     */
    @Test
    public void testUpdateGenre() {
        System.out.println("updateGenre");

        Genre genre = new Genre("Death metal");

        Genre updatedGenre = new Genre("Doom metal");


        genreDAO.create(genre);

        updatedGenre.setId(genre.getId());


        genreDAO.update(updatedGenre);                //correct

        assertDeepEquals(updatedGenre, genreDAO.getById(genre.getId()));


    }

    /**
     * Test of deleteGenre method, of class genreDAO with wrong attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testDeleteGenreWithNull() {

        genreDAO.delete(null);           //genre is null


    }

    @Test(expected = DataAccessException.class)
    public void testDeleteGenreWithNullId() {
        Genre genre = new Genre("Trip hop");

        genreDAO.delete(genre);           //genre has null id


    }

    @Test(expected = DataAccessException.class)
    public void testDeleteGenreWithNotAssignedId() {
        Genre genre = new Genre("Trip hop");
        genre.setId(new Long(100));


        genreDAO.delete(genre);           //genre has not assigned id from db 


    }

    /**
     * Test of deleteGenre method, of class genreDAO.
     */
    @Test
    public void testDeleteGenre() {
        System.out.println("deleteGenre");

        Genre genre = new Genre("Trip hop");

        genreDAO.create(genre);           //correct




        genreDAO.delete(genre);


    }

    /**
     * Test of getGenreByName method, of class genreDAO with wrong attributes.
     */
    @Test(expected = DataAccessException.class)
    public void testGetGenreByNameWithNull() {

        genreDAO.getByName(null);              //name is null


    }

    @Test
    public void testGetGenreByNameWithNotAssignedName() {

        Genre nullResult = genreDAO.getByName("Heavy Metal");              //getGenreByName with not assigned name, should return null


        assertNull(nullResult);
    }

    /**
     * Test of getGenreById method, of class genreDAO.
     */
    @Test
    public void testGetGenreByName() {
        System.out.println("getGenreByName");


        Genre expResult = new Genre("Heavy metal");
        genreDAO.create(expResult);



        assertNotNull(expResult.getId());

        Genre result = genreDAO.getByName(expResult.getName());              //correct
        assertDeepEquals(expResult, result);
    }

    /**
     * Test of getGenreById method, of class genreDAO.
     */
    @Test
    public void testGetAllGenres() {
        System.out.println("getAllGenres");

        List<Genre> expResults = new ArrayList<Genre>();
        assertEquals(expResults, genreDAO.getAll());

        Genre expResult1 = new Genre("Heavy metal");
        Genre expResult2 = new Genre("Doom metal");
        Genre expResult3 = new Genre("Brutal death");
        genreDAO.create(expResult1);
        genreDAO.create(expResult2);
        genreDAO.create(expResult3);

        assertNotNull(expResult1.getId());
        assertNotNull(expResult2.getId());
        assertNotNull(expResult3.getId());

        expResults.add(expResult1);
        expResults.add(expResult2);
        expResults.add(expResult3);

        List<Genre> results = genreDAO.getAll();              //correct
        assertEquals(expResults, results);

    }

    private void assertDeepEquals(Genre expected, Genre actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
