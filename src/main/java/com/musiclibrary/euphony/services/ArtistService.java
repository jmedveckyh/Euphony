package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.dto.ArtistDTO;
import org.springframework.dao.DataAccessException;
import java.util.List;

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
}
