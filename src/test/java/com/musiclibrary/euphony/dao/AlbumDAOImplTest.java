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
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        /*
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        albumDao = (AlbumDAO) applicationContext.getBean("albumDAO");
        */
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
    
    public void testGetAlbumByGenre(){
        em.getTransaction().begin();
        Album expResult = new Album("Kaleidoscope", "cover.jpg", new DateTime(2009,1,1,0,0), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(new Artist("Tiesto"));
        Genre tranceGen = new Genre("Trance");
        Genre technoGen = new Genre("Techno");
        Genre houseGen = new Genre("House");
        expResult.getGenres().add(tranceGen);
        expResult.getGenres().add(houseGen);
        expResult.getGenres().add(technoGen);
        albumDao.create(expResult);
        em.getTransaction().commit();
        //em.clear();
        
        em.getTransaction().begin();
        Album expResult2 = new Album("Mirage", "cover1.jpg", new DateTime(2010,1,1,0,0), new ArrayList<Song>(), "hehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult2.getArtists().add(new Artist("Armin Van Buuren"));
        expResult2.getGenres().add(tranceGen);
        expResult2.getGenres().add(houseGen);
        albumDao.create(expResult2);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());
        assertNotNull(expResult2.getId());
        
        List<Album> resultList = albumDao.getByGenre(technoGen);
        assertEquals(1, resultList.size());
        List<Album> technoList = new ArrayList<Album>();
        technoList.add(expResult);
        assertEquals(technoList, resultList);
        
        List<Album> resultList2 = albumDao.getByGenre(houseGen);
        List<Album> houseList = new ArrayList<Album>();
        houseList.add(expResult);
        houseList.add(expResult2);
        assertEquals(2, resultList2.size());
        assertEquals(houseList, resultList2);

        /*
        assertEquals(expResult.getTitle(), result.getTitle());
        assertEquals(expResult.getCover(), result.getCover());
        assertEquals(expResult.getReleaseDate(), result.getReleaseDate());
        assertEquals(expResult.getArtists(), result.getArtists());
        */
    }
    
    public void testGetAlbumByArtist(){
        em.getTransaction().begin();
        Album expResult = new Album("Spaceman", "cover.jpg", new DateTime(2009,1,1,0,0), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        Artist tiesto = new Artist("Tiesto");
        Artist hardwell = new Artist("Hardwell");
        Artist dyro = new Artist("Dyro");
        
        expResult.getArtists().add(tiesto);
        expResult.getArtists().add(hardwell);
        expResult.getArtists().add(dyro);
        albumDao.create(expResult);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Album expResult2 = new Album("Imagine", "cover1.jpg", new DateTime(2010,1,1,0,0), new ArrayList<Song>(), "hehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult2.getArtists().add(dyro);
        expResult2.getArtists().add(hardwell);
        albumDao.create(expResult2);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());
        assertNotNull(expResult2.getId());
        
        List<Album> resultList = albumDao.getByArtist(tiesto);
        assertEquals(1, resultList.size());
        List<Album> tiestosList = new ArrayList<Album>();
        tiestosList.add(expResult);
        assertEquals(tiestosList, resultList);
        
        List<Album> resultList2 = albumDao.getByArtist(hardwell);
        List<Album> hardwellList = new ArrayList<Album>();
        hardwellList.add(expResult);
        hardwellList.add(expResult2);
        assertEquals(2, resultList2.size());
        assertEquals(hardwellList, resultList2);

        /*
        assertEquals(expResult.getTitle(), result.getTitle());
        assertEquals(expResult.getCover(), result.getCover());
        assertEquals(expResult.getReleaseDate(), result.getReleaseDate());
        assertEquals(expResult.getArtists(), result.getArtists());
        */
    }
    
    public void testGetAlbumByReleaseYear(){
        em.getTransaction().begin();
        Album expResult = new Album("Club Life", "cover.jpg", new DateTime(2011,10,1,0,0), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(new Artist("Tiesto"));
        albumDao.create(expResult);
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        Album expResult2 = new Album("Club Life Alfa", "cover1.jpg", new DateTime(2011,1,1,0,0), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(new Artist("Tiesto"));
        albumDao.create(expResult2);
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        Album expResult3 = new Album("Club Life Final Release", "cover.jpg", new DateTime(2011,12,31,23,59), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(new Artist("Tiesto"));
        albumDao.create(expResult3);
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        Album expResult4 = new Album("Club Life Final Release 2010", "cover.jpg", new DateTime(2010,12,31,23,59), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(new Artist("Tiesto"));
        albumDao.create(expResult4);
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        Album expResult5 = new Album("Club Life Alfa 2012", "cover.jpg", new DateTime(2012,1,1,0,0), new ArrayList<Song>(), "nehehe",
                                      new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(new Artist("Tiesto"));
        albumDao.create(expResult5);
        em.getTransaction().commit();
        em.clear();
        
        assertNotNull(expResult.getId());  
        assertNotNull(expResult2.getId());  
        assertNotNull(expResult3.getId());  
        assertNotNull(expResult4.getId());  
        assertNotNull(expResult5.getId());  
        
        List<Album> resultList = albumDao.getByReleaseYear(new Integer(2011));
        assertEquals(3, resultList.size());
        List<Album> twoOelevenList = new ArrayList<Album>();
        twoOelevenList.add(expResult);
        twoOelevenList.add(expResult2);
        twoOelevenList.add(expResult3);

        assertEquals(twoOelevenList, resultList);
        
        List<Album> resultList2 = albumDao.getByReleaseYear(new Integer(2012));
        List<Album> twoOtwelveList = new ArrayList<Album>();
        twoOtwelveList.add(expResult5);
        assertEquals(twoOtwelveList, resultList2);
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
