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
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreDAO genreDAO;

    public void setDAO(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    @Override
    public void create(GenreDTO genre) throws DataAccessException {
        Genre genreEntity = DTOMapper.toEntity(genre);

        genreDAO.create(genreEntity);


        genre.setId(genreEntity.getId());
    }

    @Override
    public void update(GenreDTO genre) throws DataAccessException {
        Genre genreEntity = DTOMapper.toEntity(genre);

        genreDAO.update(genreEntity);

        genre = DTOMapper.toDTO(genreEntity);
    }

    @Override
    public void delete(GenreDTO genre) throws DataAccessException {

        genreDAO.delete(DTOMapper.toEntity(genre));

    }

    @Override
    public GenreDTO getById(Long id) throws DataAccessException {

        return DTOMapper.toDTO(genreDAO.getById(id));

    }

    @Override
    public List<GenreDTO> getAll() throws DataAccessException {

        return DTOMapper.genresListToDTO(genreDAO.getAll());

    }

    @Override
    public GenreDTO getByName(String name) throws DataAccessException {

        return DTOMapper.toDTO(genreDAO.getByName(name));

    }
}
