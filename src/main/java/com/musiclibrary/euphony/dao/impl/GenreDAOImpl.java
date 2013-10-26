package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
import com.musiclibrary.euphony.entities.Genre;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 *
 * @author Jakub Medvecký-Heretik #396373
 */
public class GenreDAOImpl implements DAO<Genre> {

    @PersistenceContext
    private EntityManager em;

    public GenreDAOImpl(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public void create(Genre entity) {
        
        checkGenre(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This genre entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Genre entity) {
        
        checkGenre(entity);
        
        if (entity.getId() == null) {
            throw new IllegalArgumentException("This genre entity cannot have null id.");
        }
        if (em.find(Genre.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This genre entity does not exist in database.");
        }

        em.merge(entity);
    }

    @Override
    public void delete(Genre entity) {
        
        checkGenre(entity);

        if (entity.getId() == null) {
            throw new IllegalArgumentException("This genre entity cannot have null id.");
        }
        if (em.find(Genre.class, entity.getId()) == null) {
            throw new IllegalArgumentException("This genre entity does not exist in database.");
        }
 
        Genre objectTemp = em.merge(entity);

        em.remove(objectTemp);
    }

    @Override
    public Genre getById(Long id) {
        
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        Genre objectTemp = (Genre) em.find(Genre.class, id);

        return objectTemp;
    }

    public static void checkGenre(Genre genre) {
        if (genre == null){
            throw new IllegalArgumentException("Genre cannot be null.");
        }
        if (genre.getName() == null) {
            throw new IllegalArgumentException("Genre's name is null.");
        }
        if ("".equals(genre.getName())) {
            throw new IllegalArgumentException("Genre's name is empty.");
        }
    }

}
