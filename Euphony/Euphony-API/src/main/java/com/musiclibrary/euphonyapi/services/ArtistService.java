package com.musiclibrary.euphonyapi.services;

import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Medo
 */
public interface ArtistService {
    
    void create(ArtistDTO artistDTO) throws DataAccessException;

    void update(ArtistDTO artistDTO) throws DataAccessException;

    void delete(ArtistDTO artistDTO) throws DataAccessException;

    ArtistDTO getById(Long id) throws DataAccessException;

    List<ArtistDTO> getAll() throws DataAccessException;

    ArtistDTO getByName(String name) throws DataAccessException;

    List<SongDTO> getSongsByArtist(ArtistDTO artist) throws DataAccessException;
}
