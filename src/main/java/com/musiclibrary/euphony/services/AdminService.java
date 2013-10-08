package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Song;
import com.musiclibrary.euphony.entities.User;

/**
 * 
 * 
 * @author Tomas Smetanka #396209
 */
public interface AdminService {

    Song getSong(Long id);
    
    void addSong(Song song);
    
    void updateSong(Song song);
    
    void removeSong(Song song);
    
    Artist getArtist(Long id);
    
    void addArtist(Artist artist);
    
    void updateArtist(Artist artist);
    
    void removeArtist(Artist artist);
    
    Genre getGenre(Long id);
    
    void addGenre(Genre genre);
    
    void updateGenre(Genre genre);
    
    void removeGenre(Genre genre);
    
    Album getAlbum(Long id);
    
    void addAlbum(Album album);
    
    void updateAlbum(Album album);
    
    void removeAlbum(Album album);
    
    User getUser(Long id);
    
    void addUser(User user);
    
    void updateUser(User user);
    
    void removeUser(User user);
    
    
}
