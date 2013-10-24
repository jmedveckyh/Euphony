package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
import com.musiclibrary.euphony.entities.Playlist;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * 
 * @author Tomas Smetanka #396209
 */
public class PlaylistDAOImpl implements DAO<Playlist> {

    @PersistenceContext
    private EntityManager em;

    public PlaylistDAOImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(Playlist entity) {
        
        checkPlaylist(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This playlist entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Playlist entity) {
        
        checkPlaylist(entity);
        
        if (entity.getId() == null) {
            throw new IllegalArgumentException("This playlist entity cannot have null id.");
        }
        if (em.find(Playlist.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This playlist entity does not exist in database.");
        }

        em.merge(entity);
    }

    @Override
    public void delete(Playlist entity) {
        
        checkPlaylist(entity);

        if (entity.getId() == null) {
            throw new IllegalArgumentException("This playlist entity cannot have null id.");
        }
        if (em.find(Playlist.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This playlist entity does not exist in database.");
        }
 
        Playlist objectTemp = em.merge(entity);

        em.remove(objectTemp);
    }

    @Override
    public Playlist getById(Class cls, Long id) {
        
        if (cls == null) {
            throw new IllegalArgumentException("Class cannot be null.");
        }
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        Playlist objectTemp = (Playlist) em.find(cls, id);

        return objectTemp;
    }

    public static void checkPlaylist(Playlist playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist cannot be null.");
        }
        if (playlist.getName() == null) {
            throw new IllegalArgumentException("Playlist's name is null.");
        }
        if ("".equals(playlist.getName())) {
            throw new IllegalArgumentException("Playlist's name is empty.");
        }
    }
}

