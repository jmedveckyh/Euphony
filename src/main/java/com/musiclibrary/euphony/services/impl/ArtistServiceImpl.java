/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services.impl;

import com.musiclibrary.euphony.dao.impl.ArtistDAOImpl;
import com.musiclibrary.euphony.dto.ArtistDTO;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.services.ArtistService;
import com.musiclibrary.euphony.util.DTOMapper;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Medo
 */
public class ArtistServiceImpl implements ArtistService {

    private ArtistDAOImpl artistDao;

    public void setDAO(ArtistDAOImpl artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    public void create(ArtistDTO artist) throws DataAccessException {
        Artist artistEntity = DTOMapper.toEntity(artist);
        try {
            artistDao.create(artistEntity);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }

        artist.setId(artistEntity.getId());
    }

    @Override
    public void update(ArtistDTO artist) throws DataAccessException {
        Artist artistEntity = DTOMapper.toEntity(artist);
        try {
            artistDao.update(artistEntity);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
        artist = DTOMapper.toDTO(artistEntity);
    }

    @Override
    public void delete(ArtistDTO artist) throws DataAccessException {
        try {
            artistDao.delete(DTOMapper.toEntity(artist));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public ArtistDTO getById(Long id) throws DataAccessException {
        try {
            return DTOMapper.toDTO(artistDao.getById(id));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<ArtistDTO> getAll() throws DataAccessException {
        try {
            return DTOMapper.artistsListToDTO(artistDao.getAll());
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public ArtistDTO getByName(String name) throws DataAccessException {
        try {
            return DTOMapper.toDTO(artistDao.getByName(name));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
}
