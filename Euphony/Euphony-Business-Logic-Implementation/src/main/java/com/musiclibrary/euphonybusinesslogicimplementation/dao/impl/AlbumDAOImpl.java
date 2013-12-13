package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.AlbumDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
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
 * @author Branislav Novotny
 */
@Repository
public class AlbumDAOImpl implements AlbumDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public AlbumDAOImpl() {
    }

    public AlbumDAOImpl(EntityManager em) {
        this.em = em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Album entity) {
        try {
            Util.validateAlbum(entity);

            if (entity.getId() != null) {
                throw new IllegalArgumentException("This album entity is already in databse.");
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
    public void update(Album entity) {
        try {
            Util.validateAlbum(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This album entity cannot have null id.");
            }
            if (em.find(Album.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This album entity does not exist in database.");
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
    public void delete(Album entity) {
        try {
            Util.validateAlbum(entity);

            if (entity.getId() == null) {
                throw new IllegalArgumentException("This album entity cannot have null id.");
            }
            if (em.find(Album.class, entity.getId()) == null) {
                throw new IllegalArgumentException("This album entity does not exist in database.");
            }

            Album objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Album getById(Long id) {
        try {
            if (id == null) {
                throw new IllegalArgumentException("Id cannot be null.");
            }

            Album objectTemp = (Album) em.find(Album.class, id);
            return objectTemp;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<Album> getAll() {
        try {
            Query q = em.createQuery("from Album");
            List<Album> albums = q.getResultList();
            return Collections.unmodifiableList(albums);
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Album getByTitle(String title) {
        try {
            if (title == null) {
                throw new IllegalArgumentException("Title is null");
            }
            Query q = em.createQuery("from Album where title=:title");
            q.setParameter("title", title);
            try {
                Album album = (Album) q.getSingleResult();
                return album;
            } catch (NoResultException ex) {
                return null;
            }
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
}
