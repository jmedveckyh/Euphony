/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphony.dao.impl.SongDAOImpl;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Song;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.joda.time.DateTime;

/**
 *
 * @author Sebastian
 */
public class AlbumDAOImplTest extends TestCase{
    
   EntityManagerFactory emf;

    public AlbumDAOImplTest(String name) {
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
    
    public void testCreateAlbum() {
        
        EntityManager em = emf.createEntityManager();
        AlbumDAOImpl albumDAOImpl = new AlbumDAOImpl();
    
        Album album = null;
        em.getTransaction().begin();
        try{
        albumDAOImpl.create(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        em.getTransaction().begin();
        try{
        albumDAOImpl.create(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        album.setTitle("");
        album.setReleaseDate(new DateTime(2011,11,11,0,0));
        em.getTransaction().begin();
        try{
        albumDAOImpl.create(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        album.setTitle("aaaa");
        album.setReleaseDate(new DateTime(2011,11,11,0,0));
        album.setId(new Long(1));
        em.getTransaction().begin();
        try{
        albumDAOImpl.create(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
    }
    
    public void testGetAlbum(){
        EntityManager em = emf.createEntityManager();
        AlbumDAOImpl albumDAOImpl = new AlbumDAOImpl();
    
        Album album = new Album();
        album.setTitle("aaaa");
        album.setReleaseDate(new DateTime(2011,11,11,0,0));
        em.getTransaction().begin();
        albumDAOImpl.create(album);
        em.getTransaction().commit();
        assertNotNull(album.getId());
        Album album2 = albumDAOImpl.getById(Album.class, new Long(album.getId()));
        assertEquals(album, album2);
        
        try{
            albumDAOImpl.getById(Album.class, null);
            fail();
        } catch (Exception e){
            //OK
        }
        
        try{
            albumDAOImpl.getById(Album.class, new Long(999));
            fail();
        } catch (Exception e){
            //OK
        }
    }
    
    public void testUpdateAlbum(){
        EntityManager em = emf.createEntityManager();
        AlbumDAOImpl albumDAOImpl = new AlbumDAOImpl();
        
        Album album = null;
        em.getTransaction().begin();
        try{
        albumDAOImpl.update(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        em.getTransaction().begin();
        try{
        albumDAOImpl.update(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        album.setTitle("");
        album.setReleaseDate(new DateTime(2011,11,11,0,0));
        em.getTransaction().begin();
        try{
        albumDAOImpl.update(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        album.setTitle("aaa");
        em.getTransaction().begin();
        try{
        albumDAOImpl.update(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011,11,11,0,0));
        album.setId(null);
        em.getTransaction().begin();
        try{
        albumDAOImpl.update(album);
        fail("update with noID");
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011,11,11,0,0));
        em.getTransaction().begin();
        albumDAOImpl.create(album);
        em.getTransaction().commit();
        album.setTitle("bbb");
        em.getTransaction().begin();
        albumDAOImpl.update(album);
        em.getTransaction().commit();
        Album album2 = albumDAOImpl.getById(Album.class, album.getId());
        Album album3 = new Album();
        album3.setTitle("bbb");
        album3.setReleaseDate(new DateTime(2011,11,11,0,0));
        assertEquals(album2, album3); 
    }
    
    public void testDeleteAlbum(){
        EntityManager em = emf.createEntityManager();
        AlbumDAOImpl albumDAOImpl = new AlbumDAOImpl();
        
        Album album = null;
        em.getTransaction().begin();
        try{
        albumDAOImpl.delete(album);
        fail("");
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011,11,11,0,0));
        album.setId(null);
        em.getTransaction().begin();
        try{
        albumDAOImpl.delete(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011,11,11,0,0));
        album.setId(new Long(999));
        em.getTransaction().begin();
        try{
        albumDAOImpl.delete(album);
        fail();
        } catch (Exception e){
            //OK
        }
        em.getTransaction().commit();
        em.clear();
        
        album = new Album();
        album.setTitle("aaa");
        album.setReleaseDate(new DateTime(2011,11,11,0,0));
        em.getTransaction().begin();
        albumDAOImpl.create(album);
        em.getTransaction().commit();
        em.getTransaction().begin();
        albumDAOImpl.delete(album);
        em.getTransaction().commit();
        em.clear();
    }
        
}
