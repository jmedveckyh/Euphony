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
    private AlbumDAO albumDao;

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

        try {
            albumDao.create(null);
            fail("Null album create");
        } catch (IllegalArgumentException e) {
            //OK
        }
        
        Album album = null;
        try {
            albumDao.create(album);
            fail("Empty album create");
        } catch (IllegalArgumentException e) {
            //OK
        }

        album = new Album(null, "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album title null");
        } catch (IllegalArgumentException e) {
            //OK
        }
        
        album = new Album("", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album title empty");
        } catch (IllegalArgumentException e) {
            //OK
        }
        
        album = new Album("title", "cover.jpg", null, new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album date null");
        } catch (IllegalArgumentException e) {
            //OK
        }
        
        album = new Album("title", "cover.jpg", DateTime.now(), null, "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album songs null");
        } catch (IllegalArgumentException e) {
            //OK
        }
        
        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", null, new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album artists null");
        } catch (IllegalArgumentException e) {
            //OK
        }
        
        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), null);
        try {
            albumDao.create(album);
            fail("Album genres null");
        } catch (IllegalArgumentException e) {
            //OK
        }
        
        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        albumDao.create(album);
        Long id = album.getId();
        assertNotNull(id);
        assertDeepEquals(album, albumDao.getById(id));
        
        try {
            albumDao.create(album);
            fail("Album already in db");
        } catch (IllegalArgumentException e) {
            //OK
        }
        
        /*
        List<Artist> artists = new ArrayList();
        List<Genre> genres = new ArrayList();
        artists.add(new Artist("Iron Maiden"));
        genres.add(new Genre("heavy metal"));
        album = new Album("The Number of the Beast", "cover1.jpg", DateTime.now(), new ArrayList<Song>(), "comment1", artists, genres);
        
        List<Song> songs = new ArrayList();
        songs.add(new Song("The Prisoner",320,2,"comment",genre,album,artist));*/
    }

    /**
     * Tests of getting an album
     */
    public void testGetAlbum() {

        try{
            albumDao.getById(null);
            fail("null id");
        } catch (IllegalArgumentException ex){}
        
        Album album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        
        try{
            albumDao.getById(album.getId());
            fail("id not in db");
        } catch (IllegalArgumentException ex){}
        
        albumDao.create(album);
        assertDeepEquals(album,albumDao.getById(album.getId()));

    }

    /**
     * Tests of updating album
     */
    public void testUpdateAlbum() {
        try {
            albumDao.update(null);
            fail("null album update");
        } catch (IllegalArgumentException ex) {
           
        }
        
        try {
            albumDao.update(new Album());
            fail("empty album update");
        } catch (IllegalArgumentException ex) {
           
        }
        
        Album album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.update(album);
            fail("Item not in db");
        } catch (IllegalArgumentException ex) {
           
        }
        albumDao.create(album);
        albumDao.update(album);
        assertDeepEquals(album, albumDao.getById(album.getId()));

        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        albumDao.create(album);
        album.setTitle("title2");
        albumDao.update(album);
        assertDeepEquals(album, albumDao.getById(album.getId()));
        
        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        albumDao.create(album);
        album.setCover("cover2.jpg");
        albumDao.update(album);
        assertDeepEquals(album, albumDao.getById(album.getId()));
        
        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        albumDao.create(album);
        album.setReleaseDate(DateTime.now());
        albumDao.update(album);
        assertDeepEquals(album, albumDao.getById(album.getId()));
        
        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        albumDao.create(album);
        album.setComment("comment2");
        albumDao.update(album);
        assertDeepEquals(album, albumDao.getById(album.getId()));
        
    }

    public void testDeleteAlbum() {
        try {
            albumDao.delete(null);
            fail("deleting null album");
        } catch (IllegalArgumentException ex) {
           
        }
        
        Album album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try{
            albumDao.delete(album);
            fail("album not in db");
        } catch (IllegalArgumentException ex) {
            
        }
        
        albumDao.create(album);
        Long id = album.getId();
        albumDao.delete(album);
        assertNull(albumDao.getById(id));
    }
    
    public void testGetAllAlbums(){
        assertTrue(albumDao.getAll().isEmpty());

        Album album1 = new Album("title1", "cover1.jpg", DateTime.now(), new ArrayList<Song>(), "comment1", new ArrayList<Artist>(), new ArrayList<Genre>());
        Album album2 = new Album("title2", "cover2.jpg", DateTime.now(), new ArrayList<Song>(), "comment2", new ArrayList<Artist>(), new ArrayList<Genre>());
        
        List<Album> albums = new ArrayList();
        albums.add(album1);
        albums.add(album2);
        albumDao.create(album1);
        albumDao.create(album2);
        
        //assertEquals(albums, albumDao.getAll()); 
    }
    
    public void testGetByTitleWithNullName(){
        em.getTransaction().begin();
        try{
            albumDao.getByTitle(null);              //title is null
        } catch(IllegalArgumentException e){
            //ok
        }
        em.getTransaction().commit();
        em.clear();
    }
    
    public void testGetArtistByNameWithNotAssignedName() {
        em.getTransaction().begin();
        Album nullResult = albumDao.getByTitle("Mirage");              //getAlbumByName with not assigned name, should return null
        em.getTransaction().commit();
        em.clear();
        assertNull(nullResult);
    }
    
    public void testGetAlbumByTitle(){
        
        em.getTransaction().begin();
        Album expResult = new Album("Kaleidoscope", "cover.jpg", new DateTime(2009,1,1,0,0), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(new Artist("Tiesto"));
        albumDao.create(expResult);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());

        Album result = albumDao.getByTitle(expResult.getTitle());              //correct
        //assertDeepEquals(expResult, result);
        assertEquals(expResult.getTitle(), result.getTitle());
        assertEquals(expResult.getCover(), result.getCover());
        assertEquals(expResult.getReleaseDate(), result.getReleaseDate());
        //assertEquals(expResult.getArtists(), result.getArtists());
        assertEquals(expResult.getComment(), result.getComment());
        //assertEquals(expResult.getSongs(), result.getSongs());
        //assertEquals(expResult.getGenres(), result.getGenres());
    }
    
    private void assertDeepEquals(Album a1,Album a2){
        assertEquals(a1.getId(),a2.getId());
        assertEquals(a1.getArtists(),a2.getArtists());
        assertEquals(a1.getComment(),a2.getComment());
        assertEquals(a1.getCover(),a2.getCover());
        assertEquals(a1.getGenres(),a2.getGenres());
        assertEquals(a1.getReleaseDate(),a2.getReleaseDate());
        assertEquals(a1.getSongs(),a2.getSongs());
        assertEquals(a1.getTitle(),a2.getTitle());
    }
}
