package com.musiclibrary.euphonybusinesslogicimplementation.services.impl;

import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.ArtistDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
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
 * @author Medo
 */
@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistDAO artistDAO;

    public void setDAO(ArtistDAO artistDAO) {
        this.artistDAO = artistDAO;
    }

    @Override
    public void create(ArtistDTO artist) throws DataAccessException {
        Artist artistEntity = DTOMapper.toEntity(artist);

        artistDAO.create(artistEntity);

        artist.setId(artistEntity.getId());
    }

    @Override
    public void update(ArtistDTO artist) throws DataAccessException {
        Artist artistEntity = DTOMapper.toEntity(artist);

        artistDAO.update(artistEntity);

        artist = DTOMapper.toDTO(artistEntity);
    }

    @Override
    public void delete(ArtistDTO artist) throws DataAccessException {

        artistDAO.delete(DTOMapper.toEntity(artist));

    }

    @Override
    public ArtistDTO getById(Long id) throws DataAccessException {

        return DTOMapper.toDTO(artistDAO.getById(id));

    }

    @Override
    public List<ArtistDTO> getAll() throws DataAccessException {

        return DTOMapper.artistsListToDTO(artistDAO.getAll());

    }

    @Override
    public ArtistDTO getByName(String name) throws DataAccessException {

        return DTOMapper.toDTO(artistDAO.getByName(name));

    }
    
    public List<SongDTO> getSongsByArtist(ArtistDTO artist) throws DataAccessException {
        
        return DTOMapper.songsListToDTO(artistDAO.getSongsByArtist(DTOMapper.toEntity(artist)));
        
    }
    
    @Override
    public List<ArtistDTO> getArtistsByNameSub(String phrase){
        List<ArtistDTO> tmpArtists = getAll();
        List<ArtistDTO> resArtists = new ArrayList<>();
        for (ArtistDTO album : tmpArtists) {
            if(Util.removeDiacritics(album.getName().toLowerCase())
               .contains(Util.removeDiacritics(phrase).toLowerCase())){
                resArtists.add(album);
            }
        }
        return resArtists;
    }
}
