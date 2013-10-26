package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
import com.musiclibrary.euphony.entities.Song;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        
        checkSong(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This song entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Song entity) {
        
        checkSong(entity);
        
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
        
        checkSong(entity);

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

    public static void checkSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null.");
        }
        if (song.getTitle() == null) {
            throw new IllegalArgumentException("Song's title cannot be null.");
        }
        if ("".equals(song.getTitle())) {
            throw new IllegalArgumentException("Song's title cannot be empty.");
        }
        if (song.getGenre() == null) {
            throw new IllegalArgumentException("Song's genre cannot be null.");
        }
        if (song.getAlbum() == null) {
            throw new IllegalArgumentException("Song's album cannot be null.");
        }
        if (song.getBitrate() < 1 || song.getBitrate() > 2500) {
            throw new IllegalArgumentException("Song's bitrate must be between 1 and 2500.");
        }
        if (song.getTrackNumber() <= 0) {
            throw new IllegalArgumentException("Song's track number must be bigger than 0.");
        }
    }
}

