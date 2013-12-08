package com.musiclibrary.euphonyapi.services;

import com.musiclibrary.euphonyapi.dto.GenreDTO;
//import com.musiclibrary.euphonybusinesslogicimplementation.dao.GenreDAO;
import java.util.List;

/**
 *
 * @author Medo
 */
public interface GenreService {

    void create(GenreDTO genreDTO);

    void update(GenreDTO genreDTO);

    void delete(GenreDTO genreDTO);

    GenreDTO getById(Long id);

    List<GenreDTO> getAll();

    GenreDTO getByName(String name);
}
