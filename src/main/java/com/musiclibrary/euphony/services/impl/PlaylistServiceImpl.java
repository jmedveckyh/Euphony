package com.musiclibrary.euphony.services.impl;

import com.musiclibrary.euphony.dao.PlaylistDAO;
import com.musiclibrary.euphony.dao.impl.PlaylistDAOImpl;
import com.musiclibrary.euphony.dto.PlaylistDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
import com.musiclibrary.euphony.services.PlaylistService;
import com.musiclibrary.euphony.util.DTOMapper;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.PersistenceException;
import org.springframework.dao.DataAccessException;

/**
 * Implementation of Playlist Service layer.
 * 
 * @author Tomas Smetanka #396209
 */
public class PlaylistServiceImpl implements PlaylistService {
    
    private PlaylistDAO playlistDAO;      // TODO Dao<Playlist>?
    
    public void setPlaylistDAO(PlaylistDAOImpl playlistDAO) {
        this.playlistDAO = playlistDAO;
    }
    
    @Override
    public void create(PlaylistDTO playlistDTO) throws DataAccessException {
        
        Playlist playlist = DTOMapper.toEntity(playlistDTO);
        
        try {
            playlistDAO.create(playlist);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {};
        }
        
        playlistDTO.setId(playlist.getId());
        
    }

    @Override
    public void update(PlaylistDTO playlistDTO) throws DataAccessException {
        
        Playlist playlist = DTOMapper.toEntity(playlistDTO);
        
        try {
            playlistDAO.update(playlist);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {};
        }
        
        playlistDTO = DTOMapper.toDTO(playlist);
        
    }

    @Override
    public void delete(PlaylistDTO playlistDTO) throws DataAccessException {
        
        Playlist playlist = DTOMapper.toEntity(playlistDTO);
        
        try {
            playlistDAO.delete(playlist);
        } catch (IllegalArgumentException | PersistenceException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {};
        }
        
    }

    @Override
    public PlaylistDTO getById(Long id) throws DataAccessException {
        
        Playlist playlist = new Playlist();
        
        try {
            playlist = playlistDAO.getById(id);
        } catch (IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {};
        }
        
        return DTOMapper.toDTO(playlist);
        
    }

    @Override
    public PlaylistDTO getByName(String name) throws DataAccessException {
        
        Playlist playlist = new Playlist();
        
        try {
            playlist = playlistDAO.getByName(name);
        } catch (IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {};
        }
        
        return DTOMapper.toDTO(playlist);
        
    }

    @Override
    public List<PlaylistDTO> getBySong(SongDTO songDTO) throws DataAccessException {
        
        Song song = DTOMapper.toEntity(songDTO);
        List<Playlist> playlists = new ArrayList<>();
        
        try {
            playlists = playlistDAO.getBySong(song);
        } catch (IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage(), ex) {};
        }
        
        return DTOMapper.playlistListToDTO(playlists);
        
    }
    
}