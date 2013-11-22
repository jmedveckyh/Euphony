package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.SongDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import com.musiclibrary.euphonybusinesslogicimplementation.util.Util;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author Sebastian
 */
@Repository
public class SongDAOImpl implements SongDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public SongDAOImpl() {
    }

    public SongDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Song entity) {
        try {
            Util.validateSong(entity);

            if (entity.getId() != null) {
                throw new DataAccessException("This song entity is already in databse.") {};
            }

            em.persist(entity);
            em.flush();
            em.detach(entity);
        } catch (PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public void update(Song entity) {
        try {
            Util.validateSong(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This song entity cannot have null id.") {};
            }
            if (em.find(Song.class, entity.getId()) == null) {
                throw new DataAccessException("This song entity does not exist in database.") {};
            }

            em.merge(entity);
            em.flush();
            em.detach(entity);
        } catch (PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public void delete(Song entity) {
        try {
            Util.validateSong(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This song entity cannot have null id.") {};
            }
            if (em.find(Song.class, entity.getId()) == null) {
                throw new DataAccessException("This song entity does not exist in database.") {};
            }

            Song objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Song getById(Long id) {
            if (id == null) {
                throw new DataAccessException("Id cannot be null.") {};
            }

            Song objectTemp = (Song) em.find(Song.class, id);
            return objectTemp;
    }

    @Override
    public List<Song> getAll() {
            Query q = em.createQuery("from Song");
            List<Song> songs = q.getResultList();
            return Collections.unmodifiableList(songs);
    }

    @Override
    public List<Song> getByTitle(String title) {
            if (title == null) {
                throw new DataAccessException("Title is NULL") {};
            }
            Query q = em.createQuery("from Song where title=:title");
            q.setParameter("title", title);
            List<Song> songs = q.getResultList();
            return Collections.unmodifiableList(songs);
    }

    @Override
    public List<Song> getByGenre(Genre genre) {
            Util.validateGenre(genre);
            Query q = em.createQuery("from Song where genre=:genre");
            q.setParameter("genre", genre);
            List<Song> songs = q.getResultList();
            return Collections.unmodifiableList(songs);
    }

    @Override
    public List<Song> getByArtist(Artist artist) {
            Util.validateArtist(artist);
            Query q = em.createQuery("from Song where artist=:artist");
            q.setParameter("artist", artist);
            List<Song> songs = q.getResultList();
            return Collections.unmodifiableList(songs);
    }

    @Override
    public List<Song> getByAlbum(Album album) {
            Util.validateAlbum(album);
            Query q = em.createQuery("from Song where album=:album");
            q.setParameter("album", album);
            List<Song> songs = q.getResultList();
            return Collections.unmodifiableList(songs);
    }
}
