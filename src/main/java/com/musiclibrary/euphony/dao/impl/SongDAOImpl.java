package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.SongDAO;
import com.musiclibrary.euphony.entities.Song;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * 
 * @author Sebastian
 */
public class SongDAOImpl implements SongDAO{
    
    @PersistenceContext
    private EntityManager em;

    public void create(Song entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Song entity cannot be null.");
        } 
        
        em.persist(entity);
    }

    public void update(Song entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Song entity cannot be null.");
        }
            
        em.merge(entity);
    }

    public void delete(Song entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Song entity cannot be null.");
        } 
        
        if (em.find(Song.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This Song entity does not exist in database.");
        }
        
        Song songTemp = em.merge(entity);
        
        em.remove(songTemp);
    }

    public Song getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }
        
        Song songTemp = em.find(Song.class, id);
        
        return songTemp;
    }

}
