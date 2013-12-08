package com.musiclibrary.euphonyapi.services;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Branislav Novotny #396152
 */
public interface AlbumService {
    
    void create(AlbumDTO albumDTO) throws DataAccessException;
    
    void update(AlbumDTO albumDTO) throws DataAccessException;
    
    void delete(AlbumDTO albumDTO) throws DataAccessException;
    
    AlbumDTO getById(Long id) throws DataAccessException;
    
    AlbumDTO getByTitle(String title) throws DataAccessException;
        
    List<AlbumDTO> getByGenre(GenreDTO genre)throws DataAccessException;
    
    List<AlbumDTO> getByArtist(ArtistDTO artist) throws DataAccessException;
    
    List<AlbumDTO> getAllAlbums() throws DataAccessException;
    
    List<AlbumDTO> getByReleaseYear(Integer year) throws DataAccessException;    

    List<SongDTO> getSongsByAlbum(AlbumDTO album) throws DataAccessException;
    
}