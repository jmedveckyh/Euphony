package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.util.Util;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 *
 * @author Jakub Medvecký-Heretik #396373
 */
public class ArtistDAOImpl implements DAO<Artist> {

    @PersistenceContext
    private EntityManager em;

    public ArtistDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Artist entity) {

        Util.validateArtist(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This artist entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Artist entity) {

        Util.validateArtist(entity);

        if (entity.getId() == null) {
            throw new IllegalArgumentException("This artist entity cannot have null id.");
        }
        if (em.find(Artist.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This artist entity does not exist in database.");
        }

        em.merge(entity);
    }

    @Override
    public void delete(Artist entity) {

        Util.validateArtist(entity);

        if (entity.getId() == null) {
            throw new IllegalArgumentException("This artist entity cannot have null id.");
        }
        if (em.find(Artist.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This artist entity does not exist in database.");
        }

        Artist objectTemp = em.merge(entity);

        em.remove(objectTemp);
    }

    @Override
    public Artist getById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        Artist artist = (Artist) em.find(Artist.class, id);

        return artist;
    }

    public List<Artist> getAll() {
        Query q = em.createQuery("from Artist");
        List<Artist> artists = q.getResultList();
        return Collections.unmodifiableList(artists);
    }

    public Artist getByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Title is NULL");
        }
        Query q = em.createQuery("from Artist where name=:name");
        q.setParameter("name", name);
        Artist artist = (Artist) q.getSingleResult();
        return artist;
    }
}
