package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.AlbumDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Branislav Novotny <br.novotny@gmail.com> #396152
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDAO albumDAO;

    public void setDAO(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @Override
    @Transactional
    public void create(AlbumDTO albumDTO) throws DataAccessException {

        albumDAO.create(DTOMapper.toEntity(albumDTO));

        albumDTO.setId(DTOMapper.toEntity(albumDTO).getId());
    }

    @Override
    @Transactional
    public void update(AlbumDTO albumDTO) throws DataAccessException {
        Album albumEntity = DTOMapper.toEntity(albumDTO);

        albumDAO.update(albumEntity);

        albumDTO = DTOMapper.toDTO(albumEntity);
    }

    @Override
    @Transactional
    public void delete(AlbumDTO albumDTO) throws DataAccessException {

        albumDAO.delete(DTOMapper.toEntity(albumDTO));

    }

    @Override
    @Transactional
    public AlbumDTO getById(Long id) throws DataAccessException {

        return DTOMapper.toDTO(albumDAO.getById(id));

    }

    @Override
    @Transactional
    public AlbumDTO getByTitle(String title) throws DataAccessException {

        return DTOMapper.toDTO(albumDAO.getByTitle(title));

    }

    @Override
    @Transactional
    public List<AlbumDTO> getAllAlbums() throws DataAccessException {

        return DTOMapper.albumListToDTO(albumDAO.getAll());

    }

    @Override
    @Transactional
    public List<AlbumDTO> getByReleaseYear(Integer year) throws DataAccessException {

        return DTOMapper.albumListToDTO(albumDAO.getByReleaseYear(year));

    }

    @Override
    @Transactional
    public List<AlbumDTO> getByGenre(GenreDTO genre) throws DataAccessException {

        return DTOMapper.albumListToDTO(albumDAO.getByGenre(DTOMapper.toEntity(genre)));

    }

    @Override
    @Transactional
    public List<AlbumDTO> getByArtist(ArtistDTO artist) throws DataAccessException {

        return DTOMapper.albumListToDTO(albumDAO.getByArtist(DTOMapper.toEntity(artist)));

    }
}