/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.ArtistDTO;
import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.dto.SongDTO;
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
    List<SongDTO> getByGenre(GenreDTO genre);
    List<SongDTO> getByArtist(ArtistDTO artist);
    List<SongDTO> getByAlbum(AlbumDTO album);
}
