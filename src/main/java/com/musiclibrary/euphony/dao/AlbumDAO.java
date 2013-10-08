/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.entities.Album;

/**
 *
 * @author Branislav Novotny
 */
public interface AlbumDAO {
    
    void createAlbum(Album album);
    
    void updateAlbum(Album album);
    
    void deleteAlbum(Album album);
    
    Album getAlbumById(Long id);
    
    //dalsie getByTitle...
}
