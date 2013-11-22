package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.ArtistDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
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
                throw new DataAccessException("This artist entity is already in databse.") {};
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
    public void update(Artist entity) {
        try {
            Util.validateArtist(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This artist entity cannot have null id.") {};
            }
            if (em.find(Artist.class, entity.getId()) == null) {
                throw new DataAccessException("This artist entity does not exist in database.") {};
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
    public void delete(Artist entity) {
        try {
            Util.validateArtist(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This artist entity cannot have null id.") {};
            }
            if (em.find(Artist.class, entity.getId()) == null) {
                throw new DataAccessException("This artist entity does not exist in database.") {};
            }

            Artist objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Artist getById(Long id) {
        if (id == null) {
            throw new DataAccessException("Id cannot be null.") {
            };
        }

        Artist artist = (Artist) em.find(Artist.class, id);
        return artist;
    }

    @Override
    public List<Artist> getAll() {
        Query q = em.createQuery("from Artist");
        List<Artist> artists = q.getResultList();
        return Collections.unmodifiableList(artists);
    }

    @Override
    public Artist getByName(String name) {
        if (name == null) {
            throw new DataAccessException("Title is NULL") {
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
    }
}
