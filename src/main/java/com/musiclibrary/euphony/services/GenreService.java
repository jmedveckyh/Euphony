/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.dto.GenreDTO;
import org.springframework.dao.DataAccessException;
import java.util.List;

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
