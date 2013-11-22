//package com.musiclibrary.euphonybusinesslogicimplementation.dao;
//
//import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.AlbumDAOImpl;
//import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.ArtistDAOImpl;
//import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.GenreDAOImpl;
//import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.SongDAOImpl;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertTrue;
//import static junit.framework.Assert.fail;
//import junit.framework.TestCase;
//import org.joda.time.DateTime;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.dao.DataAccessException;
//
///**
// * Unit tests for Song DAO implementation.
// *
// * @author Branislav Novotny #396152
// */
//public class SongDAOImplTest extends TestCase {
//
//    private SongDAO songDAOImpl;
//    private ArtistDAO artistDao;
//    private GenreDAO genreDao;
//    private AlbumDAO albumDao;
//
//    public SongDAOImplTest(String name) {
//        super(name);
//    }
//
//    @Override
//    protected void setUp() throws Exception {
//        super.setUp();
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("testApplicationContext.xml");
//        songDAOImpl = (SongDAO) applicationContext.getBean("songDAO");
//        artistDao = (ArtistDAO) applicationContext.getBean("artistDAO");
//        genreDao = (GenreDAO) applicationContext.getBean("genreDAO");
//        albumDao = (AlbumDAO) applicationContext.getBean("albumDAO");
//    }
//
//    @Override
//    protected void tearDown() throws Exception {
//        super.tearDown();
//    }
//
//    /**
//     * Test of create song
//     */
//    public void testCreateSong() {
//
//        Song song = new Song("Salalaj", 320, 1, "Toto si spievam ked som dobre nehehee...", new Genre(), new Album(), new Artist());
//        
//        songDAOImpl.create(song);
//        
//
//        Long id = song.getId();
//        assertNotNull(id);
//        Song song2 = songDAOImpl.getById(id);
//        assertDeepEquals(song, song2);
//
//        
//        
//        //test null song
//        
//        try {
//            songDAOImpl.create(null);
//            fail("null song!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test song with not null id
//        
//        try {
//            Song s = new Song("Salalaj", 300, 1, "nehehe", new Genre(), new Album(), new Artist());
//            s.setId(new Long(1l));
//            songDAOImpl.create(s);
//            fail("song id set!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test Create Song With Null Attributes
//        
//        try {
//            songDAOImpl.create(new Song(null, 320, 1, null, null, null, null));
//            fail("song with null attributes!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with null title
//        
//        try {
//            songDAOImpl.create(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with null title!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with empty title
//        
//        try {
//            songDAOImpl.create(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with empty title!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with null genre 
//        
//        try {
//            songDAOImpl.create(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
//            fail("song with null genre!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with null album
//        
//        try {
//            songDAOImpl.create(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
//            fail("song with null album!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//        
//        //test with null artist
//        
//        try {
//            songDAOImpl.create(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
//            fail("song with null artist!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with negative track number
//        
//        try {
//            songDAOImpl.create(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with negative bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with zero bitrate
//        
//        try {
//            songDAOImpl.create(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with zero bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with negative bitrate
//        
//        try {
//            songDAOImpl.create(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with negative bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with bitrate above 2500
//        
//        try {
//            songDAOImpl.create(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with 2500+ bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//    }
//
//    /**
//     * Test of update song
//     */
//    public void testUpdateSong() {
//
//        //Song s = new Song("Salalaj", 320, 1, "", new Genre(), new Album());
//        //s.setId(new Long(1l));
//
//        //test null song
//        
//        try {
//            songDAOImpl.update(null);
//            fail("null song update!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //testCreateSongWithNullAttributes
//        
//        try {
//            songDAOImpl.update(new Song(null, 320, 1, null, null, null, null));
//            fail("song with null attributes!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with null title
//        
//        try {
//            songDAOImpl.update(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with null title!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with empty title
//        
//        try {
//            songDAOImpl.update(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with empty title!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with null genre 
//        
//        try {
//            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
//            fail("song with null genre!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with null album
//        
//        try {
//            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
//            fail("song with null album!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//        
//        //test with null artist
//        
//        try {
//            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
//            fail("song with null album!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with negative track number
//        
//        try {
//            songDAOImpl.update(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with negative bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with zero bitrate
//        
//        try {
//            songDAOImpl.update(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with zero bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with negative bitrate
//        
//        try {
//            songDAOImpl.update(new Song("Salalaj", -1234, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with negative bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with bitrate above 2500
//        
//        try {
//            songDAOImpl.update(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with 2500+ bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test song with null id
//        
//        try {
//            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            
//            fail("null id update!");
//
//        } catch (DataAccessException ex) {
//            //ok
//        }
//        
//        
//
//        // test with id doesnt assigned by db
//        
//        try {
//            Song song = new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist());
//            song.setId(new Long(1000));
//            songDAOImpl.update(song);
//            fail("no assigned id by db update!");
//
//        } catch (DataAccessException ex) {
//            //ok
//        }
//        
//        
//    }
//
//    /**
//     * Test delete song
//     */
//    public void testDeleteSong() {
//
//        //Song s = new Song("Salalaj", 320, 1, "", new Genre(), new Album());
//        //s.setId(new Long(1l));
//
//        //test null song
//        
//        try {
//            songDAOImpl.delete(null);
//            fail("null song delete!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //testCreateSongWithNullAttributes
//        
//        try {
//            songDAOImpl.delete(new Song(null, 320, 1, null, null, null, null));
//            fail("song with null attributes!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with null title
//        
//        try {
//            songDAOImpl.delete(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with null title!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with empty title
//        
//        try {
//            songDAOImpl.delete(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with empty title!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with null genre 
//        
//        try {
//            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
//            fail("song with null genre!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with null album
//        
//        try {
//            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
//            fail("song with null album!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//        
//        //test with null artist
//        
//        try {
//            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
//            fail("song with null artist!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with negative track number
//        
//        try {
//            songDAOImpl.delete(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with negative bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with zero bitrate
//        
//        try {
//            songDAOImpl.delete(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with zero bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with negative bitrate
//        
//        try {
//            songDAOImpl.delete(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with negative bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test with bitrate above 2500
//        
//        try {
//            songDAOImpl.delete(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("song with 2500+ bitrate!");
//        } catch (DataAccessException ex) {
//            //OK
//        }
//        
//        
//
//        //test song with null id
//        
//        try {
//            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
//            fail("null id delete!");
//
//        } catch (DataAccessException ex) {
//            //ok
//        }
//        
//        
//
//        // test with id doesnt assigned by db
//        
//        try {
//            Song song = new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist());
//            song.setId(new Long(1000));
//            songDAOImpl.delete(song);
//            fail("no assigned id by db delete!");
//
//        } catch (DataAccessException ex) {
//            //ok
//        }
//        
//        
//
//        //test delete song
//
//        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", new Genre(), new Album(), new Artist());
//        
//        songDAOImpl.create(dalibomba);
//        
//
//        
//        songDAOImpl.delete(dalibomba);
//        
//        
//    }
//
//    /**
//     * Test of getById method
//     */
//    public void testGetSongById() {
//
//        //test with null both
//        
//        try {
//            songDAOImpl.getById(null);
//            fail("both null get!");
//        } catch (DataAccessException ex) {
//            //ok
//        }
//        
//        
//
//        //test with null id
//        
//        try {
//            songDAOImpl.getById(null);
//            fail("id null get!");
//        } catch (DataAccessException ex) {
//            //ok
//        }
//        
//        
//
//        //test id not assigned by db
//        
//        Song res = songDAOImpl.getById(new Long(6543));
//        
//        assertNull(res);
//
//        //test get by id
//        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", new Genre(), new Album(), new Artist());
//        
//        songDAOImpl.create(dalibomba);
//        
//        assertNotNull(dalibomba.getId());
//        Long songId = dalibomba.getId();
//
//        Song res1 = songDAOImpl.getById(songId);
//        assertDeepEquals(dalibomba, res1);
//        
//    }
//    
//    public void testGetByTitle(){
//        assertTrue(songDAOImpl.getByTitle("Prisoner").isEmpty());
//        
//        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", new Genre(), new Album(), new Artist());
//        
//        songDAOImpl.create(song);
//        
//        
//        List<Song> songs1 = new ArrayList();
//        List<Song> songs2 = new ArrayList();
//        
//        songs1.add(song);
//        songs2 = songDAOImpl.getByTitle("Prisoner");
//        
//        assertEquals(songs1, songs2);
//        
//        
//        songDAOImpl.delete(song);
//        
//        assertTrue(songDAOImpl.getByTitle("Prisoner").isEmpty());
//    }
//    
//    public void testGetByAlbum(){
//        Album album = new Album("Title","cover.jpg", DateTime.now(),new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        
//        albumDao.create(album);
//        
//        assertTrue(songDAOImpl.getByAlbum(album).isEmpty());
//
//        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", new Genre(), album, new Artist());
//        
//        songDAOImpl.create(song);
//        
//        
//        List<Song> songs1 = new ArrayList();
//        List<Song> songs2 = new ArrayList();
//        
//        songs1.add(song);
//        songs2 = songDAOImpl.getByAlbum(album);
//        
//        assertEquals(songs1, songs2);
//        
//        
//        songDAOImpl.delete(song);
//        
//        assertTrue(songDAOImpl.getByAlbum(album).isEmpty());
//    }
//    
//    public void testGetByGenre(){
//        Genre genre = new Genre("metal");
//        
//        genreDao.create(genre);
//        
//        assertTrue(songDAOImpl.getByGenre(genre).isEmpty());
//        
//        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", genre, new Album(), new Artist());
//        
//        songDAOImpl.create(song);
//        
//        
//        List<Song> songs1 = new ArrayList();
//        List<Song> songs2 = new ArrayList();
//        
//        songs1.add(song);
//        songs2 = songDAOImpl.getByGenre(genre);
//        
//        assertEquals(songs1, songs2);
//        
//        
//        songDAOImpl.delete(song);
//        
//        assertTrue(songDAOImpl.getByGenre(genre).isEmpty());
//    }
//    
//    public void testGetByArtist(){
//        Artist artist = new Artist("Iron Maiden");
//        
//        artistDao.create(artist);
//        
//        assertTrue(songDAOImpl.getByArtist(artist).isEmpty());
//        
//        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", new Genre(), new Album(), artist);
//        
//        songDAOImpl.create(song);
//        
//        
//        List<Song> songs1 = new ArrayList();
//        List<Song> songs2 = new ArrayList();
//        
//        songs1.add(song);
//        songs2 = songDAOImpl.getByArtist(artist);
//        
//        assertEquals(songs1, songs2);
//        
//        
//        songDAOImpl.delete(song);
//        
//        assertTrue(songDAOImpl.getByArtist(artist).isEmpty());
//        
//    }
//
//    private void assertDeepEquals(Song expected, Song actual) {
//        assertEquals(expected.getId(), actual.getId());
//        assertEquals(expected.getTitle(), actual.getTitle());
//        assertEquals(expected.getBitrate(), actual.getBitrate());
//        assertEquals(expected.getComment(), actual.getComment());
//        assertEquals(expected.getTrackNumber(), actual.getTrackNumber());
//        assertEquals(expected.getGenre(), actual.getGenre());
//        assertEquals(expected.getAlbum(), actual.getAlbum());
//    }
//}
