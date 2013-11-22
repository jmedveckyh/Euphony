package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.ArtistDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
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
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistDAO artistDao;

    public void setDAO(ArtistDAO artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    @Transactional
    public void create(ArtistDTO artist) throws DataAccessException {
        Artist artistEntity = DTOMapper.toEntity(artist);

        artistDao.create(artistEntity);

        artist.setId(artistEntity.getId());
    }

    @Override
    @Transactional
    public void update(ArtistDTO artist) throws DataAccessException {
        Artist artistEntity = DTOMapper.toEntity(artist);

        artistDao.update(artistEntity);

        artist = DTOMapper.toDTO(artistEntity);
    }

    @Override
    @Transactional
    public void delete(ArtistDTO artist) throws DataAccessException {

        artistDao.delete(DTOMapper.toEntity(artist));

    }

    @Override
    @Transactional
    public ArtistDTO getById(Long id) throws DataAccessException {

        return DTOMapper.toDTO(artistDao.getById(id));

    }

    @Override
    @Transactional
    public List<ArtistDTO> getAll() throws DataAccessException {

        return DTOMapper.artistsListToDTO(artistDao.getAll());

    }

    @Override
    @Transactional
    public ArtistDTO getByName(String name) throws DataAccessException {

        return DTOMapper.toDTO(artistDao.getByName(name));

    }
}
