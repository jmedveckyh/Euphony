package com.musiclibrary.euphonyapi.services;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
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

    List<SongDTO> getSongsByTitleSub(String phrase);
}
