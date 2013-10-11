/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dao.impl;

import com.musiclibrary.euphony.dao.DAO;
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
        if (entity == null) {
            throw new IllegalArgumentException(this.getClass().getName() + " entity cannot be null.");
        }
        em.persist(entity);
    }

    public void update(T entity) {
        EntityManager em = this.getEntityManager();
        if (entity == null) {
            throw new IllegalArgumentException(this.getClass().getName() + " entity cannot be null.");
        }

        em.merge(entity);
    }

    public void delete(T entity) {
        EntityManager em = this.getEntityManager();
        if (entity == null) {
            throw new IllegalArgumentException(this.getClass().getName() + " entity cannot be null.");
        }

        try {
            if (em.find(entity.getClass(), entity.getClass().getMethod("getId").invoke(entity, null)) == null) {
                throw new IllegalArgumentException("This " + this.getClass().getName() + " entity does not exist in database.");
            }
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
}
