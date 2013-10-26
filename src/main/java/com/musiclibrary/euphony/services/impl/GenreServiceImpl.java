/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services.impl;

import com.musiclibrary.euphony.dao.impl.GenreDAOImpl;
import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.services.GenreService;
import com.musiclibrary.euphony.util.DTOMapper;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Medo
 */
public class GenreServiceImpl implements GenreService {

    private GenreDAOImpl genreDao;

    public void setDAO(GenreDAOImpl genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public void create(GenreDTO genre) throws DataAccessException {
        Genre genreEntity = DTOMapper.toEntity(genre);
        try {
            genreDao.create(genreEntity);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }

        genre.setId(genreEntity.getId());
    }

    @Override
    public void update(GenreDTO genre) throws DataAccessException {
        Genre genreEntity = DTOMapper.toEntity(genre);
        try {
            genreDao.update(genreEntity);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
        genre = DTOMapper.toDTO(genreEntity);
    }

    @Override
    public void delete(GenreDTO genre) throws DataAccessException {
        try {
            genreDao.delete(DTOMapper.toEntity(genre));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public GenreDTO getById(Long id) throws DataAccessException {
        try {
            return DTOMapper.toDTO(genreDao.getById(id));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<GenreDTO> getAll() throws DataAccessException {
        try {
            return DTOMapper.genresListToDTO(genreDao.getAll());
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public GenreDTO getByName(String name) throws DataAccessException {
        try {
            return DTOMapper.toDTO(genreDao.getByName(name));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
}
