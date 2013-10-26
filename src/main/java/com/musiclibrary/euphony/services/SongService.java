/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import java.util.List;

/**
 *
 * @author Sebastian Lazon
 */
public interface SongService {
    void createSong(SongDTO song);
    void updateSong(SongDTO song);
    void deleteSong(SongDTO song);
    SongDTO get(Long id);
    List<SongDTO> byTitle(String title);
    List<SongDTO> getAll();
    List<SongDTO> byGenre(Genre genre);
    List<SongDTO> byArtist(Artist artist);
    List<SongDTO> byAlbum(Album album);
}
