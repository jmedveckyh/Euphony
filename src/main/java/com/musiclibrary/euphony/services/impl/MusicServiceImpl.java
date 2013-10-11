package com.musiclibrary.euphony.services.impl;

import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
import com.musiclibrary.euphony.services.MusicService;
import java.util.List;

/**
 * Music service layer implementation.
 * 
 * @author Tomáš Smetanka 
 */

public class MusicServiceImpl implements MusicService {

    public List<Playlist> getAllPlaylists() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addSongsToPlaylist(List<Song> songs, Playlist playlist) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeSongsFromPlaylist(List<Song> songs, Playlist playlist) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Song> getMyMusic() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void addSongsToMyMusic(List<Song> songs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeSongsFromMyMusic(List<Song> songs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
