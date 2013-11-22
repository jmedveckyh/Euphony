package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.PlaylistDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
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
 * DAO implementation of Playlist.
 *
 * @author Tomas Smetanka
 */
@Repository
public class PlaylistDAOImpl implements PlaylistDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
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
        try {
            Util.validatePlaylist(entity);

            if (entity.getId() != null) {
                throw new DataAccessException("This playlist entity is already in databse.") {};
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
    public void update(Playlist entity) {
        try {
            Util.validatePlaylist(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This playlist entity cannot have null id.") {};
            }
            if (em.find(Playlist.class, entity.getId()) == null) {
                throw new DataAccessException("This playlist entity does not exist in database.") {};
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
    public void delete(Playlist entity) {
        try {
            Util.validatePlaylist(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This playlist entity cannot have null id.") {};
            }
            if (em.find(Playlist.class, entity.getId()) == null) {
                throw new DataAccessException("This playlist entity does not exist in database.") {};
            }

            Playlist objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Playlist getById(Long id) {
        if (id == null) {
            throw new DataAccessException("Id cannot be null.") {
            };
        }

        Playlist objectTemp = (Playlist) em.find(Playlist.class, id);
        return objectTemp;
    }

    @Override
    public Playlist getByName(String name) {
        if (name == null) {
            throw new DataAccessException("Name cannot be null.") {
            };
        }

        Query q = em.createQuery("FROM Playlist WHERE name=:name");
        q.setParameter("name", name);
        Playlist playlist = (Playlist) q.getSingleResult();

        return playlist;
    }

    @Override
    public List<Playlist> getBySong(Song song) {
        Util.validateSong(song);

        if (song.getId() == null) {
            throw new DataAccessException("This song entity cannot have null id.") {
            };
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
