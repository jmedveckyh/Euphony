package com.musiclibrary.euphonyapi.services;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
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

    List<AlbumDTO> getAllAlbums();

    List<AlbumDTO> getAlbumsByTitleSub(String phrase);
}