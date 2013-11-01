package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
import com.musiclibrary.euphony.dao.PlaylistDAO;
import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
import com.musiclibrary.euphony.util.Util;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * DAO implementation of Playlist.
 * 
 * @author Tomas Smetanka
 */
public class PlaylistDAOImpl implements DAO<Playlist> , PlaylistDAO{

    @PersistenceContext
    private EntityManager em;

    public PlaylistDAOImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(Playlist entity) {
        
        Util.validatePlaylist(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This playlist entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Playlist entity) {
        
        Util.validatePlaylist(entity);
        
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
        
        Util.validatePlaylist(entity);

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
    public Playlist getById(Long id) {
        
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        Playlist objectTemp = (Playlist) em.find(Playlist.class, id);

        return objectTemp;
    }

    @Override
    public Playlist getByName(String name) {
        
        if(name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        
        Query q = em.createQuery("FROM Playlist WHERE name=:name");
        q.setParameter("name", name);
        Playlist playlist = (Playlist) q.getSingleResult();

        return playlist;
        
    }

    @Override
    public List<Playlist> getBySong(Song song) {
        
        Util.validateSong(song);
        
        if(song.getId() == null) {
            throw new IllegalArgumentException("This song entity cannot have null id.");
        }
        
        Query q = em.createQuery("FROM Playlist WHERE song=:song");
        q.setParameter("song", song);
        List<Playlist> playlists = q.getResultList();
        
        return Collections.unmodifiableList(playlists);
        
    }

    @Override
    public List<Playlist> getAll() {
                
        Query q = em.createQuery("FROM Playlist");
        List<Playlist> playlists = q.getResultList();
        
        return Collections.unmodifiableList(playlists);
        
    }
}

