package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import java.util.List;

/**
 *
 * @author Medo
 */
public interface GenreDAO {

    void create(Genre entity);

    void update(Genre entity);

    void delete(Genre entity);

    Genre getById(Long id);

    List<Genre> getAll();

    Genre getByName(String name);
}
