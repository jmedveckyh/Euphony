package com.musiclibrary.euphonyapi.services;

import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import java.util.List;

/**
 * Interface for Playlist service layer.
 * 
 * @author Tomas Smetanka #396209
 */
public interface PlaylistService {

    void create(PlaylistDTO playlistDTO);
    
    void update(PlaylistDTO playlistDTO);
    
    void delete(PlaylistDTO playlistDTO);
    
    PlaylistDTO getById(Long id);
    
    PlaylistDTO getByName(String name);
    
    List<PlaylistDTO> getBySong(SongDTO songDTO);
    
    List<PlaylistDTO> getAll();
    
}
