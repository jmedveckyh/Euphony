/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services;

import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import java.util.List;

/**
 *
 * @author Branislav Novotny
 */
public interface AlbumService {
    
    void create(AlbumDTO albumDTO);
    
    void update(AlbumDTO albumDTO);
    
    void delete(AlbumDTO albumDTO);
    
    AlbumDTO getById(Long id);
    
    AlbumDTO getByTitle(String title);
        
    List<AlbumDTO> getByGenre(Genre genre);
    
    List<AlbumDTO> getByArtist(Artist artist);
    
    List<AlbumDTO> getAllAlbums();
    
    List<AlbumDTO> getByReleaseYear(Integer year);    
}