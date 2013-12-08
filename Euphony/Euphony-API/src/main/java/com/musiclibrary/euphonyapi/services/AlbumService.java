package com.musiclibrary.euphonyapi.services;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import java.util.List;

/**
 *
 * @author Branislav Novotny #396152
 */
public interface AlbumService {
    
    void create(AlbumDTO albumDTO);
    
    void update(AlbumDTO albumDTO);
    
    void delete(AlbumDTO albumDTO);
    
    AlbumDTO getById(Long id);
    
    AlbumDTO getByTitle(String title);
        
    List<AlbumDTO> getByGenre(GenreDTO genre);
    
    List<AlbumDTO> getByArtist(ArtistDTO artist);
    
    List<AlbumDTO> getAllAlbums();
    
    List<AlbumDTO> getByReleaseYear(Integer year);    

    List<SongDTO> getSongsByAlbum(AlbumDTO album);
    
}