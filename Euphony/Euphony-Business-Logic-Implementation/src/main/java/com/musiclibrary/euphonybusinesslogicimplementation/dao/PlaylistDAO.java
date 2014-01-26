package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import java.util.List;

/**
 * Interface for Playlist DAO layer.
 *
 * @author Tomas Smetanka
 */
public interface PlaylistDAO {

    void create(Playlist entity);

    void update(Playlist entity);

    void delete(Playlist entity);

    Playlist getById(Long id);

    Playlist getByName(String name);

    List<Playlist> getBySong(Song song);

    List<Playlist> getAll();
}
