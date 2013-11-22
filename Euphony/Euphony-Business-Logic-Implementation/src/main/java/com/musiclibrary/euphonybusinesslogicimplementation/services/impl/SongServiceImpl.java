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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Sebastian
 */
@Service
@Transactional
public class SongServiceImpl implements SongService {

    @Autowired
    private SongDAO songDAO;

    public void setDAO(SongDAO songDAO) {
        this.songDAO = songDAO;
    }

    @Override
    public void create(SongDTO song) throws DataAccessException {
        Song songEntity = DTOMapper.toEntity(song);

        songDAO.create(songEntity);

        song.setId(songEntity.getId());
    }

    @Override
    public void update(SongDTO song) throws DataAccessException {
        Song songEntity = DTOMapper.toEntity(song);

        songDAO.update(songEntity);

        song = DTOMapper.toDTO(songEntity);
    }

    @Override
    public void delete(SongDTO song) throws DataAccessException {

        songDAO.delete(DTOMapper.toEntity(song));

    }

    @Override
    public SongDTO getById(Long id) throws DataAccessException {

        return DTOMapper.toDTO(songDAO.getById(id));

    }

    @Override
    public List<SongDTO> getAll() throws DataAccessException {

        return DTOMapper.songsListToDTO(songDAO.getAll());

    }

    @Override
    public List<SongDTO> getByTitle(String title) throws DataAccessException {

        return DTOMapper.songsListToDTO(songDAO.getByTitle(title));

    }

    @Override
    public List<SongDTO> getByGenre(GenreDTO genre) throws DataAccessException {

        return DTOMapper.songsListToDTO(songDAO.getByGenre(DTOMapper.toEntity(genre)));

    }

    @Override
    public List<SongDTO> getByArtist(ArtistDTO artist) throws DataAccessException {

        return DTOMapper.songsListToDTO(songDAO.getByArtist(DTOMapper.toEntity(artist)));

    }

    @Override
    public List<SongDTO> getByAlbum(AlbumDTO album) throws DataAccessException {

        return DTOMapper.songsListToDTO(songDAO.getByAlbum(DTOMapper.toEntity(album)));

    }
}
