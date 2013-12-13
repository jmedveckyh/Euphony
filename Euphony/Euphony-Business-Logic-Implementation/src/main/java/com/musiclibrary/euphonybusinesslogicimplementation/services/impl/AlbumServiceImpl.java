package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.AlbumDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import com.musiclibrary.euphonybusinesslogicimplementation.util.Util;
import java.util.ArrayList;
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
@Transactional
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumDAO albumDAO;

    public void setDAO(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @Override
    public void create(AlbumDTO albumDTO) throws DataAccessException {

        albumDAO.create(DTOMapper.toEntity(albumDTO));

        albumDTO.setId(DTOMapper.toEntity(albumDTO).getId());
    }

    @Override
    public void update(AlbumDTO albumDTO) throws DataAccessException {
        Album albumEntity = DTOMapper.toEntity(albumDTO);

        albumDAO.update(albumEntity);

        albumDTO = DTOMapper.toDTO(albumEntity);
    }

    @Override
    public void delete(AlbumDTO albumDTO) throws DataAccessException {

        albumDAO.delete(DTOMapper.toEntity(albumDTO));

    }

    @Override
    public AlbumDTO getById(Long id) throws DataAccessException {

        return DTOMapper.toDTO(albumDAO.getById(id));

    }

    @Override
    public AlbumDTO getByTitle(String title) throws DataAccessException {

        return DTOMapper.toDTO(albumDAO.getByTitle(title));

    }

    @Override
    public List<AlbumDTO> getAllAlbums() throws DataAccessException {

        return DTOMapper.albumListToDTO(albumDAO.getAll());

    }
    
    @Override
    public List<AlbumDTO> getAlbumsByTitleSub(String phrase){
        List<AlbumDTO> tmpAlbums = getAllAlbums();
        List<AlbumDTO> resAlbums = new ArrayList<>();
        for (AlbumDTO album : tmpAlbums) {
            if(Util.removeDiacritics(album.getTitle().toLowerCase())
               .contains(Util.removeDiacritics(phrase).toLowerCase())){
                resAlbums.add(album);
            }
        }
        return resAlbums;
    }
}