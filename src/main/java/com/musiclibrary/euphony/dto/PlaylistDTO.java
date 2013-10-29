package com.musiclibrary.euphony.dto;

import com.musiclibrary.euphony.entities.Song;
import java.util.Map;

/**
 * 
 * 
 * @author Tomas Smetanka #396209
 */
public class PlaylistDTO { 
    
    private Long id;
    
    private String name;
    
    private Map<Integer, SongDTO> songs;

    public PlaylistDTO() {
    }

    public PlaylistDTO(String name) {
        this.name = name;
    }
    
    public PlaylistDTO(String name, Map<Integer, SongDTO> songs) {
        this.name = name;
        this.songs = songs;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(Map<Integer, SongDTO> songs) {
        this.songs = songs;
    }
    
}
