/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services.impl;

import com.musiclibrary.euphony.dao.SongDAO;
import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.ArtistDTO;
import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.entities.Song;
import com.musiclibrary.euphony.services.SongService;
import com.musiclibrary.euphony.util.DTOMapper;
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
    
    public void setDAO(SongDAO songDao){
        this.songDao=songDao;
    }

    @Override
    @Transactional
    public void create(SongDTO song) {
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
    public void update(SongDTO song) {
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
    public void delete(SongDTO song) {
        try {
            songDao.delete(DTOMapper.toEntity(song));
            } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };
        }
    }

    @Override
    @Transactional
    public SongDTO getById(Long id) {
        try {
        return DTOMapper.toDTO(songDao.getById(id));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }
    
    @Override
    @Transactional
    public List<SongDTO> getAll() {
        try {
        return DTOMapper.songsListToDTO(songDao.getAll());
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }

    @Override
    @Transactional
    public List<SongDTO> getByTitle(String title) {
        try {
        return DTOMapper.songsListToDTO(songDao.getByTitle(title));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }

    @Override
    @Transactional
    public List<SongDTO> getByGenre(GenreDTO genre) {
        try {
        return DTOMapper.songsListToDTO(songDao.getByGenre(DTOMapper.toEntity(genre)));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }

    @Override
    @Transactional
    public List<SongDTO> getByArtist(ArtistDTO artist) {
        try {
        return DTOMapper.songsListToDTO(songDao.getByArtist(DTOMapper.toEntity(artist)));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }

    @Override
    @Transactional
    public List<SongDTO> getByAlbum(AlbumDTO album) {
        try {
        return DTOMapper.songsListToDTO(songDao.getByAlbum(DTOMapper.toEntity(album)));
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {
            };}
    }
    
}
