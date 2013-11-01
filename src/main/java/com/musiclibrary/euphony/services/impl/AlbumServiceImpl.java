/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services.impl;

import com.musiclibrary.euphony.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.services.AlbumService;
import com.musiclibrary.euphony.util.DTOMapper;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Branislav Novotny <br.novotny@gmail.com> #396152
 */
public class AlbumServiceImpl implements AlbumService{
    
    private AlbumDAOImpl albumDAO;
    
    public void setDAO(AlbumDAOImpl albumDAO){
        this.albumDAO = albumDAO;
    }

    @Override
    public void create(AlbumDTO albumDTO) throws DataAccessException{
        Album albumEntity = DTOMapper.toEntity(albumDTO);
        try {
            albumDAO.create(albumEntity);
            
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
        albumDTO.setId(albumEntity.getId());
    }

    @Override
    public void update(AlbumDTO albumDTO) throws DataAccessException{
        Album albumEntity = DTOMapper.toEntity(albumDTO);
        try {
            albumDAO.update(albumEntity);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
        albumDTO = DTOMapper.toDTO(albumEntity);
    }

    @Override
    public void delete(AlbumDTO albumDTO) throws DataAccessException{
        try {
            albumDAO.delete(DTOMapper.toEntity(albumDTO));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public AlbumDTO getById(Long id) throws DataAccessException{
        try {
            return DTOMapper.toDTO(albumDAO.getById(id));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
    @Override
    public AlbumDTO getByTitle(String title) throws DataAccessException{
        try {
            return DTOMapper.toDTO(albumDAO.getByTitle(title));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<AlbumDTO> getAllAlbums() throws DataAccessException{
        try {
            return DTOMapper.albumListToDTO(albumDAO.getAll());
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<AlbumDTO> getByReleaseYear(Integer year) throws DataAccessException{
        try {
            return DTOMapper.albumListToDTO(albumDAO.getByReleaseYear(year));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<AlbumDTO> getByGenre(Genre genre) throws DataAccessException{
        try {
            return DTOMapper.albumListToDTO(albumDAO.getByGenre(genre));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    public List<AlbumDTO> getByArtist(Artist artist) throws DataAccessException{
        try {
            return DTOMapper.albumListToDTO(albumDAO.getByArtist(artist));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
}