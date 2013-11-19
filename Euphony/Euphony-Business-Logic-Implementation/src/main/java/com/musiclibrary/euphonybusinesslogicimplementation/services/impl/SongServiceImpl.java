package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.SongService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.SongDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sebastian
 */
@Service
public class SongServiceImpl implements SongService{
    
    @Autowired
    private SongDAO songDao;
    
    public void setDAO(SongDAO songDao) {
        this.songDao = songDao;
    }

    @Override
    @Transactional
    public void create(SongDTO song) throws DataAccessException {
        Song songEntity = DTOMapper.toEntity(song);
        try {
            songDao.create(songEntity);
            } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
        song.setId(songEntity.getId());
    }

    @Override
    @Transactional
    public void update(SongDTO song) throws DataAccessException{
        Song songEntity = DTOMapper.toEntity(song);
        try {
            songDao.update(songEntity);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
        song = DTOMapper.toDTO(songEntity);
    }

    @Override
    @Transactional
    public void delete(SongDTO song) throws DataAccessException{
        try {
            songDao.delete(DTOMapper.toEntity(song));
            } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public SongDTO getById(Long id) throws DataAccessException{
        try {
        return DTOMapper.toDTO(songDao.getById(id));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }
    
    @Override
    @Transactional
    public List<SongDTO> getAll() throws DataAccessException{
        try {
        return DTOMapper.songsListToDTO(songDao.getAll());
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }

    @Override
    @Transactional
    public List<SongDTO> getByTitle(String title) throws DataAccessException{
        try {
        return DTOMapper.songsListToDTO(songDao.getByTitle(title));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }

    @Override
    @Transactional
    public List<SongDTO> getByGenre(GenreDTO genre) throws DataAccessException{
        try {
        return DTOMapper.songsListToDTO(songDao.getByGenre(DTOMapper.toEntity(genre)));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }

    @Override
    @Transactional
    public List<SongDTO> getByArtist(ArtistDTO artist) throws DataAccessException{
        try {
        return DTOMapper.songsListToDTO(songDao.getByArtist(DTOMapper.toEntity(artist)));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }

    @Override
    @Transactional
    public List<SongDTO> getByAlbum(AlbumDTO album) throws DataAccessException{
        try {
        return DTOMapper.songsListToDTO(songDao.getByAlbum(DTOMapper.toEntity(album)));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }
    
}