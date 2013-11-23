//package com.musiclibrary.euphonybusinesslogicimplementation.dao;
//
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
//import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
//import java.util.ArrayList;
//import java.util.List;
//import static junit.framework.Assert.assertEquals;
//import static junit.framework.Assert.assertTrue;
//import static junit.framework.Assert.fail;
//import junit.framework.TestCase;
//import org.joda.time.DateTime;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * Unit tests for Song DAO implementation.
// *
// * @author Branislav Novotny #396152
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/testApplicationContext.xml" ) 
//@Transactional
//public class SongDAOImplTest extends TestCase {
//
//    @Autowired
//    private SongDAO songDAO;
//    
//    @Autowired
//    private ArtistDAO artistDAO;
//    
//    @Autowired
//    private GenreDAO genreDAO;
//    
//    @Autowired
//    private AlbumDAO albumDAO;
//
//    public void setSongDAO(SongDAO songDAO) {
//        this.songDAO = songDAO;
//    }
//
//    public void setArtistDAO(ArtistDAO artistDAO) {
//        this.artistDAO = artistDAO;
//    }
//
//    public void setGenreDAO(GenreDAO genreDAO) {
//        this.genreDAO = genreDAO;
//    }
//
//    public void setAlbumDAO(AlbumDAO albumDAO) {
//        this.albumDAO = albumDAO;
//    }
//
//    /**
//     * Test of create song
//     */
//    @Test
//    public void testCreateSong() {
//
//        Genre g = new Genre("m"); 
//        genreDAO.create(g);
//        Album al = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        albumDAO.create(al);
//        Artist ar = new Artist("a");
//        artistDAO.create(ar);
//        Song song = new Song("Salalaj", 320, 1, "Toto si spievam ked som dobre nehehee...", g,al,ar);
//        
//        songDAO.create(song);
//        
//
//        Long id = song.getId();
//        assertNotNull(id);
//        Song song2 = songDAO.getById(id);
//        assertDeepEquals(song, song2);
//
//        
//        
//        //test null song
//        
//        try {
//            songDAO.create(null);
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
//            songDAO.create(s);
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
//            songDAO.create(new Song(null, 320, 1, null, null, null, null));
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
//            songDAO.create(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.create(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.create(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
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
//            songDAO.create(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
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
//            songDAO.create(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
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
//            songDAO.create(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.create(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.create(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.create(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//    @Test
//    public void testUpdateSong() {
//
//        //Song s = new Song("Salalaj", 320, 1, "", new Genre(), new Album());
//        //s.setId(new Long(1l));
//
//        //test null song
//        
//        try {
//            songDAO.update(null);
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
//            songDAO.update(new Song(null, 320, 1, null, null, null, null));
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
//            songDAO.update(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.update(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.update(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
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
//            songDAO.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
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
//            songDAO.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
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
//            songDAO.update(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.update(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.update(new Song("Salalaj", -1234, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.update(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.update(song);
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
//    @Test
//    public void testDeleteSong() {
//
//        //Song s = new Song("Salalaj", 320, 1, "", new Genre(), new Album());
//        //s.setId(new Long(1l));
//
//        //test null song
//        
//        try {
//            songDAO.delete(null);
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
//            songDAO.delete(new Song(null, 320, 1, null, null, null, null));
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
//            songDAO.delete(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.delete(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.delete(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
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
//            songDAO.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
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
//            songDAO.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
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
//            songDAO.delete(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.delete(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.delete(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.delete(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
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
//            songDAO.delete(song);
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
//        songDAO.create(dalibomba);
//        
//
//        
//        songDAO.delete(dalibomba);
//        
//        
//    }
//
//    /**
//     * Test of getById method
//     */
//    @Test
//    public void testGetSongById() {
//
//        //test with null both
//        
//        try {
//            songDAO.getById(null);
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
//            songDAO.getById(null);
//            fail("id null get!");
//        } catch (DataAccessException ex) {
//            //ok
//        }
//        
//        
//
//        //test id not assigned by db
//        
//        Song res = songDAO.getById(new Long(6543));
//        
//        assertNull(res);
//
//        //test get by id
//        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", new Genre(), new Album(), new Artist());
//        
//        songDAO.create(dalibomba);
//        
//        assertNotNull(dalibomba.getId());
//        Long songId = dalibomba.getId();
//
//        Song res1 = songDAO.getById(songId);
//        assertDeepEquals(dalibomba, res1);
//        
//    }
//    
//    @Test
//    public void testGetByTitle(){
//        assertTrue(songDAO.getByTitle("Prisoner").isEmpty());
//        
//        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", new Genre(), new Album(), new Artist());
//        
//        songDAO.create(song);
//        
//        
//        List<Song> songs1 = new ArrayList();
//        List<Song> songs2 = new ArrayList();
//        
//        songs1.add(song);
//        songs2 = songDAO.getByTitle("Prisoner");
//        
//        assertEquals(songs1, songs2);
//        
//        
//        songDAO.delete(song);
//        
//        assertTrue(songDAO.getByTitle("Prisoner").isEmpty());
//    }
//    
//    @Test
//    public void testGetByAlbum(){
//        Album album = new Album("Title","cover.jpg", DateTime.now(),new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
//        
//        albumDAO.create(album);
//        
//        assertTrue(songDAO.getByAlbum(album).isEmpty());
//
//        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", new Genre(), album, new Artist());
//        
//        songDAO.create(song);
//        
//        
//        List<Song> songs1 = new ArrayList();
//        List<Song> songs2 = new ArrayList();
//        
//        songs1.add(song);
//        songs2 = songDAO.getByAlbum(album);
//        
//        assertEquals(songs1, songs2);
//        
//        
//        songDAO.delete(song);
//        
//        assertTrue(songDAO.getByAlbum(album).isEmpty());
//    }
//    
//    @Test
//    public void testGetByGenre(){
//        Genre genre = new Genre("metal");
//        
//        genreDAO.create(genre);
//        
//        assertTrue(songDAO.getByGenre(genre).isEmpty());
//        
//        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", genre, new Album(), new Artist());
//        
//        songDAO.create(song);
//        
//        
//        List<Song> songs1 = new ArrayList();
//        List<Song> songs2 = new ArrayList();
//        
//        songs1.add(song);
//        songs2 = songDAO.getByGenre(genre);
//        
//        assertEquals(songs1, songs2);
//        
//        
//        songDAO.delete(song);
//        
//        assertTrue(songDAO.getByGenre(genre).isEmpty());
//    }
//    
//    @Test
//    public void testGetByArtist(){
//        Artist artist = new Artist("Iron Maiden");
//        
//        artistDAO.create(artist);
//        
//        assertTrue(songDAO.getByArtist(artist).isEmpty());
//        
//        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", new Genre(), new Album(), artist);
//        
//        songDAO.create(song);
//        
//        
//        List<Song> songs1 = new ArrayList();
//        List<Song> songs2 = new ArrayList();
//        
//        songs1.add(song);
//        songs2 = songDAO.getByArtist(artist);
//        
//        assertEquals(songs1, songs2);
//        
//        
//        songDAO.delete(song);
//        
//        assertTrue(songDAO.getByArtist(artist).isEmpty());
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
