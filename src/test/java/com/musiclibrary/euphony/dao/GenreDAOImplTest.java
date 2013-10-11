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

    EntityManagerFactory emf;
    EntityManager em;
    GenreDAOImpl genreDAOImpl;
    
    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        genreDAOImpl = new GenreDAOImpl();
    }
    
    /**
     * Test of createGenre method, of class genreDAOImpl with wrong
     * attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateGenreWithNull() {  
        em.getTransaction().begin();
        genreDAOImpl.create(null);              //genre je null
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
     * Test of getGenreById method, of class genreDAOImpl with wrong
     * attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetGenreWithNullBoth() {
        em.getTransaction().begin();
        genreDAOImpl.getById(null, null);              //id and class are null
        em.getTransaction().commit();
        em.clear();     
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetGenremWithNullId() {
        em.getTransaction().begin();
        genreDAOImpl.getById(Genre.class, null);              //id is null
        em.getTransaction().commit();
        em.clear();     
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testGetGenreWithNullClass() {
        em.getTransaction().begin();
        genreDAOImpl.getById(null, new Long(100));              //class is null
        em.getTransaction().commit();
        em.clear();     
    }

    @Test
    public void testGetGenremWithNotAssignedId() {
        em.getTransaction().begin();
        Genre nullResult = genreDAOImpl.getById(Genre.class, new Long(100));              //getGenre with not assigned id, should return null
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

        Genre result = genreDAOImpl.getById(Genre.class, genreId);              //spravne
        assertDeepEquals(expResult, result);
    }

    /**
     * Test of updateGenre method, of class genreDAOImpl with wrong
     * attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithNull() {
        itemManager.updateItem(null);           //item je null
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithNullAttributes() {
        Item item = new Item();
        itemManager.updateItem(item);           //item ma null atributy
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithNullTitleTypeYear() {
        Item item = new Item();
        item.setAuthor("Charles Dickens");
        itemManager.updateItem(item);           //item ma null title,type a year   
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithNullTitleYear() {
        Item item = new Item();
        item.setAuthor("Charles Dickens");
        item.setItemType(Type.BOOK);
        itemManager.updateItem(item);           //item ma null title a year
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithNullYear() {
        Item item = new Item();
        item.setAuthor("Charles Dickens");
        item.setItemType(Type.BOOK);
        item.setTitle("A Christmas Carol");
        itemManager.updateItem(item);           //item ma null year
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCUpdateItemWithMinusYear() {
        Item item = new Item();
        item.setAuthor("Charles Dickens");
        item.setItemType(Type.BOOK);
        item.setTitle("A Christmas Carol");
        item.setYear(-1843);
        itemManager.updateItem(item);           //item ma zaporny rok 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithInvalidYear() {
        Item item = new Item();
        item.setAuthor("Charles Dickens");
        item.setItemType(Type.BOOK);
        item.setTitle("A Christmas Carol");
        item.setYear(4013);
        itemManager.updateItem(item);           //item ma vacsi rok ako je aktualny      
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithEmptyAuthor() {
        Item item = new Item();
        item.setAuthor("");
        item.setItemType(Type.BOOK);
        item.setTitle("A Christmas Carol");
        item.setYear(1843);
        itemManager.updateItem(item);           //item ma prazdneho autora
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithEmptyTitle() {
        Item item = new Item();
        item.setAuthor("Charles Dickens");
        item.setItemType(Type.BOOK);
        item.setTitle("A Christmas Carol");
        item.setYear(1843);
        item.setTitle("");
        itemManager.updateItem(item);           //item ma prazdny nazov

    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithNoId() {
        Item item = new Item();
        item.setAuthor("Charles Dickens");
        item.setItemType(Type.BOOK);
        item.setTitle("A Christmas Carol");
        item.setYear(1843);
        itemManager.updateItem(item);           //item ma korektne atributy ale null id
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateItemWithNotAssignedId() {

        Item item = new Item("A Christmas Carol", "Charles Dickens", 1843, Type.BOOK);
        Item updatedItem = new Item("A Christmas Carol 2", "Charles Dickens", 1900, Type.BOOK);

        updatedItem.setId(new Long(100));                     //updatedItem ma este nepriradene id

        itemManager.updateItem(updatedItem);
    }

    /**
     * Test of updateItem method, of class ItemManagerImpl.
     */
    @Test
    public void testUpdateItem() {
        System.out.println("updateItem");

        Item item = new Item("A Christmas Carol", "Charles Dickens", 1843, Type.BOOK);

        Item updatedItem = new Item("A Christmas Carol 2", "Charles Dickens", 1900, Type.BOOK);

        itemManager.createItem(item);
        updatedItem.setId(item.getId());

        itemManager.updateItem(updatedItem);                //spravne
        assertDeepEquals(updatedItem, itemManager.getItem(item.getId()));

    }

    /**
     * Test of deleteItem method, of class ItemManagerImpl with wrong
     * attributes.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeleteItemWithNull() {
        itemManager.deleteItem(null);           //item je null
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteItemWithNullId() {
        Item item = new Item("A Christmas Carol", "Charles Dickens", 1843, Type.BOOK);
        itemManager.deleteItem(item);           //item ma null id
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteItemWithNotAssignedId() {
        Item item = new Item("A Christmas Carol", "Charles Dickens", 1843, Type.BOOK);
        item.setId(new Long(100));

        itemManager.deleteItem(item);           //item ma nepriradene id  
    }

    /**
     * Test of deleteItem method, of class ItemManagerImpl.
     */
    @Test
    public void testDeleteItem() {
        System.out.println("deleteItem");

        Item item = new Item("A Christmas Carol", "Charles Dickens", 1843, Type.BOOK);

        itemManager.createItem(item);
        itemManager.deleteItem(item);               //spravne  
    }

    private void assertDeepEquals(Genre expected, Genre actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
