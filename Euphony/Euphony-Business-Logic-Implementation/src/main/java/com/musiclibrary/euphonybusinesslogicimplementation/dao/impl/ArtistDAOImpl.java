package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.ArtistDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import com.musiclibrary.euphonybusinesslogicimplementation.util.Util;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author Jakub Medvecký-Heretik #396373
 */
@Repository
public class ArtistDAOImpl implements ArtistDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public ArtistDAOImpl(EntityManager em) {
        this.em = em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public ArtistDAOImpl() {
    }

    @Override
    public void create(Artist entity) {
        try {
            Util.validateArtist(entity);

            if (entity.getId() != null) {
                throw new IllegalArgumentException("This artist entity is already in databse.");
            }

            em.persist(entity);
            em.flush();
            em.detach(entity);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public void update(Artist entity) {
        try {
            Util.validateArtist(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This artist entity cannot have null id.");
            }
            if (em.find(Artist.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This artist entity does not exist in database.");
            }

            em.merge(entity);
            em.flush();
            em.detach(entity);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public void delete(Artist entity) {
        try {
            Util.validateArtist(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This artist entity cannot have null id.");
            }
            if (em.find(Artist.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This artist entity does not exist in database.");
            }

            Artist objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Artist getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Artist artist = (Artist) em.find(Artist.class, id);
            return artist;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Artist> getAll() {
        try {
            Query q = em.createQuery("from Artist");
            List<Artist> artists = q.getResultList();
            return Collections.unmodifiableList(artists);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Artist getByName(String name) {
        try {
            if (name == null) {
                throw new IllegalArgumentException("Title is NULL") {
                };
            }
            Query q = em.createQuery("from Artist where name=:name");
            q.setParameter("name", name);
            try {
                Artist artist = (Artist) q.getSingleResult();
                return artist;
            } catch (NoResultException ex) {
                return null;
            }
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Song> getSongsByArtist(Artist artist) {
        try {
            if (artist == null) {
                throw new IllegalArgumentException("Album is null");
            }
            Query q = em.createQuery("select x from Song x WHERE x.artist = :artist");
            q.setParameter("artist", artist);
            try {
                List<Song> songs = q.getResultList();
                return songs;
            } catch (NoResultException ex) {
                return null;
            }
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
}
