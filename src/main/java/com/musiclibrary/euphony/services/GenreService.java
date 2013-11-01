package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.dto.GenreDTO;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Medo
 */
public interface GenreService {

    void create(GenreDTO genreDTO) throws DataAccessException;

    void update(GenreDTO genreDTO) throws DataAccessException;

    void delete(GenreDTO genreDTO) throws DataAccessException;

    GenreDTO getById(Long id) throws DataAccessException;

    List<GenreDTO> getAll() throws DataAccessException;

    GenreDTO getByName(String name) throws DataAccessException;
}
