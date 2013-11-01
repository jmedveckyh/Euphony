/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Song;
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

/**
 * Unit tests for Album DAO implementation.
 *
 * @author Sebastian Lazon #395990
 */
public class AlbumDAOImplTest extends TestCase {

    private EntityManagerFactory emf;
    private EntityManager em;
    private AlbumDAOImpl albumDAOImpl;

    public AlbumDAOImplTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        albumDAOImpl = new AlbumDAOImpl(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests of creating new album
     */
    public void testCreateAlbum() {

        Album album = null;
        em.getTransaction().begin();
        try {
            albumDAOImpl.create(album);
            fail("Album is null");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album = new Album();
        em.getTransaction().begin();
        try {
            albumDAOImpl.create(album);
            fail("Empty album");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album = new Album();
        album.setTitle("");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        em.getTransaction().begin();
        try {
            albumDAOImpl.create(album);
            fail("Empty title in album");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album = new Album();
        album.setTitle("aaaa");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        album.setId(new Long(1));
        em.getTransaction().begin();
        try {
            albumDAOImpl.create(album);
            fail("Already in db");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Tests of getting an album
     */
    public void testGetAlbum() {

        Album album = new Album();
        album.setTitle("aaaa");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        em.getTransaction().begin();
        albumDAOImpl.create(album);
        em.getTransaction().commit();
        assertNotNull(album.getId());
        Album album2 = albumDAOImpl.getById( new Long(album.getId()));
        assertEquals(album, album2);
        em.getTransaction().begin();
        try {
            albumDAOImpl.getById(null);
            fail("Cant get null from db");
        } catch (IllegalArgumentException e) {
            //OK
        }

    }

    /**
     * Tests of updating album
     */
    public void testUpdateAlbum() {

        Album album = new Album();
        album.setTitle("aaaa");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        em.getTransaction().begin();
        albumDAOImpl.create(album);
        em.getTransaction().commit();

        em.getTransaction().begin();
        try {
            albumDAOImpl.update(null);
            fail("Cant update null album");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        try {
            albumDAOImpl.update(new Album());
            fail("Cant update empty album");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album.setTitle("");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        em.getTransaction().begin();
        try {
            albumDAOImpl.update(album);
            fail("Cant update album with empty title");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album.setTitle("aaa");
        album.setReleaseDate(null);
        em.getTransaction().begin();
        try {
            albumDAOImpl.update(album);
            fail("Cant update album without date");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        album.setId(null);
        em.getTransaction().begin();
        try {
            albumDAOImpl.update(album);
            fail("update with noID");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        em.getTransaction().begin();
        albumDAOImpl.create(album);
        em.getTransaction().commit();
        album.setTitle("bbb");
        em.getTransaction().begin();
        albumDAOImpl.update(album);
        em.getTransaction().commit();
        Album album2 = albumDAOImpl.getById(album.getId());
        assertEquals(album, album2);
    }

    public void testDeleteAlbum() {

        Album album = null;
        em.getTransaction().begin();
        try {
            albumDAOImpl.delete(album);
            fail("Cant delete null album");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        album.setId(null);
        em.getTransaction().begin();
        try {
            albumDAOImpl.delete(album);
            fail("Cant delete album which is not in db");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        album.setId(new Long(999));
        em.getTransaction().begin();
        try {
            albumDAOImpl.delete(album);
            fail("Cant delete album which is not in db");
        } catch (IllegalArgumentException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011, 11, 11, 0, 0));
        em.getTransaction().begin();
        albumDAOImpl.create(album);
        em.getTransaction().commit();
        em.getTransaction().begin();
        albumDAOImpl.delete(album);
        em.getTransaction().commit();
        em.clear();
    }
    
    public void testGetAllAlbums(){
        List<Album> expResults = new ArrayList<Album>();
        assertEquals(expResults, albumDAOImpl.getAll());
        
        em.getTransaction().begin();
        Album expResult1 = new Album("Pribeh", "Daco", new DateTime(), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        Album expResult2 = new Album("Mirage", "Daco", new DateTime(), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        Album expResult3 = new Album("Imagine", "Daco", new DateTime(), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        albumDAOImpl.create(expResult1);
        albumDAOImpl.create(expResult2);
        albumDAOImpl.create(expResult3);
        em.getTransaction().commit();
        em.clear();
        
        expResults.add(expResult1);
        expResults.add(expResult2);
        expResults.add(expResult3);

        List<Album> results = albumDAOImpl.getAll(); //ok
        assertEquals(expResults, results);
    }
}
