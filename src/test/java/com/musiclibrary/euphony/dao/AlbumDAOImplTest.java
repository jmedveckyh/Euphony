/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphony.dao.impl.PlaylistDAOImpl;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Playlist;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;

/**
 *
 * @author Sebastian
 */
public class AlbumDAOImplTest extends TestCase {
    
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
    
        Album album = new Album("prvy");
        em.getTransaction().begin();
        albumDAOImpl.create(album);
        em.getTransaction().commit();
        em.clear();
        
        Long id = album.getId();
        assertNotNull(id);
        Album album2 = albumDAOImpl.getById(Album.class, id);
        assertEquals(album, album2);
        
    }
    
    
    
}
