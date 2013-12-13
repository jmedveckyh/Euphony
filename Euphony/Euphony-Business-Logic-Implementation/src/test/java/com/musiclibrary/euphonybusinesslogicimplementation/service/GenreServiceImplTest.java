package com.musiclibrary.euphonybusinesslogicimplementation.service;

import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.services.GenreService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.GenreDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.GenreDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.services.impl.GenreServiceImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import java.util.ArrayList;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Medo
 */
public class GenreServiceImplTest {

    private GenreService genreService;
    private GenreDAO genreDAO;

    @Before
    public void setUp() {
        genreService = new GenreServiceImpl();
        genreDAO = mock(GenreDAOImpl.class);
        ((GenreServiceImpl) genreService).setDAO(genreDAO);
    }

    //treba??
    @Test(expected = DataAccessException.class)
    public void testCreateGenreWithNull() {
        doThrow(new DataAccessException("null album") {
        }).when(genreDAO).create(null);

        genreService.create(null);              //genre is null
        verify(genreDAO, times(1)).create(null);
        verifyNoMoreInteractions(genreDAO);
    }

    @Test
    public void testCreateGenre() {

        GenreDTO stubGenre = new GenreDTO("Hip Hop");     //genre has not-empty name and null id
        doNothing().when(genreDAO).create(DTOMapper.toEntity(stubGenre));
        genreService.create(stubGenre);

        verify(genreDAO, times(1)).create(DTOMapper.toEntity(stubGenre));
        verifyNoMoreInteractions(genreDAO);
    }

    @Test
    public void testGetGenreById() {

        GenreDTO expResult = new GenreDTO("Heavy metal");
        expResult.setId(1l);

        when(genreDAO.getById(1l)).thenReturn(DTOMapper.toEntity(expResult));
        GenreDTO result = genreService.getById(expResult.getId());              //correct
        verify(genreDAO, times(1)).getById(expResult.getId());

        assertDeepEquals(expResult, result);

        verifyNoMoreInteractions(genreDAO);
    }

    @Test
    public void testGetGenreByName() {

        GenreDTO expResult = new GenreDTO("Heavy metal");
        expResult.setId(1l);

        when(genreDAO.getByName("Heavy metal")).thenReturn(DTOMapper.toEntity(expResult));
        GenreDTO result = genreService.getByName(expResult.getName());              //correct
        verify(genreDAO, times(1)).getByName(expResult.getName());

        assertDeepEquals(expResult, result);

        verifyNoMoreInteractions(genreDAO);
    }

    @Test
    public void testGetAll() {

        when(genreDAO.getAll()).thenReturn(new ArrayList<Genre>());
        ArrayList<GenreDTO> list = new ArrayList<>();
        assertEquals(list, genreService.getAll());

        GenreDTO expResult1 = new GenreDTO("Heavy metal");
        GenreDTO expResult2 = new GenreDTO("Doom metal");
        GenreDTO expResult3 = new GenreDTO("Death metal");

        expResult1.setId(1l);
        expResult2.setId(2l);
        expResult3.setId(3l);

        list.add(expResult1);
        list.add(expResult2);
        list.add(expResult3);

        ArrayList<Genre> daoList = new ArrayList<>();
        daoList.add(DTOMapper.toEntity(expResult1));
        daoList.add(DTOMapper.toEntity(expResult2));
        daoList.add(DTOMapper.toEntity(expResult3));
        when(genreDAO.getAll()).thenReturn(daoList);

        assertEquals(DTOMapper.genresListToEntity(list), DTOMapper.genresListToEntity(genreService.getAll()));
        verify(genreDAO, times(2)).getAll();

        verifyNoMoreInteractions(genreDAO);
    }

    @Test
    public void testUpdateGenre() {

        GenreDTO genre = new GenreDTO("Death metal");
        GenreDTO updatedGenre = new GenreDTO("Doom metal");
        genre.setId(1l);
        updatedGenre.setId(genre.getId());

        doNothing().when(genreDAO).update(DTOMapper.toEntity(genre));
        genreService.update(updatedGenre);              //correct
        verify(genreDAO, times(1)).update(DTOMapper.toEntity(updatedGenre));

        when(genreDAO.getById(1l)).thenReturn(DTOMapper.toEntity(updatedGenre));

        assertDeepEquals(updatedGenre, genreService.getById(updatedGenre.getId()));
        verify(genreDAO, times(1)).getById(updatedGenre.getId());

        verifyNoMoreInteractions(genreDAO);
    }

    @Test
    public void testDeleteGenre() {

        GenreDTO genre = new GenreDTO("Trip hop");
        genre.setId(1l);

        doNothing().when(genreDAO).delete(DTOMapper.toEntity(genre));
        genreService.delete(genre);
        verify(genreDAO, times(1)).delete(DTOMapper.toEntity(genre));

        genre.setId(null);

        when(genreDAO.getById(null)).thenReturn(null);
        GenreDTO result = genreService.getById(genre.getId());
        verify(genreDAO, times(1)).getById(genre.getId());

        assertNull(genre.getId());

        verifyNoMoreInteractions(genreDAO);
    }

    private void assertDeepEquals(GenreDTO expected, GenreDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
