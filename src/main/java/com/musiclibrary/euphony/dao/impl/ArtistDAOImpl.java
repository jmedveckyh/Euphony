package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
import com.musiclibrary.euphony.entities.Artist;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        
        checkArtist(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This artist entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Artist entity) {
        
        checkArtist(entity);
        
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
        
        checkArtist(entity);

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

        Artist objectTemp = (Artist) em.find(Artist.class, id);

        return objectTemp;
    }

    public static void checkArtist(Artist artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null.");
        }
        if (artist.getName() == null) {
            throw new IllegalArgumentException("Artist's name is null.");
        }
        if ("".equals(artist.getName())) {
            throw new IllegalArgumentException("Artist's name is empty.");
        }
    }
}
