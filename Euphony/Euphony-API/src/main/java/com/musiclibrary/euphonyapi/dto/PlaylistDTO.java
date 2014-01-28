package com.musiclibrary.euphonyapi.dto;

import java.util.Map;

/**
 * Playlist DTO layer.
 *
 * @author Tomas Smetanka
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlaylistDTO other = (PlaylistDTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
