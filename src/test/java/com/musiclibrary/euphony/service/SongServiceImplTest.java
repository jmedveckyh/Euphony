/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.service;

import com.musiclibrary.euphony.dao.SongDAO;
import com.musiclibrary.euphony.dao.impl.SongDAOImpl;
import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.entities.Song;
import com.musiclibrary.euphony.services.SongService;
import com.musiclibrary.euphony.services.impl.SongServiceImpl;
import com.musiclibrary.euphony.util.DTOMapper;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.junit.Test;
import static org.mockito.Mockito.*;

/**
 *
 * @author Sebastian
 */
public class SongServiceImplTest extends TestCase{
    
    private SongService service;
    private SongDAO dao;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        service = new SongServiceImpl();
        dao = mock(SongDAOImpl.class);
        ((SongServiceImpl) service).setDAO(dao);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testCreate(){
        doThrow(new IllegalArgumentException()).when(dao).create(null);
        try {
            service.create(null);
            fail();
        } catch (IllegalArgumentException ex) {
            //OK
        }
        verify(dao).create(null);
        verify(dao, never()).update(null);

        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),songs);
        SongDTO songDto = new SongDTO("ttt",144,1,"mmmedo",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
        doNothing().when(dao).create(DTOMapper.toEntity(songDto));

        service.create(songDto);

        verify(dao).create(DTOMapper.toEntity(songDto));
        verify(dao, never()).delete(DTOMapper.toEntity(songDto));
    }
    
    @Test
    public void testUpdate() {
        doThrow(new IllegalArgumentException()).when(dao).update(null);
       
        try{
            service.update(null);
            fail();
        }catch(IllegalArgumentException ex){
            //OK
        }
         
        verify(dao,never()).create(null);
        verify(dao,times(1)).update(null);
       
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),songs);
        SongDTO songDto = new SongDTO("ttt",144,1,"mmmedo",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
        doNothing().when(dao).create(DTOMapper.toEntity(songDto));

        service.update(songDto);

        verify(dao,times(1)).update(DTOMapper.toEntity(songDto));
        verify(dao,times(0)).create(DTOMapper.toEntity(songDto));
    }
    
    @Test
    public void testRemove() {
        doThrow(new IllegalArgumentException()).when(dao).delete(null);
       
        try{
            service.delete(null);
            fail();
        }catch(IllegalArgumentException ex){
            //OK
        }
         
        verify(dao,never()).create(null);
        verify(dao,times(1)).delete(null);
        verify(dao,never()).update(null);
       
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),songs);
        SongDTO songDto = new SongDTO("ttt",144,1,"mmmedo",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
       
        service.delete(songDto);
       
        verify(dao,times(1)).delete(DTOMapper.toEntity(songDto));
        verify(dao,times(0)).create(DTOMapper.toEntity(songDto));
        verify(dao,never()).update(DTOMapper.toEntity(songDto));
    }
    
    @Test
    public void testGetById() {
        doThrow(new IllegalArgumentException()).when(dao).getById(null);
        doThrow(new IllegalArgumentException()).when(dao).getById(-1l);
       
        try{
            service.getById(null);
            fail();
        }catch(IllegalArgumentException ex){
            //OK
        }
       
        try{
            service.getById(-1l);
            fail();
        }catch(IllegalArgumentException ex){
            //OK
        }
         
        verify(dao,never()).create(null);
        verify(dao,times(1)).getById(null);
        verify(dao,never()).update(null);
        verify(dao,times(1)).getById(-1l);
       
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),songs);
        SongDTO songDto = new SongDTO("ttt",144,1,"mmmedo",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
        songDto.setId(1l);
       
        when(dao.getById(1l)).thenReturn(DTOMapper.toEntity(songDto));
        assertEquals(DTOMapper.toEntity(songDto), DTOMapper.toEntity(service.getById(songDto.getId())));
       
        verify(dao,times(1)).getById(1l);
        verify(dao,times(0)).create(DTOMapper.toEntity(songDto));
        verify(dao,never()).update(DTOMapper.toEntity(songDto));
    }
    
    @Test
    public void testGetAll() {
        when(dao.getAll()).thenReturn(new ArrayList<Song>());
        ArrayList<SongDTO> list = new ArrayList<>();
        assertEquals(new ArrayList<Song>(), dao.getAll());
       
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),songs);
        SongDTO songDto1 = new SongDTO("ttt1",144,1,"mmmedo1",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
        SongDTO songDto2 = new SongDTO("ttt2",144,2,"mmmedo2",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
        SongDTO songDto3 = new SongDTO("ttt3",144,3,"mmmedo3",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
       
        songDto1.setId(1l);
        songDto2.setId(2l);
        songDto3.setId(3l);
       
        list.add(songDto1);
        list.add(songDto2);
        list.add(songDto3);
       
        List<Song> daoList = new ArrayList<>();
        daoList.add(DTOMapper.toEntity(songDto1));
        daoList.add(DTOMapper.toEntity(songDto2));
        daoList.add(DTOMapper.toEntity(songDto3));
        when(dao.getAll()).thenReturn(daoList);
       
        assertEquals(DTOMapper.songsListToEntity(list), DTOMapper.songsListToEntity(service.getAll()));
       
        verify(dao,times(2)).getAll();
    }
    
    @Test
    public void testGetByTitle() {
        when(dao.getByTitle(null)).thenThrow(new IllegalArgumentException());
        try {
            service.getByTitle(null);
            fail();
        } catch(IllegalArgumentException e){
           //OK
        }
       
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),songs);
        SongDTO songDto1 = new SongDTO("ttt1",144,1,"mmmedo1",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
        SongDTO songDto2 = new SongDTO("ttt2",144,2,"mmmedo2",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
        SongDTO songDto3 = new SongDTO("ttt3",144,3,"mmmedo3",DTOMapper.toEntity(genreDto),DTOMapper.toEntity(albumDto));
       
        songDto1.setId(1l);
        songDto2.setId(2l);
        songDto3.setId(3l);  
       
        when(dao.getByTitle("Name5")).thenReturn(new ArrayList<Song>());
        ArrayList<Song> list = new ArrayList<>();
        ArrayList<SongDTO> dtoList = new ArrayList<>();
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByTitle("Name5")));
       
        list.add(DTOMapper.toEntity(songDto1));
        list.add(DTOMapper.toEntity(songDto2));
        list.add(DTOMapper.toEntity(songDto3));
       
        dtoList.add(songDto1);
        dtoList.add(songDto2);
        dtoList.add(songDto3);
       
        when(dao.getByTitle("Name2")).thenReturn(list);
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByTitle("Name2")));
       
        verify(dao,times(1)).getByTitle(null);
        verify(dao,times(1)).getByTitle("Name5");
        verify(dao,times(1)).getByTitle("Name2");
    }

    @Test
    public void testGetByGenre() {
        when(dao.getByGenre(null)).thenThrow(new IllegalArgumentException());
        try {
            service.getByGenre(null);
            fail();
        } catch(IllegalArgumentException e){
           //OK
        }
       
        GenreDTO genreDto1 = new GenreDTO();
        genreDto1.setName("mental");
        GenreDTO genreDto2 = new GenreDTO();
        genreDto2.setName("HOP-HOP");
        GenreDTO genreDto3 = new GenreDTO();
        genreDto3.setName("shitstep");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),songs);
        SongDTO songDto1 = new SongDTO("ttt1",144,1,"mmmedo1",DTOMapper.toEntity(genreDto1),DTOMapper.toEntity(albumDto));
        SongDTO songDto2 = new SongDTO("ttt2",144,2,"mmmedo2",DTOMapper.toEntity(genreDto2),DTOMapper.toEntity(albumDto));
        SongDTO songDto3 = new SongDTO("ttt3",144,3,"mmmedo3",DTOMapper.toEntity(genreDto3),DTOMapper.toEntity(albumDto));
        SongDTO songDto4 = new SongDTO("ttt4",144,3,"mmmedo4",DTOMapper.toEntity(genreDto2),DTOMapper.toEntity(albumDto));
       
        songDto1.setId(1l);
        songDto2.setId(2l);
        songDto3.setId(3l);
        songDto3.setId(4l);
       
        when(dao.getByGenre(DTOMapper.toEntity(genreDto2))).thenReturn(new ArrayList<Song>());
        ArrayList<Song> list = new ArrayList<>();
        ArrayList<SongDTO> dtoList = new ArrayList<>();
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByGenre(genreDto2)));
       
        list.add(DTOMapper.toEntity(songDto1));
        list.add(DTOMapper.toEntity(songDto2));
        list.add(DTOMapper.toEntity(songDto3));
       
        dtoList.add(songDto1);
        dtoList.add(songDto2);
        dtoList.add(songDto3);
       
        when(dao.getByTitle("Name2")).thenReturn(list);
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByTitle("Name2")));
       
        verify(dao,times(1)).getByTitle(null);
        verify(dao,times(1)).getByTitle("Name5");
        verify(dao,times(1)).getByTitle("Name2");
    }







    
}
