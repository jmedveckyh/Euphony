package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.dto.PlaylistDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import java.util.List;

/**
 * 
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
    
}
