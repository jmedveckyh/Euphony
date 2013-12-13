package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.ArtistDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.GenreDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.SongDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;

/**
 * Unit tests for Song DAO implementation.
 *
 * @author Branislav Novotny #396152
 */
public class SongDAOImplTest extends TestCase {

    private EntityManagerFactory emf;
    private EntityManager em;
    private SongDAOImpl songDao;
    private ArtistDAOImpl artistDao;
    private GenreDAOImpl genreDao;
    private AlbumDAOImpl albumDao;

    public SongDAOImplTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        songDao = new SongDAOImpl(em);
        artistDao = new ArtistDAOImpl(em);
        genreDao = new GenreDAOImpl(em);
        albumDao = new AlbumDAOImpl(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of create song
     */
    public void testCreateSong() {

        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", DateTime.now(), "comment");
        Artist artistTemp = new Artist("Ylvis");

        em.getTransaction().begin();
        genreDao.create(genreTemp);
        albumDao.create(albumTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();

        Song song = new Song("Salalaj", 320, 1, "Toto si spievam ked som dobre nehehee...", genreTemp, albumTemp, artistTemp);
        em.getTransaction().begin();
        songDao.create(song);
        em.getTransaction().commit();

        Long id = song.getId();
        assertNotNull(id);
        Song song2 = songDao.getById(id);
        assertDeepEquals(song, song2);

        em.clear();

        //test null song
        em.getTransaction().begin();
        try {
            songDao.create(null);
            fail("null song!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test song with not null id
        em.getTransaction().begin();
        try {
            Song s = new Song("Salalaj", 300, 1, "nehehe", new Genre(), new Album(), new Artist());
            s.setId(new Long(1l));
            songDao.create(s);
            fail("song id set!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test Create Song With Null Attributes
        em.getTransaction().begin();
        try {
            songDao.create(new Song(null, 320, 1, null, null, null, null));
            fail("song with null attributes!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null title
        em.getTransaction().begin();
        try {
            songDao.create(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with null title!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with empty title
        em.getTransaction().begin();
        try {
            songDao.create(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with empty title!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null genre 
        em.getTransaction().begin();
        try {
            songDao.create(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
            fail("song with null genre!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null album
        em.getTransaction().begin();
        try {
            songDao.create(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
            fail("song with null album!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null artist
        em.getTransaction().begin();
        try {
            songDao.create(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
            fail("song with null artist!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative track number
        em.getTransaction().begin();
        try {
            songDao.create(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with zero bitrate
        em.getTransaction().begin();
        try {
            songDao.create(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with zero bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative bitrate
        em.getTransaction().begin();
        try {
            songDao.create(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with bitrate above 2500
        em.getTransaction().begin();
        try {
            songDao.create(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with 2500+ bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

    }

    /**
     * Test of update song
     */
    public void testUpdateSong() {

        //Song s = new Song("Salalaj", 320, 1, "", new Genre(), new Album());
        //s.setId(new Long(1l));

        //test null song
        em.getTransaction().begin();
        try {
            songDao.update(null);
            fail("null song update!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //testCreateSongWithNullAttributes
        em.getTransaction().begin();
        try {
            songDao.update(new Song(null, 320, 1, null, null, null, null));
            fail("song with null attributes!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null title
        em.getTransaction().begin();
        try {
            songDao.update(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with null title!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with empty title
        em.getTransaction().begin();
        try {
            songDao.update(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with empty title!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null genre 
        em.getTransaction().begin();
        try {
            songDao.update(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
            fail("song with null genre!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null album
        em.getTransaction().begin();
        try {
            songDao.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
            fail("song with null album!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null artist
        em.getTransaction().begin();
        try {
            songDao.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
            fail("song with null album!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative track number
        em.getTransaction().begin();
        try {
            songDao.update(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with zero bitrate
        em.getTransaction().begin();
        try {
            songDao.update(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with zero bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative bitrate
        em.getTransaction().begin();
        try {
            songDao.update(new Song("Salalaj", -1234, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with bitrate above 2500
        em.getTransaction().begin();
        try {
            songDao.update(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with 2500+ bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test song with null id
        em.getTransaction().begin();
        try {
            songDao.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            em.getTransaction().commit();
            fail("null id update!");

        } catch (DataAccessException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        // test with id doesnt assigned by db
        em.getTransaction().begin();
        try {
            Song song = new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist());
            song.setId(new Long(1000));
            songDao.update(song);
            fail("no assigned id by db update!");

        } catch (DataAccessException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Test delete song
     */
    public void testDeleteSong() {

        //Song s = new Song("Salalaj", 320, 1, "", new Genre(), new Album());
        //s.setId(new Long(1l));

        //test null song
        em.getTransaction().begin();
        try {
            songDao.delete(null);
            fail("null song delete!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //testCreateSongWithNullAttributes
        em.getTransaction().begin();
        try {
            songDao.delete(new Song(null, 320, 1, null, null, null, null));
            fail("song with null attributes!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null title
        em.getTransaction().begin();
        try {
            songDao.delete(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with null title!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with empty title
        em.getTransaction().begin();
        try {
            songDao.delete(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with empty title!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null genre 
        em.getTransaction().begin();
        try {
            songDao.delete(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
            fail("song with null genre!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null album
        em.getTransaction().begin();
        try {
            songDao.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
            fail("song with null album!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null artist
        em.getTransaction().begin();
        try {
            songDao.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
            fail("song with null artist!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative track number
        em.getTransaction().begin();
        try {
            songDao.delete(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with zero bitrate
        em.getTransaction().begin();
        try {
            songDao.delete(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with zero bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative bitrate
        em.getTransaction().begin();
        try {
            songDao.delete(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with bitrate above 2500
        em.getTransaction().begin();
        try {
            songDao.delete(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with 2500+ bitrate!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test song with null id
        em.getTransaction().begin();
        try {
            songDao.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("null id delete!");

        } catch (DataAccessException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        // test with id doesnt assigned by db
        em.getTransaction().begin();
        try {
            Song song = new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist());
            song.setId(new Long(1000));
            songDao.delete(song);
            fail("no assigned id by db delete!");

        } catch (DataAccessException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        //test delete song
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", DateTime.now(), "comment");
        Artist artistTemp = new Artist("Ylvis");

        em.getTransaction().begin();
        genreDao.create(genreTemp);
        albumDao.create(albumTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();

        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", genreTemp, albumTemp, artistTemp);
        em.getTransaction().begin();
        songDao.create(dalibomba);
        em.getTransaction().commit();

        em.getTransaction().begin();
        songDao.delete(dalibomba);
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Test of getById method
     */
    public void testGetSongById() {

        //test with null both
        em.getTransaction().begin();
        try {
            songDao.getById(null);
            fail("both null get!");
        } catch (DataAccessException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        //test with null id
        em.getTransaction().begin();
        try {
            songDao.getById(null);
            fail("id null get!");
        } catch (DataAccessException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        //test id not assigned by db
        em.getTransaction().begin();
        Song res = songDao.getById(new Long(6543));
        em.getTransaction().commit();
        assertNull(res);

        //test get by id
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", DateTime.now(), "comment");
        Artist artistTemp = new Artist("Ylvis");

        em.getTransaction().begin();
        genreDao.create(genreTemp);
        albumDao.create(albumTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();

        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", genreTemp, albumTemp, artistTemp);
        em.getTransaction().begin();
        songDao.create(dalibomba);
        em.getTransaction().commit();
        assertNotNull(dalibomba.getId());
        Long songId = dalibomba.getId();

        Song res1 = songDao.getById(songId);
        assertDeepEquals(dalibomba, res1);
        em.clear();
    }

    public void testGetByTitle() {
        assertTrue(songDao.getByTitle("Prisoner").isEmpty());

        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", DateTime.now(), "comment");
        Artist artistTemp = new Artist("Ylvis");

        em.getTransaction().begin();
        genreDao.create(genreTemp);
        albumDao.create(albumTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();

        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", genreTemp, albumTemp, artistTemp);
        em.getTransaction().begin();
        songDao.create(song);
        em.getTransaction().commit();

        List<Song> songs1 = new ArrayList();
        List<Song> songs2 = new ArrayList();

        songs1.add(song);
        songs2 = songDao.getByTitle("Prisoner");

        assertEquals(songs1, songs2);

        em.getTransaction().begin();
        songDao.delete(song);
        em.getTransaction().commit();
        assertTrue(songDao.getByTitle("Prisoner").isEmpty());
    }

    public void testGetByAlbum() {

        Album album = new Album("Title", "cover.jpg", DateTime.now(), "comment");
        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        assertTrue(songDao.getByAlbum(album).isEmpty());

        Genre genreTemp = new Genre("Pop");
        Artist artistTemp = new Artist("Ylvis");

        em.getTransaction().begin();
        genreDao.create(genreTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();

        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", genreTemp, album, artistTemp);
        em.getTransaction().begin();
        songDao.create(song);
        em.getTransaction().commit();

        List<Song> songs1 = new ArrayList();
        List<Song> songs2 = new ArrayList();

        songs1.add(song);
        songs2 = songDao.getByAlbum(album);

        assertEquals(songs1, songs2);

        em.getTransaction().begin();
        songDao.delete(song);
        em.getTransaction().commit();
        assertTrue(songDao.getByAlbum(album).isEmpty());
    }

    public void testGetByGenre() {
        Genre genre = new Genre("metal");
        em.getTransaction().begin();
        genreDao.create(genre);
        em.getTransaction().commit();
        assertTrue(songDao.getByGenre(genre).isEmpty());

        Album albumTemp = new Album("The Fox", "cover.jpg", DateTime.now(), "comment");
        Artist artistTemp = new Artist("Ylvis");

        em.getTransaction().begin();
        albumDao.create(albumTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();

        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", genre, albumTemp, artistTemp);
        em.getTransaction().begin();
        songDao.create(song);
        em.getTransaction().commit();

        List<Song> songs1 = new ArrayList();
        List<Song> songs2 = new ArrayList();

        songs1.add(song);
        songs2 = songDao.getByGenre(genre);

        assertEquals(songs1, songs2);

        em.getTransaction().begin();
        songDao.delete(song);
        em.getTransaction().commit();
        assertTrue(songDao.getByGenre(genre).isEmpty());
    }

    public void testGetByArtist() {
        Artist artist = new Artist("Iron Maiden");
        em.getTransaction().begin();
        artistDao.create(artist);
        em.getTransaction().commit();
        assertTrue(songDao.getByArtist(artist).isEmpty());

        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", DateTime.now(), "comment");

        em.getTransaction().begin();
        genreDao.create(genreTemp);
        albumDao.create(albumTemp);
        em.getTransaction().commit();

        Song song = new Song("Prisoner", 320, 2, "Children of the Damned", genreTemp, albumTemp, artist);
        em.getTransaction().begin();
        songDao.create(song);
        em.getTransaction().commit();

        List<Song> songs1 = new ArrayList();
        List<Song> songs2 = new ArrayList();

        songs1.add(song);
        songs2 = songDao.getByArtist(artist);

        assertEquals(songs1, songs2);

        em.getTransaction().begin();
        songDao.delete(song);
        em.getTransaction().commit();

        assertTrue(songDao.getByArtist(artist).isEmpty());
        em.clear();

    }

    public static void assertDeepEquals(Song expected, Song actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getBitrate(), actual.getBitrate());
        assertEquals(expected.getComment(), actual.getComment());
        assertEquals(expected.getTrackNumber(), actual.getTrackNumber());
        assertEquals(expected.getGenre(), actual.getGenre());
        assertEquals(expected.getAlbum(), actual.getAlbum());
    }
}
