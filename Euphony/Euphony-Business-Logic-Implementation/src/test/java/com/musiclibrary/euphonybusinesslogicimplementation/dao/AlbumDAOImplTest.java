package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;

/**
 * Unit tests for Album DAO implementation.
 *
 * @author Sebastian Lazon #395990
 */
public class AlbumDAOImplTest extends TestCase {

    private EntityManagerFactory emf;
    private EntityManager em;
    private AlbumDAOImpl albumDao;

    public AlbumDAOImplTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        albumDao = new AlbumDAOImpl(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests of creating new album
     */
    public void testCreateAlbum() {

        em.getTransaction().begin();
        try {
            albumDao.create(null);
            fail("Null album create");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Album album = null;
        try {
            albumDao.create(album);
            fail("Empty album create");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album(null, "cover.jpg", DateTime.now(), "comment");
        try {
            albumDao.create(album);
            fail("Album title null");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album("", "cover.jpg", DateTime.now(), "comment");
        try {
            albumDao.create(album);
            fail("Album title empty");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album("title", "cover.jpg", null, "comment");
        try {
            albumDao.create(album);
            fail("Album date null");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();



        em.getTransaction().begin();
        album = new Album("title", "cover.jpg", DateTime.now(), "comment");
        albumDao.create(album);
        em.getTransaction().commit();
        em.clear();
        Long id = album.getId();
        assertNotNull(id);
        assertDeepEquals(album, albumDao.getById(id));


        em.getTransaction().begin();
        try {
            albumDao.create(album);
            fail("Album already in db");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Tests of getting an album
     */
    public void testGetAlbum() {

        em.getTransaction().begin();
        try {
            albumDao.getById(null);
            fail("null id");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        Album album = new Album("title", "cover.jpg", DateTime.now(), "comment");

        em.getTransaction().begin();
        try {
            albumDao.getById(album.getId());
            fail("id not in db");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        assertDeepEquals(album, albumDao.getById(album.getId()));
        em.clear();

    }

    /**
     * Tests of updating album
     */
    public void testUpdateAlbum() {
        em.getTransaction().begin();
        try {
            albumDao.update(null);
            fail("null album update");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        try {
            albumDao.update(new Album());
            fail("empty album update");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Album album = new Album("title", "cover.jpg", DateTime.now(), "comment");
        try {
            albumDao.update(album);
            fail("Item not in db");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();

        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

        album = new Album("title", "cover.jpg", DateTime.now(), "comment");
        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        album.setTitle("title2");
        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

        album = new Album("title", "cover.jpg", DateTime.now(), "comment");
        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        album.setCover("cover2.jpg");
        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

        album = new Album("title", "cover.jpg", DateTime.now(), "comment");
        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        album.setReleaseDate(DateTime.now());
        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

        album = new Album("title", "cover.jpg", DateTime.now(), "comment");
        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        album.setComment("comment2");
        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

    }

    /*
     * Testing of deleting album
     */
    public void testDeleteAlbum() {
        em.getTransaction().begin();
        try {
            albumDao.delete(null);
            fail("deleting null album");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Album album = new Album("title", "cover.jpg", DateTime.now(), "comment");
        try {
            albumDao.delete(album);
            fail("album not in db");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        Long id = album.getId();
        em.getTransaction().begin();
        albumDao.delete(album);
        em.getTransaction().commit();
        em.clear();
        assertNull(albumDao.getById(id));
    }

    /*
     * Testing of getting all albums
     */
    public void testGetAllAlbums() {
        assertTrue(albumDao.getAll().isEmpty());

        Album album1 = new Album("title1", "cover1.jpg", DateTime.now(), "comment1");
        Album album2 = new Album("title2", "cover2.jpg", DateTime.now(), "comment2");

        List<Album> albums = new ArrayList();
        albums.add(album1);
        albums.add(album2);
        em.getTransaction().begin();
        albumDao.create(album1);
        albumDao.create(album2);
        em.getTransaction().commit();
        assertEquals(albums, albumDao.getAll());
        em.clear();
    }

    /*
     * Get with null name
     */
    public void testGetByTitleWithNullName() {
        em.getTransaction().begin();
        try {
            albumDao.getByTitle(null);//title is null
        } catch (DataAccessException e) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();
    }

    public void testGetAlbumByNameWithNotAssignedName() {
        em.getTransaction().begin();
        Album nullResult = albumDao.getByTitle("Mirage");//getAlbumByName with not assigned name, should return null
        em.getTransaction().commit();
        em.clear();
        assertNull(nullResult);
    }

    public void testGetAlbumByTitle() {
        em.getTransaction().begin();
        Album expResult = new Album("Kaleidoscope", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), "nehehe");
        albumDao.create(expResult);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());

        Album result = albumDao.getByTitle(expResult.getTitle());//correct
        assertDeepEquals(expResult, result);
    }

    private void assertDeepEquals(Album a1, Album a2) {
        assertEquals(a1.getId(), a2.getId());
        assertEquals(a1.getComment(), a2.getComment());
        assertEquals(a1.getCover(), a2.getCover());
        assertEquals(a1.getReleaseDate(), a2.getReleaseDate());
        assertEquals(a1.getTitle(), a2.getTitle());
    }
}
