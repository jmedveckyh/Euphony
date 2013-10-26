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
    
    private Map<Integer, Song> songs;

    public PlaylistDTO() {
    }
    
    public PlaylistDTO(String name, Map<Integer, Song> songs) {
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

    public Map<Integer, Song> getSongs() {
        return songs;
    }

    public void setSongs(Map<Integer, Song> songs) {
        this.songs = songs;
    }
    
}
