package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.GenreDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
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
public class GenreDAOImpl implements GenreDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public GenreDAOImpl() {
    }

    public GenreDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Genre entity) {
        try {
            Util.validateGenre(entity);

            if (entity.getId() != null) {
                throw new DataAccessException("This genre entity is already in databse.") {};
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
    public void update(Genre entity) {
        try {
            Util.validateGenre(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This genre entity cannot have null id.") {};
            }
            if (em.find(Genre.class, entity.getId()) == null) {
                throw new DataAccessException("This genre entity does not exist in database.") {};
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
    public void delete(Genre entity) {
        try {
            Util.validateGenre(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This genre entity cannot have null id.") {};
            }
            if (em.find(Genre.class, entity.getId()) == null) {
                throw new DataAccessException("This genre entity does not exist in database.") {};
            }

            Genre objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Genre getById(Long id) {
        if (id == null) {
            throw new DataAccessException("Id cannot be null.") {
            };
        }

        Genre genre = (Genre) em.find(Genre.class, id);

        return genre;
    }

    @Override
    public List<Genre> getAll() {
        Query q = em.createQuery("from Genre");
        List<Genre> genres = q.getResultList();
        return Collections.unmodifiableList(genres);
    }

    @Override
    public Genre getByName(String name) {
        if (name == null) {
            throw new DataAccessException("Name is NULL") {
            };
        }
        Query q = em.createQuery("from Genre where name=:name");
        q.setParameter("name", name);
        try {
            Genre genre = (Genre) q.getSingleResult();
            return genre;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
