/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dto;

import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;

/**
 *
 * @author Sebastian Lazon
 */
public class SongDTO{
    
    private Long id;
    private String title;
    private int bitrate;
    private int trackNumber;
    private String comment;
    private Genre genre;
    private Album album;
    private Artist artist;

    public SongDTO(){ }

    public SongDTO(String title, int bitrate, int trackNumber, String comment, Genre genre, Album album, Artist artist) {
        this.title = title;
        this.bitrate = bitrate;
        this.trackNumber = trackNumber;
        this.comment = comment;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
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

    public int getBitrate() {
        return bitrate;
    }

    public void setBitrate(int bitrate) {
        this.bitrate = bitrate;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
