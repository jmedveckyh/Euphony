package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import java.util.List;

/**
 *
 * @author Branislav Novotny <br.novotny@gmail.com> #396152
 */
public interface AlbumDAO {

    void create(Album entity);

    void update(Album entity);

    void delete(Album entity);

    Album getById(Long id);

    List<Album> getAll();

    Album getByTitle(String title);
}
