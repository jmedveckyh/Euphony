package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Song;
import com.musiclibrary.euphony.util.Util;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * 
 * @author Sebastian
 */
public class SongDAOImpl implements DAO<Song> {

    @PersistenceContext
    private EntityManager em;

    public SongDAOImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(Song entity) {
        
        Util.validateSong(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This song entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Song entity) {
        
        Util.validateSong(entity);
        
        if (entity.getId() == null) {
            throw new IllegalArgumentException("This song entity cannot have null id.");
        }
        if (em.find(Song.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This song entity does not exist in database.");
        }

        em.merge(entity);
    }

    @Override
    public void delete(Song entity) {
        
        Util.validateSong(entity);

        if (entity.getId() == null) {
            throw new IllegalArgumentException("This song entity cannot have null id.");
        }
        if (em.find(Song.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This song entity does not exist in database.");
        }
 
        Song objectTemp = em.merge(entity);

        em.remove(objectTemp);
    }

    @Override
    public Song getById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        Song objectTemp = (Song) em.find(Song.class, id);

        return objectTemp;
    }
    
    public List<Song> getAll(){
        Query q = em.createQuery("from Song");
        List<Song> songs = q.getResultList();
        return Collections.unmodifiableList(songs);
    }
    
    public List<Song> getByTitle(String title){
        if(title == null) {
            throw new IllegalArgumentException("Title is NULL");
        }
        Query q = em.createQuery("from Song where title=:title");
        q.setParameter("title", title);
        List<Song> songs = q.getResultList();
        return Collections.unmodifiableList(songs);
    }
    
    public List<Song> getByGenre(Genre genre){
        Util.validateGenre(genre);
        Query q = em.createQuery("from Song where genre=:genre");
        q.setParameter("genre", genre);
        List<Song> songs = q.getResultList();
        return Collections.unmodifiableList(songs);
    }
    
    public List<Song> getByArtist(Artist artist){
        Util.validateArtist(artist);
        Query q = em.createQuery("from Song where artist=:artist");
        q.setParameter("artist", artist);
        List<Song> songs = q.getResultList();
        return Collections.unmodifiableList(songs);
    }
    
    public List<Song> getByAlbum(Album album){
        Util.validateAlbum(album);
        Query q = em.createQuery("from Song where album=:album");
        q.setParameter("album", album);
        List<Song> songs = q.getResultList();
        return Collections.unmodifiableList(songs);
    }
}

