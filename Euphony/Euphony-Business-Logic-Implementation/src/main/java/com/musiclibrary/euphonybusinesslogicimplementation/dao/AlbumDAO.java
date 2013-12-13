package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import java.util.List;

/**
 *
 * @author Branislav Novotny <br.novotny@gmail.com> #396152
 */
public interface AlbumDAO {

    public void create(Album entity);

    public void update(Album entity);

    public void delete(Album entity);

    public Album getById(Long id);

    public List<Album> getAll();

    public Album getByTitle(String title);
}
