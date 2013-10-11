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
import static junit.framework.Assert.fail;
import junit.framework.TestCase;

/**
 *
 * @author PC
 */
public class SongDAOImplTest extends TestCase{
    
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
        
        //testNullSong
        try{
            songDAOImpl.create(null);
            fail("null song!");
        } 
        catch(IllegalArgumentException ex){
            //OK
        }
        
        //testCreateSongWithNullAttributes
        try{
            songDAOImpl.create(new Song(null, 0, 0, null, null, null));
            fail("song with null attributes!");
        } 
        catch(IllegalArgumentException ex){
            //OK
        }
        
        //test with null title
        try{
            songDAOImpl.create(new Song(null, 0, 0, "nehehe", new Genre(), new Album()));
            fail("song with null title!");
        } 
        catch(IllegalArgumentException ex){
            //OK
        }
        
        //test with empty title
        try{
            songDAOImpl.create(new Song("", 0, 0, "nehehe", new Genre(), new Album()));
            fail("song with empty title!");
        } 
        catch(IllegalArgumentException ex){
            //OK
        }
        
        //test with null genre 
        try{
            songDAOImpl.create(new Song("Salalaj", 0, 0, "nehehe", null, new Album()));
            fail("song with null genre!");
        } 
        catch(IllegalArgumentException ex){
            //OK
        }
        
        //test with null album
        try{
            songDAOImpl.create(new Song("Salalaj", 0, 0, "nehehe", new Genre(), null));
            fail("song with null album!");
        } 
        catch(IllegalArgumentException ex){
            //OK
        }
        
        //test with null comment
        try{
            em.getTransaction().begin();
            songDAOImpl.create(new Song("Salalaj", 0, 0, null, new Genre(), new Album()));
            em.getTransaction().commit();
        } 
        catch(IllegalArgumentException ex){
            fail("song with null comment is OK!");
        }
        em.clear();
        
        //test with empty comment
        try{
            em.getTransaction().begin();
            songDAOImpl.create(new Song("Salalaj", 0, 0, "", new Genre(), new Album()));
            em.getTransaction().commit();
        } 
        catch(IllegalArgumentException ex){
            fail("song with empty comment is OK!");
        }
        em.close();
    }
        
}
