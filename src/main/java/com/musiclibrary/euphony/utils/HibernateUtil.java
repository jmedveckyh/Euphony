/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.utils;

import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
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

    public static void checkAlbum(Album album) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void checkArtist(Artist artist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    public static void checkPlaylist(Playlist playlist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void checkSong(Song song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
