package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.services.GenreService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.GenreDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import java.util.List;
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

        genreDao.create(genreEntity);


        genre.setId(genreEntity.getId());
    }

    @Override
    @Transactional
    public void update(GenreDTO genre) throws DataAccessException {
        Genre genreEntity = DTOMapper.toEntity(genre);

        genreDao.update(genreEntity);

        genre = DTOMapper.toDTO(genreEntity);
    }

    @Override
    @Transactional
    public void delete(GenreDTO genre) throws DataAccessException {

        genreDao.delete(DTOMapper.toEntity(genre));

    }

    @Override
    @Transactional
    public GenreDTO getById(Long id) throws DataAccessException {

        return DTOMapper.toDTO(genreDao.getById(id));

    }

    @Override
    @Transactional
    public List<GenreDTO> getAll() throws DataAccessException {

        return DTOMapper.genresListToDTO(genreDao.getAll());

    }

    @Override
    @Transactional
    public GenreDTO getByName(String name) throws DataAccessException {

        return DTOMapper.toDTO(genreDao.getByName(name));

    }
}
