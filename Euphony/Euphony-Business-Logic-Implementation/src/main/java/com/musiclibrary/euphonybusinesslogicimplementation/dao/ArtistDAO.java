package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import java.util.List;

/**
 *
 * @author Medo
 */
public interface ArtistDAO {

    public void create(Artist entity);

    public void update(Artist entity);

    public void delete(Artist entity);

    public Artist getById(Long id);

    public List<Artist> getAll();

    public Artist getByName(String name);
}
