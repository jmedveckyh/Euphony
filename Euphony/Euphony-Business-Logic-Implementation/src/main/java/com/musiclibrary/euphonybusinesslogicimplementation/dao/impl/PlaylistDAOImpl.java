package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.PlaylistDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import com.musiclibrary.euphonybusinesslogicimplementation.util.Util;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        try {
            Util.validatePlaylist(entity);

            if (entity.getId() != null) {
                throw new IllegalArgumentException("This playlist entity is already in databse.");
            }

            em.persist(entity);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public void update(Playlist entity) {
        try {
            Util.validatePlaylist(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This playlist entity cannot have null id.");
            }
            if (em.find(Playlist.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This playlist entity does not exist in database.");
            }

            em.merge(entity);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public void delete(Playlist entity) {
        try {
            Util.validatePlaylist(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This playlist entity cannot have null id.");
            }
            if (em.find(Playlist.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This playlist entity does not exist in database.");
            }

            Playlist objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Playlist getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Playlist objectTemp = (Playlist) em.find(Playlist.class, id);

            return objectTemp;
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Playlist getByName(String name) {
        try {
            if (name == null) {
                throw new IllegalArgumentException("Name cannot be null.");
            }

            Query q = em.createQuery("FROM Playlist WHERE name=:name");
            q.setParameter("name", name);
            Playlist playlist = (Playlist) q.getSingleResult();

            return playlist;
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Playlist> getBySong(Song song) {
        try {
            Util.validateSong(song);

            if (song.getId() == null) {
                throw new IllegalArgumentException("This song entity cannot have null id.");
            }

            Query q = em.createQuery("FROM Playlist WHERE song=:song");
            q.setParameter("song", song);
            List<Playlist> playlists = q.getResultList();

            return Collections.unmodifiableList(playlists);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Playlist> getAll() {
        try {
            Query q = em.createQuery("FROM Playlist");
            List<Playlist> playlists = q.getResultList();

            return Collections.unmodifiableList(playlists);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
}
