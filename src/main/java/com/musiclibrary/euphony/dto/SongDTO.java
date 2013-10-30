/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dto;

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
    private GenreDTO genre;
    private AlbumDTO album;
    private ArtistDTO artist;

    public SongDTO(){ }

    public SongDTO(String title, int bitrate, int trackNumber, String comment, GenreDTO genre, AlbumDTO album, ArtistDTO artist) {
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

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }
    
    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }
}
