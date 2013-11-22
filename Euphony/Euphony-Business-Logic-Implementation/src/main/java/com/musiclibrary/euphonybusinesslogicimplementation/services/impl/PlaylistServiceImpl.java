package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.PlaylistDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of Playlist service layer.
 *
 * @author Tomas Smetanka #396209
 */
@Service
@Transactional
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistDAO playlistDAO;      // TODO Dao<Playlist>?

    public void setDAO(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Override
    public void create(PlaylistDTO playlistDTO) throws DataAccessException {

        Playlist playlist = DTOMapper.toEntity(playlistDTO);


        playlistDAO.create(playlist);

        playlistDTO.setId(playlist.getId());

    }

    @Override
    public void update(PlaylistDTO playlistDTO) throws DataAccessException {

        Playlist playlist = DTOMapper.toEntity(playlistDTO);


        playlistDAO.update(playlist);


        playlistDTO = DTOMapper.toDTO(playlist);

    }

    @Override
    public void delete(PlaylistDTO playlistDTO) throws DataAccessException {

        Playlist playlist = DTOMapper.toEntity(playlistDTO);


        playlistDAO.delete(playlist);

    }

    @Override
    public PlaylistDTO getById(Long id) throws DataAccessException {

        Playlist playlist = new Playlist();


        playlist = playlistDAO.getById(id);


        return DTOMapper.toDTO(playlist);

    }

    @Override
    public PlaylistDTO getByName(String name) throws DataAccessException {

        Playlist playlist = new Playlist();


        playlist = playlistDAO.getByName(name);


        return DTOMapper.toDTO(playlist);

    }

    @Override
    public List<PlaylistDTO> getBySong(SongDTO songDTO) throws DataAccessException {

        Song song = DTOMapper.toEntity(songDTO);
        List<Playlist> playlists = new ArrayList<>();


        playlists = playlistDAO.getBySong(song);


        return DTOMapper.playlistListToDTO(playlists);

    }

    @Override
    public List<PlaylistDTO> getAll() throws DataAccessException {

        List<Playlist> playlists = new ArrayList<>();


        playlists = playlistDAO.getAll();


        return DTOMapper.playlistListToDTO(playlists);

    }
}