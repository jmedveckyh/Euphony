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
public class SongServiceImpl implements SongService {

    @Autowired
    private SongDAO songDao;

    public void setDAO(SongDAO songDao) {
        this.songDao = songDao;
    }

    @Override
    @Transactional
    public void create(SongDTO song) throws DataAccessException {
        Song songEntity = DTOMapper.toEntity(song);

        songDao.create(songEntity);

        song.setId(songEntity.getId());
    }

    @Override
    @Transactional
    public void update(SongDTO song) throws DataAccessException {
        Song songEntity = DTOMapper.toEntity(song);

        songDao.update(songEntity);

        song = DTOMapper.toDTO(songEntity);
    }

    @Override
    @Transactional
    public void delete(SongDTO song) throws DataAccessException {

        songDao.delete(DTOMapper.toEntity(song));

    }

    @Override
    @Transactional
    public SongDTO getById(Long id) throws DataAccessException {

        return DTOMapper.toDTO(songDao.getById(id));

    }

    @Override
    @Transactional
    public List<SongDTO> getAll() throws DataAccessException {

        return DTOMapper.songsListToDTO(songDao.getAll());

    }

    @Override
    @Transactional
    public List<SongDTO> getByTitle(String title) throws DataAccessException {

        return DTOMapper.songsListToDTO(songDao.getByTitle(title));

    }

    @Override
    @Transactional
    public List<SongDTO> getByGenre(GenreDTO genre) throws DataAccessException {

        return DTOMapper.songsListToDTO(songDao.getByGenre(DTOMapper.toEntity(genre)));

    }

    @Override
    @Transactional
    public List<SongDTO> getByArtist(ArtistDTO artist) throws DataAccessException {

        return DTOMapper.songsListToDTO(songDao.getByArtist(DTOMapper.toEntity(artist)));

    }

    @Override
    @Transactional
    public List<SongDTO> getByAlbum(AlbumDTO album) throws DataAccessException {

        return DTOMapper.songsListToDTO(songDao.getByAlbum(DTOMapper.toEntity(album)));

    }
}
