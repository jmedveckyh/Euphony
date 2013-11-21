package com.musiclibrary.euphony.services.impl;

import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.ArtistDTO;
import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.services.AlbumService;
import com.musiclibrary.euphony.dao.AlbumDAO;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.util.DTOMapper;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Branislav Novotny <br.novotny@gmail.com> #396152
 */
@Service
public class AlbumServiceImpl implements AlbumService{
    
    @Autowired
    private AlbumDAO albumDAO;
    
    public void setDAO(AlbumDAO albumDAO){
        this.albumDAO = albumDAO;
    }

    @Override
    @Transactional
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
    @Transactional
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
    @Transactional
    public void delete(AlbumDTO albumDTO) throws DataAccessException{
        try {
            albumDAO.delete(DTOMapper.toEntity(albumDTO));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public AlbumDTO getById(Long id) throws DataAccessException{
        try {
            return DTOMapper.toDTO(albumDAO.getById(id));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
    @Override
    @Transactional
    public AlbumDTO getByTitle(String title) throws DataAccessException{
        try {
            return DTOMapper.toDTO(albumDAO.getByTitle(title));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public List<AlbumDTO> getAllAlbums() throws DataAccessException{
        try {
            return DTOMapper.albumListToDTO(albumDAO.getAll());
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public List<AlbumDTO> getByReleaseYear(Integer year) throws DataAccessException{
        try {
            return DTOMapper.albumListToDTO(albumDAO.getByReleaseYear(year));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public List<AlbumDTO> getByGenre(GenreDTO genre) throws DataAccessException{
        try {
            return DTOMapper.albumListToDTO(albumDAO.getByGenre(DTOMapper.toEntity(genre)));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public List<AlbumDTO> getByArtist(ArtistDTO artist) throws DataAccessException{
        try {
            return DTOMapper.albumListToDTO(albumDAO.getByArtist(DTOMapper.toEntity(artist)));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }
    
}