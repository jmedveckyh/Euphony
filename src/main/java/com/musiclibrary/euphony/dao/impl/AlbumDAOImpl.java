package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.AlbumDAO;
import com.musiclibrary.euphony.entities.Album;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * 
 * @author Branislav Novotny
 */
public class AlbumDAOImpl implements AlbumDAO{
    
    @PersistenceContext
    private EntityManager em;
    
    // vyriesit verifikaciu
    
    public void createAlbum(Album album) {
        if(album == null){
            throw new NullPointerException("album is null.");
        }
        
        //try catch ?
        em.persist(album);
    }

    public void updateAlbum(Album album) {
        if(album == null){
            throw new NullPointerException("album is null.");
        }
        em.merge(album);
    }

    public void deleteAlbum(Album album) {
        
        if(album == null){
            throw new NullPointerException("album is null.");
        }
        if(em.find(Album.class, album.getId()) == null) {
            throw new IllegalArgumentException("Album does not exist in DB.");
        }
        
        Album albumTemp = em.merge(album); //?
        
        em.remove(albumTemp);
    }

    public Album getAlbumById(Long id) {
        
        if(id == null){
            throw new NullPointerException("Id is null.");
        }
        
        return em.find(Album.class, id);
    }

}
