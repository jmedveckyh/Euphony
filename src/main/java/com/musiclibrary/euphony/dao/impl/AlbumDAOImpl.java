package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.AlbumDAO;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import org.joda.time.DateTime;

/**
 *
 *
 * @author Branislav Novotny
 */
public class AlbumDAOImpl implements AlbumDAO {

    @PersistenceContext
    private EntityManager em;

    public AlbumDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Album entity) {

        Util.validateAlbum(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This album entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Album entity) {

        Util.validateAlbum(entity);

        if (entity.getId() == null) {
            throw new IllegalArgumentException("This album entity cannot have null id.");
        }
        if (em.find(Album.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This album entity does not exist in database.");
        }

        em.merge(entity);
    }

    @Override
    public void delete(Album entity) {

        Util.validateAlbum(entity);

        if (entity.getId() == null) {
            throw new IllegalArgumentException("This album entity cannot have null id.");
        }
        if (em.find(Album.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This album entity does not exist in database.");
        }

        Album objectTemp = em.merge(entity);

        em.remove(objectTemp);
    }

    @Override
    public Album getById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
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
    }

    @Override
    public List<Album> getByGenre(Genre genre) {
        if (genre == null) {
         throw new IllegalArgumentException("Genre is null");
         }
         Query q = em.createQuery("SELECT x FROM Album x WHERE (:genre) IN elements(x.genres)");
         q.setParameter("genre", genre);
         try {
         List<Album> albums = q.getResultList();
         return albums;
         } catch (NoResultException ex) {
         return null;
         }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<Album> getByArtist(Artist artist) {
        if (artist == null) {
         throw new IllegalArgumentException("Artist is null");
         }
         Query q = em.createQuery("SELECT x FROM Album x WHERE (:artist) IN elements(x.artists)");
         q.setParameter("artist", artist);
         try {
         List<Album> albums = q.getResultList();
         return albums;
         } catch (NoResultException ex) {
         return null;
         }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public List<Album> getByReleaseYear(Integer year) { //ceknut si je to spravne
        if (year == null) {
            throw new IllegalArgumentException("Year is null");
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
