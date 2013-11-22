//package com.musiclibrary.euphonybusinesslogicimplementation.dao;
//
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
//import java.util.ArrayList;
//import java.util.List;
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertNotNull;
//import static junit.framework.Assert.fail;
//import junit.framework.TestCase;
//import org.joda.time.DateTime;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.dao.DataAccessException;
//
///**
// * Unit tests for Album DAO implementation.
// *
// * @author Sebastian Lazon #395990
// */
//public class AlbumDAOImplTest extends TestCase {
//
//    private AlbumDAO albumDao;
//
//    public AlbumDAOImplTest(String name) {
//        super(name);
//    }
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testApplicationContext.xml");
//        albumDao = (AlbumDAO) applicationContext.getBean("albumDAO");
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }
//
//    /**
//     * Tests of creating new album
//     */
//    public void testCreateAlbum() {
//
//        try {
//            albumDao.create(null);
//            fail("Null album create");
//        } catch (DataAccessException e) {
//            //OK
//        }
//
//        Album album = null;
//        try {
//            albumDao.create(album);
//            fail("Empty album create");
//        } catch (DataAccessException e) {
//            //OK
//        }
//
//        album = new Album(null, "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        try {
//            albumDao.create(album);
//            fail("Album title null");
//        } catch (DataAccessException e) {
//            //OK
//        }
//
//        album = new Album("", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        try {
//            albumDao.create(album);
//            fail("Album title empty");
//        } catch (DataAccessException e) {
//            //OK
//        }
//
//        album = new Album("title", "cover.jpg", null, new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        try {
//            albumDao.create(album);
//            fail("Album date null");
//        } catch (DataAccessException e) {
//            //OK
//        }
//
//        album = new Album("title", "cover.jpg", DateTime.now(), null, "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        try {
//            albumDao.create(album);
//            fail("Album songs null");
//        } catch (DataAccessException e) {
//            //OK
//        }
//
//        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", null, new ArrayList<Genre>());
//        try {
//            albumDao.create(album);
//            fail("Album artists null");
//        } catch (DataAccessException e) {
//            //OK
//        }
//
//        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), null);
//        try {
//            albumDao.create(album);
//            fail("Album genres null");
//        } catch (DataAccessException e) {
//            //OK
//        }
//
//        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        albumDao.create(album);
//        Long id = album.getId();
//        assertNotNull(id);
//        assertDeepEquals(album, albumDao.getById(id));
//
//        try {
//            albumDao.create(album);
//            fail("Album already in db");
//        } catch (DataAccessException e) {
//            //OK
//        }
//
//        /*
//         List<Artist> artists = new ArrayList();
//         List<Genre> genres = new ArrayList();
//         artists.add(new Artist("Iron Maiden"));
//         genres.add(new Genre("heavy metal"));
//         album = new Album("The Number of the Beast", "cover1.jpg", DateTime.now(), new ArrayList<Song>(), "comment1", artists, genres);
//        
//         List<Song> songs = new ArrayList();
//         songs.add(new Song("The Prisoner",320,2,"comment",genre,album,artist));*/
//    }
//
//    /**
//     * Tests of getting an album
//     */
//    public void testGetAlbum() {
//
//        try {
//            albumDao.getById(null);
//            fail("null id");
//        } catch (DataAccessException ex) {
//        }
//
//        Album album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//
//        try {
//            albumDao.getById(album.getId());
//            fail("id not in db");
//        } catch (DataAccessException ex) {
//        }
//
//        albumDao.create(album);
//        assertDeepEquals(album, albumDao.getById(album.getId()));
//
//    }
//
//    /**
//     * Tests of updating album
//     */
//    public void testUpdateAlbum() {
//        try {
//            albumDao.update(null);
//            fail("null album update");
//        } catch (DataAccessException ex) {
//        }
//
//        try {
//            albumDao.update(new Album());
//            fail("empty album update");
//        } catch (DataAccessException ex) {
//        }
//
//        Album album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        try {
//            albumDao.update(album);
//            fail("Item not in db");
//        } catch (DataAccessException ex) {
//        }
//        albumDao.create(album);
//        albumDao.update(album);
//        assertDeepEquals(album, albumDao.getById(album.getId()));
//
//        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        albumDao.create(album);
//        album.setTitle("title2");
//        albumDao.update(album);
//        assertDeepEquals(album, albumDao.getById(album.getId()));
//
//        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        albumDao.create(album);
//        album.setCover("cover2.jpg");
//        albumDao.update(album);
//        assertDeepEquals(album, albumDao.getById(album.getId()));
//
//        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        albumDao.create(album);
//        album.setReleaseDate(DateTime.now());
//        albumDao.update(album);
//        assertDeepEquals(album, albumDao.getById(album.getId()));
//
//        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        albumDao.create(album);
//        album.setComment("comment2");
//        albumDao.update(album);
//        assertDeepEquals(album, albumDao.getById(album.getId()));
//
//    }
//
//    /*
//     * Testing of deleting album
//     */
//    public void testDeleteAlbum() {
//        try {
//            albumDao.delete(null);
//            fail("deleting null album");
//        } catch (DataAccessException ex) {
//        }
//
//        Album album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        try {
//            albumDao.delete(album);
//            fail("album not in db");
//        } catch (DataAccessException ex) {
//        }
//
//        albumDao.create(album);
//        Long id = album.getId();
//        albumDao.delete(album);
//        assertNull(albumDao.getById(id));
//    }
//
//    /*
//     * Testing of getting all albums
//     */
//    public void testGetAllAlbums() {
//        assertTrue(albumDao.getAll().isEmpty());
//
//        Album album1 = new Album("title1", "cover1.jpg", DateTime.now(), new ArrayList<Song>(), "comment1", new ArrayList<Artist>(), new ArrayList<Genre>());
//        Album album2 = new Album("title2", "cover2.jpg", DateTime.now(), new ArrayList<Song>(), "comment2", new ArrayList<Artist>(), new ArrayList<Genre>());
//
//        List<Album> albums = new ArrayList();
//        albums.add(album1);
//        albums.add(album2);
//        
//        albumDao.create(album1);
//        albumDao.create(album2);      
//
//        assertEquals(albums, albumDao.getAll());
//    }
//
//    /*
//     * Get with null name
//     */
//    public void testGetByTitleWithNullName() {
//        
//        try {
//            albumDao.getByTitle(null);              //title is null
//        } catch (DataAccessException e) {
//            //ok
//        }
//        
//        
//    }
//
//    public void testGetArtistByNameWithNotAssignedName() {
//        
//        Album nullResult = albumDao.getByTitle("Mirage");              //getAlbumByName with not assigned name, should return null
//        
//        
//        assertNull(nullResult);
//    }
//
//    public void testGetAlbumByTitle() {
//
//        
//        Album expResult = new Album("Kaleidoscope", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        expResult.getArtists().add(new Artist("Tiesto"));
//        albumDao.create(expResult);
//        
//        
//
//        assertNotNull(expResult.getId());
//
//        Album result = albumDao.getByTitle(expResult.getTitle());              //correct
//        //assertDeepEquals(expResult, result);
//        assertEquals(expResult.getTitle(), result.getTitle());
//        assertEquals(expResult.getCover(), result.getCover());
//        assertEquals(expResult.getReleaseDate(), result.getReleaseDate());
//        //assertEquals(expResult.getArtists(), result.getArtists());
//        assertEquals(expResult.getComment(), result.getComment());
//        //assertEquals(expResult.getSongs(), result.getSongs());
//        //assertEquals(expResult.getGenres(), result.getGenres());
//    }
//
//    public void testGetAlbumByGenre() {
//        
//        Album expResult = new Album("Kaleidoscope", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        expResult.getArtists().add(new Artist("Tiesto"));
//        Genre tranceGen = new Genre("Trance");
//        Genre technoGen = new Genre("Techno");
//        Genre houseGen = new Genre("House");
//        expResult.getGenres().add(tranceGen);
//        expResult.getGenres().add(houseGen);
//        expResult.getGenres().add(technoGen);
//        albumDao.create(expResult);
//        
//        //
//
//        
//        Album expResult2 = new Album("Mirage", "cover1.jpg", new DateTime(2010, 1, 1, 0, 0), new ArrayList<Song>(), "hehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        expResult2.getArtists().add(new Artist("Armin Van Buuren"));
//        expResult2.getGenres().add(tranceGen);
//        expResult2.getGenres().add(houseGen);
//        albumDao.create(expResult2);
//        
//        
//
//        assertNotNull(expResult.getId());
//        assertNotNull(expResult2.getId());
//
//        List<Album> resultList = albumDao.getByGenre(technoGen);
//        assertEquals(1, resultList.size());
//        List<Album> technoList = new ArrayList<Album>();
//        technoList.add(expResult);
//        assertEquals(technoList, resultList);
//
//        List<Album> resultList2 = albumDao.getByGenre(houseGen);
//        List<Album> houseList = new ArrayList<Album>();
//        houseList.add(expResult);
//        houseList.add(expResult2);
//        assertEquals(2, resultList2.size());
//        assertEquals(houseList, resultList2);
//
//        /*
//         assertEquals(expResult.getTitle(), result.getTitle());
//         assertEquals(expResult.getCover(), result.getCover());
//         assertEquals(expResult.getReleaseDate(), result.getReleaseDate());
//         assertEquals(expResult.getArtists(), result.getArtists());
//         */
//    }
//
//    public void testGetAlbumByArtist() {
//        
//        Album expResult = new Album("Spaceman", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        Artist tiesto = new Artist("Tiesto");
//        Artist hardwell = new Artist("Hardwell");
//        Artist dyro = new Artist("Dyro");
//
//        expResult.getArtists().add(tiesto);
//        expResult.getArtists().add(hardwell);
//        expResult.getArtists().add(dyro);
//        albumDao.create(expResult);
//        
//
//        
//        Album expResult2 = new Album("Imagine", "cover1.jpg", new DateTime(2010, 1, 1, 0, 0), new ArrayList<Song>(), "hehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        expResult2.getArtists().add(dyro);
//        expResult2.getArtists().add(hardwell);
//        albumDao.create(expResult2);
//        
//        
//
//        assertNotNull(expResult.getId());
//        assertNotNull(expResult2.getId());
//
//        List<Album> resultList = albumDao.getByArtist(tiesto);
//        assertEquals(1, resultList.size());
//        List<Album> tiestosList = new ArrayList<Album>();
//        tiestosList.add(expResult);
//        assertEquals(tiestosList, resultList);
//
//        List<Album> resultList2 = albumDao.getByArtist(hardwell);
//        List<Album> hardwellList = new ArrayList<Album>();
//        hardwellList.add(expResult);
//        hardwellList.add(expResult2);
//        assertEquals(2, resultList2.size());
//        assertEquals(hardwellList, resultList2);
//
//        /*
//         assertEquals(expResult.getTitle(), result.getTitle());
//         assertEquals(expResult.getCover(), result.getCover());
//         assertEquals(expResult.getReleaseDate(), result.getReleaseDate());
//         assertEquals(expResult.getArtists(), result.getArtists());
//         */
//    }
//
//    public void testGetAlbumByReleaseYear() {
//        
//        Album expResult = new Album("Club Life", "cover.jpg", new DateTime(2011, 10, 1, 0, 0), new ArrayList<Song>(), "nehehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        expResult.getArtists().add(new Artist("Tiesto"));
//        albumDao.create(expResult);
//        
//        
//
//        
//        Album expResult2 = new Album("Club Life Alfa", "cover1.jpg", new DateTime(2011, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        expResult.getArtists().add(new Artist("Tiesto"));
//        albumDao.create(expResult2);
//        
//        
//
//        
//        Album expResult3 = new Album("Club Life Final Release", "cover.jpg", new DateTime(2011, 12, 31, 23, 59), new ArrayList<Song>(), "nehehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        expResult.getArtists().add(new Artist("Tiesto"));
//        albumDao.create(expResult3);
//        
//        
//
//        
//        Album expResult4 = new Album("Club Life Final Release 2010", "cover.jpg", new DateTime(2010, 12, 31, 23, 59), new ArrayList<Song>(), "nehehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        expResult.getArtists().add(new Artist("Tiesto"));
//        albumDao.create(expResult4);
//        
//        
//
//        
//        Album expResult5 = new Album("Club Life Alfa 2012", "cover.jpg", new DateTime(2012, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
//                new ArrayList<Artist>(), new ArrayList<Genre>());
//        expResult.getArtists().add(new Artist("Tiesto"));
//        albumDao.create(expResult5);
//        
//        
//
//        assertNotNull(expResult.getId());
//        assertNotNull(expResult2.getId());
//        assertNotNull(expResult3.getId());
//        assertNotNull(expResult4.getId());
//        assertNotNull(expResult5.getId());
//
//        List<Album> resultList = albumDao.getByReleaseYear(new Integer(2011));
//        assertEquals(3, resultList.size());
//        List<Album> twoOelevenList = new ArrayList<Album>();
//        twoOelevenList.add(expResult);
//        twoOelevenList.add(expResult2);
//        twoOelevenList.add(expResult3);
//
//        assertEquals(twoOelevenList, resultList);
//
//        List<Album> resultList2 = albumDao.getByReleaseYear(new Integer(2012));
//        List<Album> twoOtwelveList = new ArrayList<Album>();
//        twoOtwelveList.add(expResult5);
//        assertEquals(twoOtwelveList, resultList2);
//    }
//
//    private void assertDeepEquals(Album a1, Album a2) {
//        assertEquals(a1.getId(), a2.getId());
//        assertEquals(a1.getArtists(), a2.getArtists());
//        assertEquals(a1.getComment(), a2.getComment());
//        assertEquals(a1.getCover(), a2.getCover());
//        assertEquals(a1.getGenres(), a2.getGenres());
//        assertEquals(a1.getReleaseDate(), a2.getReleaseDate());
//        assertEquals(a1.getSongs(), a2.getSongs());
//        assertEquals(a1.getTitle(), a2.getTitle());
//    }
//}
