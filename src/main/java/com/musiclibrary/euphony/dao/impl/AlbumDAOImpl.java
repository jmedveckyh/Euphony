package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
import com.musiclibrary.euphony.entities.Album;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * 
 * @author Branislav Novotny
 */
public class AlbumDAOImpl implements DAO<Album>{

    @PersistenceContext
    private EntityManager em;

    public AlbumDAOImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(Album entity) {
        
        checkAlbum(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This album entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Album entity) {
        
        checkAlbum(entity);
        
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
        
        checkAlbum(entity);

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
    public Album getById(Class cls, Long id) {
        
        if (cls == null) {
            throw new IllegalArgumentException("Class cannot be null.");
        }
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        Album objectTemp = (Album) em.find(cls, id);

        return objectTemp;
    }

    public static void checkAlbum(Album album) {
        if (album == null){
            throw new IllegalArgumentException("Album cannot be null.");
        }
        if (album.getReleaseDate() == null) {
            throw new IllegalArgumentException("Album's release date is null.");
        }
        if ("".equals(album.getTitle())) {
            throw new IllegalArgumentException("Album's title is empty.");
        }
        if (album.getTitle() == null) {
            throw new IllegalArgumentException("Album's title is null.");
        }
    }
}
