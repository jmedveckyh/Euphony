/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Song;
import java.util.List;

/**
 *
 * @author Sebastian Lazon
 */
public interface SongService {
    void createSong(Song song);
    void updateSong(Song song);
    void deleteSong(Song song);
    Song get(Long id);
    Song byTitle(String title);
    List<Song> getAll();
    List<Song> byGenre(Genre genre);
    List<Song> byArtist(Artist artist);
    List<Song> byAlbum(Album album);
}
