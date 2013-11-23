package com.musiclibrary.euphonybusinesslogicimplementation.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * 
 * 
 * @author Sebastian
 */
@Entity
public class Song implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    private String title;
    
    private int bitrate;
    
    @Column(name="track_number")
    private int trackNumber;
    
    private String comment;
    
    @ManyToOne (optional=false,cascade= CascadeType.REFRESH)
    private Genre genre;
    
    @ManyToOne (optional=false,cascade= CascadeType.REFRESH)
    private Album album;
    
    @ManyToOne (optional=false,cascade= CascadeType.REFRESH)
    private Artist artist;

    @ManyToOne
    private Playlist playlist;            
            
    public Song(){ }

    public Song(String title, int bitrate, int trackNumber, String comment, Genre genre, Album album, Artist artist) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Song other = (Song) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Song{" + "id=" + id + ", title=" + title + ", bitrate=" + bitrate + ", trackNumber=" + trackNumber + ", comment=" + comment + ", genre=" + genre + ", album=" + album + ", artist=" + artist + '}';
    }
    
}
