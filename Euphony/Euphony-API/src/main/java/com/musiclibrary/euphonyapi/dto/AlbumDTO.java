package com.musiclibrary.euphonyapi.dto;

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
    
    private String comment;
    
    private DateTime releaseDate;
        
    private List<SongDTO> songs;
    
    private List<GenreDTO> genres;
    
    private List<ArtistDTO> artists;

    public AlbumDTO() {
        
    }

    public AlbumDTO(String title, String cover, DateTime releaseDate, List<SongDTO> songs,
            String comment, List<ArtistDTO> artists, List<GenreDTO> genres) {
        this.title = title;
        this.cover = cover;
        this.releaseDate = releaseDate;
        this.songs = songs;
        this.comment = comment;
        this.artists = artists;
        this.genres = genres;
        
    }

    public AlbumDTO(String title) {
        this.title = title;
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

    public List<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDTO> songs) {
        this.songs = songs;
    }
    
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }
    
}
