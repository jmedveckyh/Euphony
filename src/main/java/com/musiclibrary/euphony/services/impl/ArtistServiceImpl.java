/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services.impl;

import com.musiclibrary.euphony.dao.ArtistDAO;
import com.musiclibrary.euphony.dto.ArtistDTO;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.services.ArtistService;
import com.musiclibrary.euphony.util.DTOMapper;
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
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistDAO artistDao;

    @Override
    public void setDAO(ArtistDAO artistDao) {
        this.artistDao = artistDao;
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void delete(ArtistDTO artist) throws DataAccessException {
        try {
            artistDao.delete(DTOMapper.toEntity(artist));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public ArtistDTO getById(Long id) throws DataAccessException {
        try {
            return DTOMapper.toDTO(artistDao.getById(id));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public List<ArtistDTO> getAll() throws DataAccessException {
        try {
            return DTOMapper.artistsListToDTO(artistDao.getAll());
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public ArtistDTO getByName(String name) throws DataAccessException {
        try {
            return DTOMapper.toDTO(artistDao.getByName(name));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
}
