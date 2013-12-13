package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import java.util.List;

/**
 *
 * @author Medo
 */
public interface GenreDAO {

    public void create(Genre entity);

    public void update(Genre entity);

    public void delete(Genre entity);

    public Genre getById(Long id);

    public List<Genre> getAll();

    public Genre getByName(String name);
}
