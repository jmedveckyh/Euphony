/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.entities.Genre;
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
