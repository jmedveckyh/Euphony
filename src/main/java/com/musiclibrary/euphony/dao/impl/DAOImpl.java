/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
import com.musiclibrary.euphony.utils.HibernateUtil;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Medo
 */
public abstract class DAOImpl<T> implements DAO<T> {

    protected EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager();
    }

    public void create(T entity) {
        EntityManager em = this.getEntityManager();
        
        checkEntity(entity);

        if (getIdOfEntity(entity) != null) {
            throw new IllegalArgumentException(this.getClass().getName() + " entity is already in databse.");
        }

        em.persist(entity);
    }

    public void update(T entity) {
        EntityManager em = this.getEntityManager();
        
        checkEntity(entity);
        
        if (getIdOfEntity(entity) == null) {
            throw new IllegalArgumentException(this.getClass().getName() + " entity does not exist in database.");
        }

        em.merge(entity);
    }

    public void delete(T entity) {
        EntityManager em = this.getEntityManager();
        
        checkEntity(entity);

        if (em.find(entity.getClass(), getIdOfEntity(entity)) == null) {
            throw new IllegalArgumentException("This " + this.getClass().getName() + " entity does not exist in database.");
        }
 
        T objectTemp = em.merge(entity);

        em.remove(objectTemp);

    }

    public T getById(Class cls, Long id) {
        EntityManager em = this.getEntityManager();
        if (cls == null) {
            throw new IllegalArgumentException("Class cannot be null.");
        }
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null.");
        }

        T objectTemp = (T) em.find(cls, id);

        return objectTemp;
    }

    public Long getIdOfEntity(T entity) {
        Long id = null;

        try {
            id = (Long) entity.getClass().getMethod("getId").invoke(entity, (Object) null);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;
    }

    public void checkEntity(T entity) {
        if (entity instanceof Album) {
            Album album = (Album) entity;
            HibernateUtil.checkAlbum(album);
        } else if (entity instanceof Artist) {
            Artist artist = (Artist) entity;
            HibernateUtil.checkArtist(artist);
        } else if (entity instanceof Genre) {
            Genre genre = (Genre) entity;
            HibernateUtil.checkGenre(genre);
        } else if (entity instanceof Playlist) {
            Playlist playlist = (Playlist) entity;
            HibernateUtil.checkPlaylist(playlist);
        } else if (entity instanceof Song) {
            Song song = (Song) entity;
            HibernateUtil.checkSong(song);
        } else {
            throw new IllegalArgumentException("Wrong type of entity!");
        }
    }
}
