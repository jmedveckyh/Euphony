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
    void create(SongDTO song);
    void update(SongDTO song);
    void delete(SongDTO song);
    SongDTO getById(Long id);
    List<SongDTO> getAll();
    List<SongDTO> getByTitle(String title);
    List<SongDTO> getByGenre(Genre genre);
    List<SongDTO> getByArtist(Artist artist);
    List<SongDTO> getByAlbum(Album album);
}
