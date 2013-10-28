/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.dao;

import com.musiclibrary.euphony.entities.Artist;
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
