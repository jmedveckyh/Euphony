package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import java.util.List;

/**
 *
 * @author Medo
 */
public interface ArtistDAO {

    void create(Artist entity);

    void update(Artist entity);

    void delete(Artist entity);

    Artist getById(Long id);

    List<Artist> getAll();

    Artist getByName(String name);
}
