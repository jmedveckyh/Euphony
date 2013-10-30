/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dto;

import com.musiclibrary.euphony.entities.Song;
import java.util.List;
import org.joda.time.DateTime;

/**
 *
 * @author Branislav Novotny <br.novotny@gmail.com> #396152
 */
public class AlbumDTO {
    
    private Long id;
    
    private String title;
    
    private String cover;
    
    private DateTime releaseDate;
        
    private List<Song> songs;

    public AlbumDTO() {
    }

    public AlbumDTO(String title, String cover, DateTime releaseDate, List<Song> songs) {
        this.title = title;
        this.cover = cover;
        this.releaseDate = releaseDate;
        this.songs = songs;
    }

    public AlbumDTO(String cover) {
        this.cover = cover;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public DateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(DateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
