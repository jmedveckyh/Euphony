package com.musiclibrary.euphonybusinesslogicimplementation.dao.impl;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.AlbumDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
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
import org.joda.time.DateTime;
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
                throw new DataAccessException("This album entity is already in databse.") {};
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
    public void update(Album entity) {
        try {
            Util.validateAlbum(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This album entity cannot have null id.") {};
            }
            if (em.find(Album.class, entity.getId()) == null) {
                throw new DataAccessException("This album entity does not exist in database.") {};
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
    public void delete(Album entity) {
        try {
            Util.validateAlbum(entity);

            if (entity.getId() == null) {
                throw new DataAccessException("This album entity cannot have null id.") {};
            }
            if (em.find(Album.class, entity.getId()) == null) {
                throw new DataAccessException("This album entity does not exist in database.") {};
            }

            Album objectTemp = em.merge(entity);

            em.remove(objectTemp);
        } catch (PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public Album getById(Long id) {
        if (id == null) {
            throw new DataAccessException("Id cannot be null.") {
            };
        }

        Album objectTemp = (Album) em.find(Album.class, id);
        return objectTemp;
    }

    @Override
    public List<Album> getAll() {
        Query q = em.createQuery("from Album");
        List<Album> albums = q.getResultList();
        return Collections.unmodifiableList(albums);
    }

    @Override
    public Album getByTitle(String title) {
        if (title == null) {
            throw new DataAccessException("Title is null") {
            };
        }
        Query q = em.createQuery("from Album where title=:title");
        q.setParameter("title", title);
        try {
            Album album = (Album) q.getSingleResult();
            return album;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Album> getByGenre(Genre genre) {
        if (genre == null) {
            throw new DataAccessException("Genre is null") {
            };
        }
        Query q = em.createQuery("SELECT x FROM Album x WHERE (:genre) IN elements(x.genres)");
        q.setParameter("genre", genre);
        try {
            List<Album> albums = q.getResultList();
            return albums;
        } catch (NoResultException ex) {
            return null;
        }

    }

    @Override
    public List<Album> getByArtist(Artist artist) {
        if (artist == null) {
            throw new DataAccessException("Artist is null") {
            };
        }
        Query q = em.createQuery("SELECT x FROM Album x WHERE (:artist) IN elements(x.artists)");
        q.setParameter("artist", artist);
        try {
            List<Album> albums = q.getResultList();
            return albums;
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Album> getByReleaseYear(Integer year) { //ceknut si je to spravnetry
        if (year == null) {
            throw new DataAccessException("Year is null") {
            };
        }
        Query q = em.createQuery("select x from Album x WHERE x.releaseDate BETWEEN :from AND :to");
        q.setParameter("from", new DateTime(year, 1, 1, 0, 0));
        q.setParameter("to", new DateTime(year, 12, 31, 23, 59));
        try {
            List<Album> albums = q.getResultList();
            return albums;
        } catch (NoResultException ex) {
            return null;
        }
    }
}
