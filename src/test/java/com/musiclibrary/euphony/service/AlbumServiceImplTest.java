/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.service;

import com.musiclibrary.euphony.dao.AlbumDAO;
import com.musiclibrary.euphony.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.ArtistDTO;
import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.services.AlbumService;
import com.musiclibrary.euphony.util.DTOMapper;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;


/**
 *
 * @author Branislav Novotny <br.novotny@gmail.com> #396152
 */
public class AlbumServiceImplTest extends TestCase{
    
    private AlbumService service;
    private AlbumDAO dao;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
        service = (AlbumService) ctx.getBean("albumServiceImpl");
        dao = mock(AlbumDAOImpl.class);
        service.setDAO(dao);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCreateAlbum(){
        doThrow(new IllegalArgumentException()).when(dao).create(null);

        try{
            service.create(null);              
            fail();
        } catch(DataAccessException e){
            //ok
        }
        verify(dao, times(1)).create(null);
        verifyNoMoreInteractions(dao);
        
        AlbumDTO album = new AlbumDTO("Club Life", "cover.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        doNothing().when(dao).create(DTOMapper.toEntity(album));
        service.create(album);

        verify(dao, times(1)).create(DTOMapper.toEntity(album));
        verifyNoMoreInteractions(dao);
    }
    
    public void testGetByTitle(){
        AlbumDTO expResult = new AlbumDTO("Club Life", "cover.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.setId(1l);

        when(dao.getByTitle("Club Life")).thenReturn(DTOMapper.toEntity(expResult));
        AlbumDTO result = service.getByTitle(expResult.getTitle());              //ok
        verify(dao, times(1)).getByTitle(expResult.getTitle());
        
        assertDeepEquals(expResult, result);

        verifyNoMoreInteractions(dao);
    }
    
    public void testGetAllAlbums(){
        when(dao.getAll()).thenReturn(new ArrayList<Album>());
        ArrayList<AlbumDTO> list = new ArrayList<>();
        assertEquals(list, service.getAllAlbums());
        
        AlbumDTO expResult1 = new AlbumDTO("Club Life", "cover1.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "nehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        AlbumDTO expResult2 = new AlbumDTO("Club Life 2", "cover1.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        AlbumDTO expResult3 = new AlbumDTO("Club Life 3", "cover1.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "ne",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        
        expResult1.setId(1l);
        expResult2.setId(2l);
        expResult3.setId(3l);
       
        list.add(expResult1);
        list.add(expResult2);
        list.add(expResult3);
        
        ArrayList<Album> daoList = new ArrayList<>();
        daoList.add(DTOMapper.toEntity(expResult1));
        daoList.add(DTOMapper.toEntity(expResult2));
        daoList.add(DTOMapper.toEntity(expResult3));
        when(dao.getAll()).thenReturn(daoList);
        
        assertEquals(DTOMapper.albumListToEntity(list), DTOMapper.albumListToEntity(service.getAllAlbums()));
        verify(dao,times(2)).getAll();

        verifyNoMoreInteractions(dao);
    }
    
    public void testUpdateAlbum() {

        AlbumDTO album = new AlbumDTO("Club Life", "cover1.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "nehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        AlbumDTO upAlbum = new AlbumDTO("Club Life 2", "cover1.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        album.setId(1l);
        upAlbum.setId(album.getId());

        doNothing().when(dao).update(DTOMapper.toEntity(album));
        service.update(upAlbum);              //ok
        verify(dao, times(1)).update(DTOMapper.toEntity(upAlbum));
        
        when(dao.getById(1l)).thenReturn(DTOMapper.toEntity(upAlbum));

        assertDeepEquals(upAlbum, service.getById(upAlbum.getId()));
        verify(dao, times(1)).getById(upAlbum.getId());
        
        verifyNoMoreInteractions(dao);
    }
    
    public void testDeleteArtist() {

        AlbumDTO album = new AlbumDTO("Eleve11", "cover11.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "nehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        album.setId(1l);

        doNothing().when(dao).delete(DTOMapper.toEntity(album));
        service.delete(album);
        verify(dao, times(1)).delete(DTOMapper.toEntity(album));
        
        album.setId(null);

        when(dao.getById(null)).thenReturn(null);
        AlbumDTO result = service.getById(album.getId());
        verify(dao, times(1)).getById(album.getId());

        assertNull(album.getId());

        verifyNoMoreInteractions(dao);
    }
    
    public void testGetById(){
        AlbumDTO expResult = new AlbumDTO("Eleve11", "cover11.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "nehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.setId(1l);

        when(dao.getById(1l)).thenReturn(DTOMapper.toEntity(expResult));
        AlbumDTO result = service.getById(expResult.getId());              //ok
        verify(dao, times(1)).getById(expResult.getId());
        
        assertDeepEquals(expResult, result);

        verifyNoMoreInteractions(dao);
    }
    
    public void testGetAlbumByGenre(){
        AlbumDTO expResult = new AlbumDTO("Kaleidoscope", "cover.jpg", new DateTime(2009,1,1,0,0), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.getArtists().add(new ArtistDTO("Tiesto"));
        GenreDTO tranceGen = new GenreDTO("Trance");
        GenreDTO technoGen = new GenreDTO("Techno");
        GenreDTO houseGen = new GenreDTO("House");
        expResult.getGenres().add(tranceGen);
        expResult.getGenres().add(houseGen);
        expResult.getGenres().add(technoGen);
        expResult.setId(1l);
        
        AlbumDTO expResult2 = new AlbumDTO("Mirage", "cover1.jpg", new DateTime(2010,1,1,0,0), new ArrayList<SongDTO>(), "hehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult2.getArtists().add(new ArtistDTO("Armin Van Buuren"));
        expResult2.getGenres().add(tranceGen);
        expResult2.getGenres().add(houseGen);
        expResult.setId(2l);
        List<Album> technoList = new ArrayList<Album>();
        technoList.add(DTOMapper.toEntity(expResult));
        when(dao.getByGenre(DTOMapper.toEntity(technoGen))).thenReturn(technoList);
        
        List<Album> resultList = dao.getByGenre(DTOMapper.toEntity(technoGen));
        
        assertEquals(1, resultList.size());
        assertEquals(technoList, resultList);
        
        List<Album> houseList = new ArrayList<Album>();
        houseList.add(DTOMapper.toEntity(expResult));
        houseList.add(DTOMapper.toEntity(expResult2));
        when(dao.getByGenre(DTOMapper.toEntity(houseGen))).thenReturn(houseList);
        
        List<Album> resultList2 = dao.getByGenre(DTOMapper.toEntity(houseGen));
        
        assertEquals(2, resultList2.size());
        assertEquals(houseList, resultList2);
    }
    
    public void testGetAlbumByArtist(){
        
        ArtistDTO tiesto = new ArtistDTO("Tiesto");
        ArtistDTO hardwell = new ArtistDTO("Hardwell");
        ArtistDTO dyro = new ArtistDTO("Dyro");
        AlbumDTO expResult = new AlbumDTO("Kaleidoscope", "cover.jpg", new DateTime(2009,1,1,0,0), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.getArtists().add(dyro);
        expResult.getArtists().add(tiesto);
        expResult.getArtists().add(hardwell);
        expResult.setId(1l);
        
        AlbumDTO expResult2 = new AlbumDTO("Mirage", "cover1.jpg", new DateTime(2010,1,1,0,0), new ArrayList<SongDTO>(), "hehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.getArtists().add(dyro);
        expResult.getArtists().add(tiesto);
        expResult.setId(2l);
        List<Album> hardwellsList = new ArrayList<Album>();
        hardwellsList.add(DTOMapper.toEntity(expResult));
        when(dao.getByArtist(DTOMapper.toEntity(hardwell))).thenReturn(hardwellsList);
        
        List<Album> resultList = dao.getByArtist(DTOMapper.toEntity(hardwell));
        
        assertEquals(1, resultList.size());
        assertEquals(hardwellsList, resultList);
        
        List<Album> dyrosList = new ArrayList<Album>();
        dyrosList.add(DTOMapper.toEntity(expResult));
        dyrosList.add(DTOMapper.toEntity(expResult2));
        when(dao.getByArtist(DTOMapper.toEntity(dyro))).thenReturn(dyrosList);
        
        List<Album> resultList2 = dao.getByArtist(DTOMapper.toEntity(dyro));
        //List<AlbumDTO> houseList = new ArrayList<AlbumDTO>();
        
        assertEquals(2, resultList2.size());
        assertEquals(dyrosList, resultList2);
    }
    
    public void testGetAlbumsByReleaseYear(){
        AlbumDTO expResult = new AlbumDTO("Club Life", "cover.jpg", new DateTime(2011,10,1,0,0), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.getArtists().add(new ArtistDTO("Tiesto"));

        AlbumDTO expResult2 = new AlbumDTO("Club Life Alfa", "cover1.jpg", new DateTime(2011,1,1,0,0), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.getArtists().add(new ArtistDTO("Tiesto"));

        AlbumDTO expResult3 = new AlbumDTO("Club Life Final Release", "cover.jpg", new DateTime(2011,12,31,23,59), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.getArtists().add(new ArtistDTO("Tiesto"));

        AlbumDTO expResult4 = new AlbumDTO("Club Life Final Release 2010", "cover.jpg", new DateTime(2010,12,31,23,59), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.getArtists().add(new ArtistDTO("Tiesto"));

        AlbumDTO expResult5 = new AlbumDTO("Club Life Alfa 2012", "cover.jpg", new DateTime(2012,1,1,0,0), new ArrayList<SongDTO>(), "nehehe",
                                      new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        expResult.getArtists().add(new ArtistDTO("Tiesto"));
        expResult.setId(1l);
        expResult2.setId(2l);
        expResult3.setId(3l);
        expResult4.setId(4l);
        expResult5.setId(5l);
        
        List<Album> twoOelevenList = new ArrayList<Album>();
        twoOelevenList.add(DTOMapper.toEntity(expResult));
        twoOelevenList.add(DTOMapper.toEntity(expResult2));
        twoOelevenList.add(DTOMapper.toEntity(expResult3));
        when(dao.getByReleaseYear(new Integer(2011))).thenReturn(twoOelevenList);

        List<Album> resultList = dao.getByReleaseYear(new Integer(2011));
        assertEquals(3, resultList.size());
        assertEquals(twoOelevenList, resultList);
               
        List<Album> twoOtwelveList = new ArrayList<Album>();
        twoOtwelveList.add(DTOMapper.toEntity(expResult5));
        
        when(dao.getByReleaseYear(new Integer(2012))).thenReturn(twoOtwelveList);
        
        List<Album> resultList2 = dao.getByReleaseYear(new Integer(2012));

        assertEquals(twoOtwelveList, resultList2);

    }
    
    private void assertDeepEquals(AlbumDTO a1,AlbumDTO a2){
        assertEquals(a1.getId(),a2.getId());
        assertEquals(a1.getArtists(),a2.getArtists());
        assertEquals(a1.getComment(),a2.getComment());
        assertEquals(a1.getCover(),a2.getCover());
        assertEquals(a1.getGenres(),a2.getGenres());
        assertEquals(a1.getReleaseDate(),a2.getReleaseDate());
        assertEquals(a1.getSongs(),a2.getSongs());
        assertEquals(a1.getTitle(),a2.getTitle());
    }
}
