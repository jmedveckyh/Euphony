/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
import java.util.List;

/**
 * Interface for Music service layer.
 * 
 * @author Tomáš Smetanka
 */
public interface MusicService {
    
    public List<Playlist> getAllPlaylists();
    
    public void addSongsToPlaylist(List<Song> songs, Playlist playlist);
    
    public void removeSongsFromPlaylist(List<Song> songs, Playlist playlist);
    
    public List<Song> getMyMusic();
    
    public void addSongsToMyMusic(List<Song> songs);
    
    public void removeSongsFromMyMusic(List<Song> songs);
    
    
}
