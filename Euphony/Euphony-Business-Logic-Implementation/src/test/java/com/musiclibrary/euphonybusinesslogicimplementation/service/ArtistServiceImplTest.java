package com.musiclibrary.euphonybusinesslogicimplementation.service;

import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.ArtistDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.ArtistDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.services.impl.ArtistServiceImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import java.util.ArrayList;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Medo
 */
public class ArtistServiceImplTest {

    private ArtistService artistService;
    private ArtistDAO artistDAO;

    @Before
    public void setUp() {
        artistService = new ArtistServiceImpl();
        artistDAO = mock(ArtistDAOImpl.class);
        ((ArtistServiceImpl) artistService).setDAO(artistDAO);
    }

    //treba??
    @Test(expected = DataAccessException.class)
    public void testCreateArtistWithNull() {
        doThrow(new DataAccessException("null album") {
        }).when(artistDAO).create(null);

        artistService.create(null);              //artist is null
        verify(artistDAO, times(1)).create(null);
        verifyNoMoreInteractions(artistDAO);
    }

    @Test
    public void testCreateArtist() {

        ArtistDTO stubArtist = new ArtistDTO("Nicki Minaj");     //artist has not-empty name and null id
        doNothing().when(artistDAO).create(DTOMapper.toEntity(stubArtist));
        artistService.create(stubArtist);

        verify(artistDAO, times(1)).create(DTOMapper.toEntity(stubArtist));
        verifyNoMoreInteractions(artistDAO);
    }

    @Test
    public void testGetArtistById() {

        ArtistDTO expResult = new ArtistDTO("Robo Kazik");
        expResult.setId(1l);

        when(artistDAO.getById(1l)).thenReturn(DTOMapper.toEntity(expResult));
        ArtistDTO result = artistService.getById(expResult.getId());              //correct
        verify(artistDAO, times(1)).getById(expResult.getId());

        assertDeepEquals(expResult, result);

        verifyNoMoreInteractions(artistDAO);
    }

    @Test
    public void testGetArtistByName() {

        ArtistDTO expResult = new ArtistDTO("Nicki Minaj");
        expResult.setId(1l);

        when(artistDAO.getByName("Nicki Minaj")).thenReturn(DTOMapper.toEntity(expResult));
        ArtistDTO result = artistService.getByName(expResult.getName());              //correct
        verify(artistDAO, times(1)).getByName(expResult.getName());

        assertDeepEquals(expResult, result);

        verifyNoMoreInteractions(artistDAO);
    }

    @Test
    public void testGetAll() {

        when(artistDAO.getAll()).thenReturn(new ArrayList<Artist>());
        ArrayList<ArtistDTO> list = new ArrayList<>();
        assertEquals(list, artistService.getAll());

        ArtistDTO expResult1 = new ArtistDTO("Nicki Minaj");
        ArtistDTO expResult2 = new ArtistDTO("Robo Kazik");
        ArtistDTO expResult3 = new ArtistDTO("OneRepublic");

        expResult1.setId(1l);
        expResult2.setId(2l);
        expResult3.setId(3l);

        list.add(expResult1);
        list.add(expResult2);
        list.add(expResult3);

        ArrayList<Artist> daoList = new ArrayList<>();
        daoList.add(DTOMapper.toEntity(expResult1));
        daoList.add(DTOMapper.toEntity(expResult2));
        daoList.add(DTOMapper.toEntity(expResult3));
        when(artistDAO.getAll()).thenReturn(daoList);

        assertEquals(DTOMapper.artistsListToEntity(list), DTOMapper.artistsListToEntity(artistService.getAll()));
        verify(artistDAO, times(2)).getAll();

        verifyNoMoreInteractions(artistDAO);
    }

    @Test
    public void testUpdateArtist() {

        ArtistDTO artist = new ArtistDTO("Nicki Minaj");
        ArtistDTO updatedArtist = new ArtistDTO("Nickita Minaz");
        artist.setId(1l);
        updatedArtist.setId(artist.getId());

        doNothing().when(artistDAO).update(DTOMapper.toEntity(artist));
        artistService.update(updatedArtist);              //correct
        verify(artistDAO, times(1)).update(DTOMapper.toEntity(updatedArtist));

        when(artistDAO.getById(1l)).thenReturn(DTOMapper.toEntity(updatedArtist));

        assertDeepEquals(updatedArtist, artistService.getById(updatedArtist.getId()));
        verify(artistDAO, times(1)).getById(updatedArtist.getId());

        verifyNoMoreInteractions(artistDAO);
    }

    @Test
    public void testDeleteArtist() {

        ArtistDTO artist = new ArtistDTO("Sandu Ciorba");
        artist.setId(1l);

        doNothing().when(artistDAO).delete(DTOMapper.toEntity(artist));
        artistService.delete(artist);
        verify(artistDAO, times(1)).delete(DTOMapper.toEntity(artist));

        artist.setId(null);

        when(artistDAO.getById(null)).thenReturn(null);
        ArtistDTO result = artistService.getById(artist.getId());
        verify(artistDAO, times(1)).getById(artist.getId());

        assertNull(artist.getId());

        verifyNoMoreInteractions(artistDAO);
    }

    public void testGetArtistByNameSub() {
        ArrayList<Artist> allList = new ArrayList<>();

        ArtistDTO expResult1 = new ArtistDTO("Armin Van Buuren");
        ArtistDTO expResult2 = new ArtistDTO("Paul van Dyk");
        ArtistDTO expResult3 = new ArtistDTO("Sean Paul");

        expResult1.setId(1l);
        expResult2.setId(2l);
        expResult3.setId(3l);

        allList.add(DTOMapper.toEntity(expResult1));
        allList.add(DTOMapper.toEntity(expResult2));
        allList.add(DTOMapper.toEntity(expResult3));

        ArrayList<Artist> paulList = new ArrayList<>();
        paulList.add(DTOMapper.toEntity(expResult2));
        paulList.add(DTOMapper.toEntity(expResult3));

        ArrayList<Artist> vanList = new ArrayList<>();
        vanList.add(DTOMapper.toEntity(expResult1));
        vanList.add(DTOMapper.toEntity(expResult2));

        when(artistDAO.getAll()).thenReturn(allList);

        assertEquals(paulList, DTOMapper.artistsListToEntity(artistService.getArtistsByNameSub("pAUL")));
        assertEquals(vanList, DTOMapper.artistsListToEntity(artistService.getArtistsByNameSub("van")));
    }

    private void assertDeepEquals(ArtistDTO expected, ArtistDTO actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
    }
}
