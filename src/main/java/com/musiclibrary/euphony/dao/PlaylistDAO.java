package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
import java.util.List;

/**
 * Interface for Playlist DAO layer.
 * 
 * @author Tomas Smetanka #396209
 */
public interface PlaylistDAO extends DAO<Playlist> {
    
    Playlist getByName(String name);
    
    List<Playlist> getBySong(Song song);
    
    List<Playlist> getAll();
    
}
