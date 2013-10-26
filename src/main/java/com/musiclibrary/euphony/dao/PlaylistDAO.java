package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
import java.util.List;

/**
 * 
 * 
 * @author Tomas Smetanka #396209
 */
public interface PlaylistDAO extends DAO<Playlist> {
    
    public Playlist getByName(String name);
    
    public List<Playlist> getBySong(Song song);
    
}
