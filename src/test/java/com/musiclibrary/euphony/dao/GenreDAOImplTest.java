package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.GenreDAOImpl;
import com.musiclibrary.euphony.entities.Genre;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for Genre DAO implementation.
 *
 * @author Jakub Medvecky-Heretik #396373
 */
public class GenreDAOImplTest {

    private EntityManagerFactory emf;
    private EntityManager em;
    private GenreDAOImpl genreDAOImpl;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        genreDAOImpl = new GenreDAOImpl(em);
    }

    /**
     * Test of createGenre method, of class genreDAOImpl with wrong attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateGenreWithNull() {
        em.getTransaction().begin();
        genreDAOImpl.create(null);              //genre is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGenreWithNullAttributes() {
        em.getTransaction().begin();
        Genre genre = new Genre();
        genreDAOImpl.create(genre);              //genre has null attributes
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGenreWithEmptyName() {
        em.getTransaction().begin();
        Genre genre = new Genre();
        genre.setName("");
        genreDAOImpl.create(genre);              //genre has empty name
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Test of createGenre method, of class genreDAOImpl.
     */
    @Test
    public void testCreateGenre() {
        System.out.println("createGenre");

        em.getTransaction().begin();
        Genre genre = new Genre("Hip Hop");     //genre has not-empty name and null id
        genreDAOImpl.create(genre);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(genre.getId());
        Long genreId = genre.getId();

        Genre expResult = new Genre("Hip Hop");
        expResult.setId(genreId);

        assertDeepEquals(expResult, genre);
    }

    /**
     * Test of getGenreById method, of class genreDAOImpl with wrong attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetGenreWithNullBoth() {
        em.getTransaction().begin();
        genreDAOImpl.getById(null);              //id and class are null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGenremWithNullId() {
        em.getTransaction().begin();
        genreDAOImpl.getById(null);              //id is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGenreWithNullClass() {
        em.getTransaction().begin();
        genreDAOImpl.getById(new Long(100));              //class is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test
    public void testGetGenreWithNotAssignedId() {
        em.getTransaction().begin();
        Genre nullResult = genreDAOImpl.getById(new Long(100));              //getGenre with not assigned id, should return null
        em.getTransaction().commit();
        em.clear();
        assertNull(nullResult);
    }

    /**
     * Test of getGenreById method, of class genreDAOImpl.
     */
    @Test
    public void testGetGenreById() {
        System.out.println("getGenreById");

        em.getTransaction().begin();
        Genre expResult = new Genre("Heavy metal");
        genreDAOImpl.create(expResult);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());
        Long genreId = expResult.getId();

        Genre result = genreDAOImpl.getById(genreId);              //correct
        assertDeepEquals(expResult, result);
    }

    /**
     * Test of updateGenre method, of class genreDAOImpl with wrong attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateGenreWithNull() {
        em.getTransaction().begin();
        genreDAOImpl.update(null);              //genre is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateGenreWithNullAttributes() {
        em.getTransaction().begin();
        Genre genre = new Genre();
        genreDAOImpl.update(genre);              //genre has null attributes
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateGenreWithEmptyName() {
        em.getTransaction().begin();
        Genre genre = new Genre();
        genre.setName("");
        genreDAOImpl.update(genre);              //genre has empty name
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateGenreWithNoId() {
        em.getTransaction().begin();
        Genre genre = new Genre();
        genre.setName("Death metal");           //genre has null id
        genreDAOImpl.update(genre);
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateGenreWithNotAssignedId() {
        em.getTransaction().begin();
        Genre genre = new Genre("Death metal");
        genre.setId(new Long(100));             //genre has not assigned id from db
        genreDAOImpl.update(genre);
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Test of updateGenre method, of class genreDAOImpl.
     */
    @Test
    public void testUpdateGenre() {
        System.out.println("updateGenre");

        Genre genre = new Genre("Death metal");

        Genre updatedGenre = new Genre("Doom metal");

        em.getTransaction().begin();
        genreDAOImpl.create(genre);
        em.getTransaction().commit();
        updatedGenre.setId(genre.getId());

        em.getTransaction().begin();
        genreDAOImpl.update(updatedGenre);                //correct
        em.getTransaction().commit();
        assertDeepEquals(updatedGenre, genreDAOImpl.getById(genre.getId()));
        em.clear();

    }

    /**
     * Test of deleteGenre method, of class genreDAOImpl with wrong attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteGenreWithNull() {
        em.getTransaction().begin();
        genreDAOImpl.delete(null);           //genre is null
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteGenreWithNullId() {
        Genre genre = new Genre("Trip hop");
        em.getTransaction().begin();
        genreDAOImpl.delete(genre);           //genre has null id
        em.getTransaction().commit();
        em.clear();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteGenreWithNotAssignedId() {
        Genre genre = new Genre("Trip hop");
        genre.setId(new Long(100));

        em.getTransaction().begin();
        genreDAOImpl.delete(genre);           //genre has not assigned id from db 
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Test of deleteGenre method, of class genreDAOImpl.
     */
    @Test
    public void testDeleteGenre() {
        System.out.println("deleteGenre");

        Genre genre = new Genre("Trip hop");
        em.getTransaction().begin();
        genreDAOImpl.create(genre);           //correct
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        genreDAOImpl.delete(genre);           
        em.getTransaction().commit();
        em.clear();
    }

    private void assertDeepEquals(Genre expected, Genre actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
