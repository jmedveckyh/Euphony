package com.musiclibrary.euphonyapi.dto;

import java.util.Objects;

/**
 *
 * @author Sebastian Lazon
 */
public class SongDTO {

    private Long id;
    private String title;
    private int bitrate;
    private int trackNumber;
    private String comment;
    private GenreDTO genre;
    private AlbumDTO album;
    private ArtistDTO artist;

    public SongDTO() {
    }

    public SongDTO(String title) {
        this.title = title;
    }

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final SongDTO other = (SongDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
