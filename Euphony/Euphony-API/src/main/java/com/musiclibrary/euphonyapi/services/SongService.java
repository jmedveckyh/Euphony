package com.musiclibrary.euphonyapi.services;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
//import com.musiclibrary.euphonybusinesslogicimplementation.dao.SongDAO;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Sebastian Lazon
 */
public interface SongService {

    void create(SongDTO song) throws DataAccessException;

    void update(SongDTO song) throws DataAccessException;

    void delete(SongDTO song) throws DataAccessException;

    SongDTO getById(Long id) throws DataAccessException;

    List<SongDTO> getAll() throws DataAccessException;

    List<SongDTO> getByTitle(String title) throws DataAccessException;

    List<SongDTO> getByGenre(GenreDTO genre) throws DataAccessException;

    List<SongDTO> getByArtist(ArtistDTO artist) throws DataAccessException;

    List<SongDTO> getByAlbum(AlbumDTO album) throws DataAccessException;

//    void setDAO(SongDAO dao);
    
}
