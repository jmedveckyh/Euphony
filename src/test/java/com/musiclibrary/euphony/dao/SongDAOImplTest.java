/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.SongDAOImpl;
import com.musiclibrary.euphony.entities.Album;
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
 *
 * @author PC
 */
public class SongDAOImplTest extends TestCase {

    EntityManagerFactory emf;

    public SongDAOImplTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.emf = Persistence.createEntityManagerFactory("testEuphonyPU");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateSong() {

        EntityManager em = emf.createEntityManager();
        SongDAOImpl songDAOImpl = new SongDAOImpl();

        Song song = new Song("Salalaj", 320, 1, "Toto si spievam ked som dobre nehehee...", new Genre(), new Album());
        em.getTransaction().begin();
        songDAOImpl.create(song);
        em.getTransaction().commit();
        em.clear();

        Long id = song.getId();
        assertNotNull(id);
        Song song2 = songDAOImpl.getById(Song.class, id);
        assertEquals(song, song2);

        //test null song
        try {
            songDAOImpl.create(null);
            fail("null song!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test song with not null id
        try {
            Song s = new Song("Salalaj", 300, 1, "nehehe", new Genre(), new Album());
            s.setId(new Long(1l));
            songDAOImpl.create(s);
            fail("song id set!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //testCreateSongWithNullAttributes
        try {
            songDAOImpl.create(new Song(null, 320, 1, null, null, null));
            fail("song with null attributes!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null title
        try {
            songDAOImpl.create(new Song(null, 320, 1, "nehehe", new Genre(), new Album()));
            fail("song with null title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with empty title
        try {
            songDAOImpl.create(new Song("", 320, 1, "nehehe", new Genre(), new Album()));
            fail("song with empty title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null genre 
        try {
            songDAOImpl.create(new Song("Salalaj", 320, 1, "nehehe", null, new Album()));
            fail("song with null genre!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null album
        try {
            songDAOImpl.create(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null));
            fail("song with null album!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with negative track number
        try {
            songDAOImpl.create(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with zero bitrate
        try {
            songDAOImpl.create(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album()));
            fail("song with zero bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with negative bitrate
        try {
            songDAOImpl.create(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with bitrate above 2500
        try {
            songDAOImpl.create(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album()));
            fail("song with 2500+ bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null comment
        try {
            em.getTransaction().begin();
            songDAOImpl.create(new Song("Salalaj", 320, 1, null, new Genre(), new Album()));
            em.getTransaction().commit();
        } catch (IllegalArgumentException ex) {
            fail("song with null comment is OK!");
        }
        em.clear();

        //test with empty comment
        try {
            em.getTransaction().begin();
            songDAOImpl.create(new Song("Salalaj", 320, 1, "", new Genre(), new Album()));
            em.getTransaction().commit();
        } catch (IllegalArgumentException ex) {
            fail("song with empty comment is OK!");
        }
        em.close();
    }

    public void testUpdateSong() {

        EntityManager em = emf.createEntityManager();
        SongDAOImpl songDAOImpl = new SongDAOImpl();
        //Song s = new Song("Salalaj", 320, 1, "", new Genre(), new Album());
        //s.setId(new Long(1l));

        //test null song
        try {
            songDAOImpl.update(null);
            fail("null song update!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //testCreateSongWithNullAttributes
        try {
            songDAOImpl.update(new Song(null, 320, 1, null, null, null));
            fail("song with null attributes!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null title
        try {
            songDAOImpl.update(new Song(null, 320, 1, "nehehe", new Genre(), new Album()));
            fail("song with null title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with empty title
        try {
            songDAOImpl.update(new Song("", 320, 1, "nehehe", new Genre(), new Album()));
            fail("song with empty title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null genre 
        try {
            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", null, new Album()));
            fail("song with null genre!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null album
        try {
            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null));
            fail("song with null album!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with negative track number
        try {
            songDAOImpl.update(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with zero bitrate
        try {
            songDAOImpl.update(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album()));
            fail("song with zero bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with negative bitrate
        try {
            songDAOImpl.update(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with bitrate above 2500
        try {
            songDAOImpl.update(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album()));
            fail("song with 2500+ bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test song with null id
        try {
            em.getTransaction().begin();
            songDAOImpl.update(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album()));
            em.getTransaction().commit();
            fail("null id update!");

        } catch (IllegalArgumentException ex) {
            //ok
        }
        em.clear();

        // test with id doesnt assigned by db
        EntityManager em2 = emf.createEntityManager();
        try {

            em2.getTransaction().begin();
            Song song = new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album());
            song.setId(new Long(1000));
            songDAOImpl.update(song);
            em2.getTransaction().commit();
            fail("no assigned id by db update!");

        } catch (IllegalArgumentException ex) {
            //ok
        }
        em2.close();

        //test update song
        EntityManager em3 = emf.createEntityManager();

        Song blessed = new Song("Tim Hangs - Blessed", 320, 1, "Yeah", new Genre(), new Album());
        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", new Genre(), new Album());
        em3.getTransaction().begin();
        songDAOImpl.create(blessed);
        em3.getTransaction().commit();
        dalibomba.setId(blessed.getId());

        em3.getTransaction().begin();
        songDAOImpl.update(dalibomba);                //OK
        em3.getTransaction().commit();
        assertDeepEquals(dalibomba, songDAOImpl.getById(Song.class, blessed.getId()));
        em3.close();


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

    public void testDeleteSong() {

        EntityManager em = emf.createEntityManager();
        SongDAOImpl songDAOImpl = new SongDAOImpl();
        //Song s = new Song("Salalaj", 320, 1, "", new Genre(), new Album());
        //s.setId(new Long(1l));

        //test null song
        try {
            songDAOImpl.delete(null);
            fail("null song delete!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //testCreateSongWithNullAttributes
        try {
            songDAOImpl.delete(new Song(null, 320, 1, null, null, null));
            fail("song with null attributes!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null title
        try {
            songDAOImpl.delete(new Song(null, 320, 1, "nehehe", new Genre(), new Album()));
            fail("song with null title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with empty title
        try {
            songDAOImpl.delete(new Song("", 320, 1, "nehehe", new Genre(), new Album()));
            fail("song with empty title!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null genre 
        try {
            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", null, new Album()));
            fail("song with null genre!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with null album
        try {
            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), null));
            fail("song with null album!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with negative track number
        try {
            songDAOImpl.delete(new Song("Salalaj", 320, -10, "nehehe", new Genre(), new Album()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with zero bitrate
        try {
            songDAOImpl.delete(new Song("Salalaj", 0, 1, "nehehe", new Genre(), new Album()));
            fail("song with zero bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with negative bitrate
        try {
            songDAOImpl.delete(new Song("Salalaj", -1000, 1, "nehehe", new Genre(), new Album()));
            fail("song with negative bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test with bitrate above 2500
        try {
            songDAOImpl.delete(new Song("Salalaj", 10000, 1, "nehehe", new Genre(), new Album()));
            fail("song with 2500+ bitrate!");
        } catch (IllegalArgumentException ex) {
            //OK
        }

        //test song with null id
        try {
            em.getTransaction().begin();
            songDAOImpl.delete(new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album()));
            em.getTransaction().commit();
            fail("null id delete!");

        } catch (IllegalArgumentException ex) {
            //ok
        }
        em.clear();

        // test with id doesnt assigned by db
        EntityManager em2 = emf.createEntityManager();
        try {

            em2.getTransaction().begin();
            Song song = new Song("Salalaj", 320, 1, "nehehe", new Genre(), new Album());
            song.setId(new Long(1000));
            songDAOImpl.delete(song);
            em2.getTransaction().commit();
            fail("no assigned id by db delete!");

        } catch (IllegalArgumentException ex) {
            //ok
        }
        em2.close();

        //test delete song
        EntityManager em3 = emf.createEntityManager();

        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", new Genre(), new Album());
        em3.getTransaction().begin();
        songDAOImpl.create(dalibomba);
        em3.getTransaction().commit();
        em3.clear();

        em3.getTransaction().begin();
        songDAOImpl.delete(dalibomba);
        em3.getTransaction().commit();
        em3.clear();
    }

    public void testGetSongById() {

        //test with null both
        EntityManager em = emf.createEntityManager();
        SongDAOImpl songDAOImpl = new SongDAOImpl();
        try {
            em.getTransaction().begin();
            songDAOImpl.getById(null, null);
            em.getTransaction().commit();
            fail("both null get!");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        em.close();

        //test with null id
        EntityManager em2 = emf.createEntityManager();

        try {
            em2.getTransaction().begin();
            songDAOImpl.getById(Song.class, null);
            em2.getTransaction().commit();
            fail("id null get!");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        em2.close();

        //test id not assigned by db
        EntityManager em3 = emf.createEntityManager();
        em3.getTransaction().begin();
        Song res = songDAOImpl.getById(Song.class, new Long(6543));
        em3.getTransaction().commit();
        assertNull(res);
        em3.close();
        
        //test get by id
        EntityManager em4 = emf.createEntityManager();
        Song dalibomba = new Song("Sandru - Dalibomba", 320, 1, "Yeah!!!", new Genre(), new Album());
        em4.getTransaction().begin();
        songDAOImpl.create(dalibomba);
        em4.getTransaction().commit();
        assertNotNull(dalibomba.getId());
        Long songId = dalibomba.getId();

        Song res1 = songDAOImpl.getById(Song.class, songId);
        assertDeepEquals(dalibomba, res1);
        em4.clear();




    }
}
