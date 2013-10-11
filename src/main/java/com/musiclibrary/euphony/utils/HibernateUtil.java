/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Medo
 */
public class HibernateUtil {

    private static final EntityManagerFactory emf;
    private static final EntityManager em;
    
    static {
        try {
            emf = Persistence.createEntityManagerFactory("EuphonyPU");
            em = emf.createEntityManager();
        } catch (Throwable ex) {
            System.err.println("Initial EntityManagerFactory or EntityManager creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
    
    public static EntityManager getEntityManager() {
        return em;
    }

    public static void beginTransaction() {
        HibernateUtil.getEntityManager().getTransaction().begin();
    }

    public static void commitTransaction() {
        HibernateUtil.getEntityManager().getTransaction().commit();
    }

    public static void rollbackTransaction() {
        HibernateUtil.getEntityManager().getTransaction().rollback();
    }

    public static void closeEntityManager() {
        HibernateUtil.getEntityManager().close();
    }
}
