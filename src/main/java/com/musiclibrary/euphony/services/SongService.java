package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.ArtistDTO;
import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Sebastian Lazon
 */
public interface SongService {

    void create(SongDTO song) throws DataAccessException;

    void update(SongDTO song) throws DataAccessException;

    void delete(SongDTO song) throws DataAccessException;

    SongDTO getById(Long id) throws DataAccessException;

    List<SongDTO> getAll() throws DataAccessException;

    List<SongDTO> getByTitle(String title) throws DataAccessException;

    List<SongDTO> getByGenre(GenreDTO genre) throws DataAccessException;

    List<SongDTO> getByArtist(ArtistDTO artist) throws DataAccessException;

    List<SongDTO> getByAlbum(AlbumDTO album) throws DataAccessException;
    
}
