/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.util;

import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;

/**
 *
 * @author Sebastian
 */
public class Util {
    public static void validateAlbum(Album album) {
        if (album == null){
            throw new IllegalArgumentException("Album cannot be null.");
        }
        if (album.getReleaseDate() == null) {
            throw new IllegalArgumentException("Album's release date is null.");
        }
        if ("".equals(album.getTitle())) {
            throw new IllegalArgumentException("Album's title is empty.");
        }
        if (album.getTitle() == null) {
            throw new IllegalArgumentException("Album's title is null.");
        }
        if (album.getSongs() == null) {
            throw new IllegalArgumentException("Album's songs is null.");
        }
        for(Song s : album.getSongs()){
            if (s == null) {
                throw new IllegalArgumentException("Album's list of songs contains null song.");
            }
        }
    }
    
    public static void validateArtist(Artist artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null.");
        }
        if (artist.getName() == null) {
            throw new IllegalArgumentException("Artist's name is null.");
        }
        if ("".equals(artist.getName())) {
            throw new IllegalArgumentException("Artist's name is empty.");
        }
    }
    
    public static void validateGenre(Genre genre) {
        if (genre == null){
            throw new IllegalArgumentException("Genre cannot be null.");
        }
        if (genre.getName() == null) {
            throw new IllegalArgumentException("Genre's name is null.");
        }
        if ("".equals(genre.getName())) {
            throw new IllegalArgumentException("Genre's name is empty.");
        }
    }
        
    public static void validatePlaylist(Playlist playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist cannot be null.");
        }
        if (playlist.getName() == null) {
            throw new IllegalArgumentException("Playlist's name is null.");
        }
        if ("".equals(playlist.getName())) {
            throw new IllegalArgumentException("Playlist's name is empty.");
        }
    }
    
    public static void validateSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null.");
        }
        if (song.getTitle() == null) {
            throw new IllegalArgumentException("Song's title cannot be null.");
        }
        if ("".equals(song.getTitle())) {
            throw new IllegalArgumentException("Song's title cannot be empty.");
        }
        if (song.getGenre() == null) {
            throw new IllegalArgumentException("Song's genre cannot be null.");
        }
        if (song.getAlbum() == null) {
            throw new IllegalArgumentException("Song's album cannot be null.");
        }
        if (song.getArtist() == null) {
            throw new IllegalArgumentException("Song's artist cannot be null.");
        }
        if (song.getBitrate() < 1 || song.getBitrate() > 2500) {
            throw new IllegalArgumentException("Song's bitrate must be between 1 and 2500.");
        }
        if (song.getTrackNumber() <= 0) {
            throw new IllegalArgumentException("Song's track number must be bigger than 0.");
        }
    }
}
