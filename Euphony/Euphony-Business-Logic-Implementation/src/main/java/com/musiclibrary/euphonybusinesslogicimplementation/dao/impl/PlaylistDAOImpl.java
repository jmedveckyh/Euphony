package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.PlaylistDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import com.musiclibrary.euphonybusinesslogicimplementation.util.Util;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * DAO implementation of Playlist.
 * 
 * @author Tomas Smetanka
 */

@Repository
public class PlaylistDAOImpl implements PlaylistDAO{

    @PersistenceContext
    private EntityManager em;

    public PlaylistDAOImpl() {
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

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

