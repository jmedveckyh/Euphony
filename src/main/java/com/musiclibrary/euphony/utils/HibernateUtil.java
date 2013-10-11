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
        if (artist == null){
            throw new IllegalArgumentException("Artist cannot be null.");
        }
        if (artist.getName() == null) {
            throw new IllegalArgumentException("Artist's name is null.");
        }
        if ("".equals(artist.getName())) {
            throw new IllegalArgumentException("Artist's name is empty.");
        }
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
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist cannot be null.");
        }
        if (playlist.getName() == null) {
            throw new IllegalArgumentException("Playlist's name is null.");
        }
        if ("".equals(playlist.getName())) {
            throw new IllegalArgumentException("Playlist's name is empty.");
        }
    }

    public static void checkSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null.");
        }
        if (song.getTitle() == null) {
            throw new IllegalArgumentException("Song's title cannot be null.");
        }
        if ("".equals(song.getTitle())) {
            throw new IllegalArgumentException("Song's title cannot be empty.");
        }
        if (song.getGenre() == null) {
            throw new IllegalArgumentException("Song's genre cannot be null.");
        }
        if (song.getAlbum() == null) {
            throw new IllegalArgumentException("Song's album cannot be null.");
        }
        if (song.getBitrate() < 0 || song.getBitrate() > 2500) {
            throw new IllegalArgumentException("Song's bitrate must be between 0 and 2500.");
        }
        if (song.getTrackNumber() <= 0) {
            throw new IllegalArgumentException("Song's track number must be bigger than 0.");
        }
    }
    
}
