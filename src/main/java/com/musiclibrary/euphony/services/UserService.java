/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public interface UserService {
    
    void createPlaylist();
    
    Playlist getPlaylist();
    
    List<Playlist> getAllPlaylists();
    
    void updatePlaylist(Playlist playlist);
    
    void deletePlaylist(Playlist playlist);
    
    void updateMyMusic(List<Song> songs);
    
    List<Song> getMyMusic();
    
}
