package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.SongDAOImpl;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Song;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Unit tests for Song DAO implementation.
 *
 * @author Branislav Novotny #396152
 */
public class SongDAOImplTest extends TestCase {

    private EntityManagerFactory emf;
    private EntityManager em;
    private SongDAOImpl songDAOImpl;

    public SongDAOImplTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        songDAOImpl = new SongDAOImpl(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of create song
     */
    public void testCreateSong() {

        Song song = new Song("Salalaj", 320, 1, "Toto si spievam ked som dobre nehehee...", new Genre(), new Album(), new Artist());
        em.getTransaction().begin();
        songDAOImpl.create(song);
        em.getTransaction().commit();

        Long id = song.getId();
        assertNotNull(id);
        Song song2 = songDAOImpl.getById(id);
        assertDeepEquals(song, song2);

        em.clear();

        //test null song
        em.getTransaction().begin();
        try {
            songDAOImpl.create(null);
            fail("null song!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test song with not null id
        em.getTransaction().begin();
        try {
            Song s = new Song("Salalaj", 300, 1, "nehehe", new Genre(), new Album(), new Artist());
            s.setId(new Long(1l));
            songDAOImpl.create(s);
            fail("song id set!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test Create Song With Null Attributes
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song(null, 320, 1, null, null, null, null));
            fail("song with null attributes!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null title
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with null title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with empty title
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with empty title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null genre 
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
            fail("song with null genre!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null album
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
            fail("song with null album!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        //test with null artist
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
            fail("song with null artist!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative track number
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with zero bitrate
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with zero bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative bitrate
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with bitrate above 2500
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with 2500+ bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null comment
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("Salalaj", 320, 1, null, new Genre(), new Album(), new Artist()));
        } catch (IllegalArgumentException ex) {
            fail("song with null comment is OK!");
        }
        em.getTransaction().commit();
        em.clear();

        //test with empty comment
        em.getTransaction().begin();
        try {
            songDAOImpl.create(new Song("Salalaj", 320, 1, "", new Genre(), new Album(), new Artist()));
        } catch (IllegalArgumentException ex) {
            fail("song with empty comment is OK!");
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
            songDAOImpl.update(null);
            fail("null song update!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //testCreateSongWithNullAttributes
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song(null, 320, 1, null, null, null, null));
            fail("song with null attributes!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null title
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with null title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with empty title
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with empty title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null genre 
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
            fail("song with null genre!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null album
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
            fail("song with null album!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        //test with null artist
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
            fail("song with null album!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative track number
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with zero bitrate
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with zero bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative bitrate
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song("Salalaj", -1234, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with bitrate above 2500
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with 2500+ bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test song with null id
        em.getTransaction().begin();
        try {
            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            em.getTransaction().commit();
            fail("null id update!");

        } catch (IllegalArgumentException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        // test with id doesnt assigned by db
        em.getTransaction().begin();
        try {
            Song song = new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist());
            song.setId(new Long(1000));
            songDAOImpl.update(song);
            fail("no assigned id by db update!");

        } catch (IllegalArgumentException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        //test update song

        Song blessed = new Song("Tim Hangs - Blessed", 320, 1, "Yeah", new Genre(), new Album(), new Artist());
        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", new Genre(), new Album(), new Artist());
        em.getTransaction().begin();
        songDAOImpl.create(blessed);
        em.getTransaction().commit();
        dalibomba.setId(blessed.getId());
        dalibomba.getGenre().setId(blessed.getGenre().getId());
        dalibomba.getAlbum().setId(blessed.getAlbum().getId());

        em.getTransaction().begin();
        songDAOImpl.update(dalibomba);                //OK
        em.getTransaction().commit();
        assertDeepEquals(dalibomba, songDAOImpl.getById(blessed.getId()));
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
            songDAOImpl.delete(null);
            fail("null song delete!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //testCreateSongWithNullAttributes
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song(null, 320, 1, null, null, null, null));
            fail("song with null attributes!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null title
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song(null, 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with null title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with empty title
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song("", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with empty title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null genre 
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", null, new Album(), new Artist()));
            fail("song with null genre!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null album
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null, new Artist()));
            fail("song with null album!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        //test with null artist
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), null));
            fail("song with null artist!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative track number
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with zero bitrate
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with zero bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with negative bitrate
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with bitrate above 2500
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("song with 2500+ bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test song with null id
        em.getTransaction().begin();
        try {
            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist()));
            fail("null id delete!");

        } catch (IllegalArgumentException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        // test with id doesnt assigned by db
        em.getTransaction().begin();
        try {
            Song song = new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album(), new Artist());
            song.setId(new Long(1000));
            songDAOImpl.delete(song);
            fail("no assigned id by db delete!");

        } catch (IllegalArgumentException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        //test delete song

        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", new Genre(), new Album(), new Artist());
        em.getTransaction().begin();
        songDAOImpl.create(dalibomba);
        em.getTransaction().commit();

        em.getTransaction().begin();
        songDAOImpl.delete(dalibomba);
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
            songDAOImpl.getById(null);
            fail("both null get!");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        //test with null id
        em.getTransaction().begin();
        try {
            songDAOImpl.getById(null);
            fail("id null get!");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        //test id not assigned by db
        em.getTransaction().begin();
        Song res = songDAOImpl.getById(new Long(6543));
        em.getTransaction().commit();
        assertNull(res);

        //test get by id
        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", new Genre(), new Album(), new Artist());
        em.getTransaction().begin();
        songDAOImpl.create(dalibomba);
        em.getTransaction().commit();
        assertNotNull(dalibomba.getId());
        Long songId = dalibomba.getId();

        Song res1 = songDAOImpl.getById(songId);
        assertDeepEquals(dalibomba, res1);
        em.clear();
    }

    private void assertDeepEquals(Song expected, Song actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getBitrate(), actual.getBitrate());
        assertEquals(expected.getComment(), actual.getComment());
        assertEquals(expected.getTrackNumber(), actual.getTrackNumber());
        assertEquals(expected.getGenre(), actual.getGenre());
        assertEquals(expected.getAlbum(), actual.getAlbum());
    }
}
