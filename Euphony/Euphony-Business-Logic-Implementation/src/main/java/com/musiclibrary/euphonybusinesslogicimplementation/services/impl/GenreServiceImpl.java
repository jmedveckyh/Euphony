package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.services.GenreService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.GenreDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Medo
 */

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDAO genreDao;

    public void setDAO(GenreDAO genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void delete(GenreDTO genre) throws DataAccessException {
        try {
            genreDao.delete(DTOMapper.toEntity(genre));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public GenreDTO getById(Long id) throws DataAccessException {
        try {
            return DTOMapper.toDTO(genreDao.getById(id));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public List<GenreDTO> getAll() throws DataAccessException {
        try {
            return DTOMapper.genresListToDTO(genreDao.getAll());
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public GenreDTO getByName(String name) throws DataAccessException {
        try {
            return DTOMapper.toDTO(genreDao.getByName(name));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
}
