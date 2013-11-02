package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.GenreDAO;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.util.Util;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 *
 * @author Jakub Medvecký-Heretik #396373
 */

@Repository
public class GenreDAOImpl implements GenreDAO {

    @PersistenceContext
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

        Util.validateGenre(entity);

        if (entity.getId() != null) {
            throw new IllegalArgumentException("This genre entity is already in databse.");
        }

        em.persist(entity);
    }

    @Override
    public void update(Genre entity) {

        Util.validateGenre(entity);

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

        Util.validateGenre(entity);

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
            throw new IllegalArgumentException("Name is NULL");
        }
        Query q = em.createQuery("from Genre where name=:name");
        q.setParameter("name", name);
        try {
            Genre genre = (Genre) q.getSingleResult();
            return genre;
        }catch (NoResultException ex){
            return null;
        }
    }
}
