package com.musiclibrary.euphonybusinesslogicimplementation.service;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.SongService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.SongDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.SongDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import com.musiclibrary.euphonybusinesslogicimplementation.services.impl.SongServiceImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Sebastian Lazon 395990
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
        doThrow(new DataAccessException("null album") {}).when(dao).create(null);
        try {
            service.create(null);
            fail();
        } catch (DataAccessException ex) {
            //OK
        }
        verify(dao).create(null);
        verify(dao, never()).update(null);

        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<SongDTO> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),songs,"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        ArtistDTO artistDto = new ArtistDTO();
        artistDto.setName("Robo Kazik");
        SongDTO songDto = new SongDTO("ttt",144,1,"mmmedo",genreDto,albumDto,artistDto);
        doNothing().when(dao).create(DTOMapper.toEntity(songDto));

        service.create(songDto);

        verify(dao).create(DTOMapper.toEntity(songDto));
        verify(dao, never()).delete(DTOMapper.toEntity(songDto));
    }
    
    @Test
    public void testUpdate() {
        doThrow(new DataAccessException("null album") {}).when(dao).update(null);
       
        try{
            service.update(null);
            fail();
        }catch(DataAccessException ex){
            //OK
        }
         
        verify(dao,never()).create(null);
        verify(dao,times(1)).update(null);
       
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),new ArrayList<SongDTO>(),"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        ArtistDTO artistDto = new ArtistDTO();
        artistDto.setName("Robo Kazik");
        SongDTO songDto = new SongDTO("ttt",144,1,"mmmedo",genreDto,albumDto,artistDto);
        doNothing().when(dao).create(DTOMapper.toEntity(songDto));

        service.update(songDto);

        verify(dao,times(1)).update(DTOMapper.toEntity(songDto));
        verify(dao,times(0)).create(DTOMapper.toEntity(songDto));
    }
    
    @Test
    public void testRemove() {
        doThrow(new DataAccessException("null album") {}).when(dao).delete(null);
       
        try{
            service.delete(null);
            fail();
        }catch(DataAccessException ex){
            //OK
        }
         
        verify(dao,never()).create(null);
        verify(dao,times(1)).delete(null);
        verify(dao,never()).update(null);
       
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),new ArrayList<SongDTO>(),"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        ArtistDTO artistDto = new ArtistDTO();
        artistDto.setName("Robo Kazik");
        SongDTO songDto = new SongDTO("ttt",144,1,"mmmedo",genreDto,albumDto,artistDto);
       
        service.delete(songDto);
       
        verify(dao,times(1)).delete(DTOMapper.toEntity(songDto));
        verify(dao,times(0)).create(DTOMapper.toEntity(songDto));
        verify(dao,never()).update(DTOMapper.toEntity(songDto));
    }
    
    @Test
    public void testGetById() {
        doThrow(new DataAccessException("null album") {}).when(dao).getById(null);
        doThrow(new DataAccessException("null album") {}).when(dao).getById(-1l);
       
        try{
            service.getById(null);
            fail();
        }catch(DataAccessException ex){
            //OK
        }
       
        try{
            service.getById(-1l);
            fail();
        }catch(DataAccessException ex){
            //OK
        }
         
        verify(dao,never()).create(null);
        verify(dao,times(1)).getById(null);
        verify(dao,never()).update(null);
        verify(dao,times(1)).getById(-1l);
       
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),new ArrayList<SongDTO>(),"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        ArtistDTO artistDto = new ArtistDTO();
        artistDto.setName("Robo Kazik");
        SongDTO songDto = new SongDTO("ttt",144,1,"mmmedo",genreDto,albumDto,artistDto);
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
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),new ArrayList<SongDTO>(),"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        ArtistDTO artistDto = new ArtistDTO();
        artistDto.setName("Robo Kazik");
        
        SongDTO songDto1 = new SongDTO("ttt1",144,1,"mmmedo1",genreDto,albumDto,artistDto);
        SongDTO songDto2 = new SongDTO("ttt2",144,2,"mmmedo2",genreDto,albumDto,artistDto);
        SongDTO songDto3 = new SongDTO("ttt3",144,3,"mmmedo3",genreDto,albumDto,artistDto);
        
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
        when(dao.getByTitle(null)).thenThrow(new DataAccessException("null album") {});
        try {
            service.getByTitle(null);
            fail();
        } catch(DataAccessException e){
           //OK
        }
       
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),new ArrayList<SongDTO>(),"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        ArtistDTO artistDto = new ArtistDTO();
        artistDto.setName("Robo Kazik");
        SongDTO songDto1 = new SongDTO("ttt1",144,1,"mmmedo1",genreDto,albumDto,artistDto);
        SongDTO songDto2 = new SongDTO("ttt2",144,2,"mmmedo2",genreDto,albumDto,artistDto);
        SongDTO songDto3 = new SongDTO("ttt3",144,3,"mmmedo3",genreDto,albumDto,artistDto);
       
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
        when(dao.getByGenre(null)).thenThrow(new DataAccessException("null album") {});
        try {
            service.getByGenre(null);
            fail();
        } catch(DataAccessException e){
           //OK
        }
        
        verify(dao,never()).create(null);
        verify(dao,times(0)).getById(null);
        verify(dao,never()).update(null);
        verify(dao,times(1)).getByGenre(null);
 
        GenreDTO genreDto1 = new GenreDTO();
        genreDto1.setName("mental");
        GenreDTO genreDto2 = new GenreDTO();
        genreDto2.setName("HOP-HOP");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),new ArrayList<SongDTO>(),"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        ArtistDTO artistDto = new ArtistDTO();
        artistDto.setName("Robo Kazik");
        SongDTO songDto1 = new SongDTO("ttt1",144,1,"mmmedo1",genreDto1,albumDto,artistDto);
        SongDTO songDto2 = new SongDTO("ttt2",144,2,"mmmedo2",genreDto2,albumDto,artistDto);
        SongDTO songDto3 = new SongDTO("ttt3",144,3,"mmmedo3",genreDto1,albumDto,artistDto);
       
        songDto1.setId(1l);
        songDto2.setId(2l);
        songDto3.setId(3l);
       
        when(dao.getByGenre(DTOMapper.toEntity(genreDto1))).thenReturn(new ArrayList<Song>());
        ArrayList<Song> list = new ArrayList<>();
        ArrayList<SongDTO> dtoList = new ArrayList<>();
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByGenre(genreDto1)));
       
        list.add(DTOMapper.toEntity(songDto1));
        list.add(DTOMapper.toEntity(songDto2));
        list.add(DTOMapper.toEntity(songDto3));
       
        dtoList.add(songDto1);
        dtoList.add(songDto2);
        dtoList.add(songDto3);
       
        when(dao.getByGenre(DTOMapper.toEntity(genreDto2))).thenReturn(list);
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByGenre(genreDto2)));
       
        /*
        verify(dao,times(1)).getByGenre(DTOMapper.toEntity(genreDto1));
        verify(dao,times(1)).getByGenre(DTOMapper.toEntity(genreDto2));
        */
    }
    
    public void testGetByArtist(){
        when(dao.getByArtist(null)).thenThrow(new DataAccessException("null album") {});
        try {
            service.getByArtist(null);
            fail();
        } catch(DataAccessException e){
           //OK
        }
        
        verify(dao,never()).create(null);
        verify(dao,times(0)).getById(null);
        verify(dao,never()).update(null);
        verify(dao,times(1)).getByArtist(null);
 
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto1 = new AlbumDTO("fenomeno","aaa",new DateTime(2011, 11, 11, 0, 0),new ArrayList<SongDTO>(),"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        
        ArtistDTO artistDto1 = new ArtistDTO();
        artistDto1.setName("Robo Kazik");
        ArtistDTO artistDto2 = new ArtistDTO();
        artistDto2.setName("Rytmaus");
        
        SongDTO songDto1 = new SongDTO("ttt1",144,1,"mmmedo1",genreDto,albumDto1,artistDto1);
        SongDTO songDto2 = new SongDTO("ttt2",144,2,"mmmedo2",genreDto,albumDto1,artistDto2);
        SongDTO songDto3 = new SongDTO("ttt3",144,3,"mmmedo3",genreDto,albumDto1,artistDto2);
       
        songDto1.setId(1l);
        songDto2.setId(2l);
        songDto3.setId(3l);
       
        when(dao.getByArtist(DTOMapper.toEntity(artistDto1))).thenReturn(new ArrayList<Song>());
        ArrayList<Song> list = new ArrayList<>();
        ArrayList<SongDTO> dtoList = new ArrayList<>();
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByArtist(artistDto1)));
       
        list.add(DTOMapper.toEntity(songDto1));
        list.add(DTOMapper.toEntity(songDto2));
        list.add(DTOMapper.toEntity(songDto3));
       
        dtoList.add(songDto1);
        dtoList.add(songDto2);
        dtoList.add(songDto3);
       
        when(dao.getByArtist(DTOMapper.toEntity(artistDto2))).thenReturn(list);
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByArtist(artistDto2)));
       
        /*
        verify(dao,times(1)).getByGenre(DTOMapper.toEntity(genreDto1));
        verify(dao,times(1)).getByGenre(DTOMapper.toEntity(genreDto2));
        */
    }
    
    public void testGetByAlbum(){
        when(dao.getByAlbum(null)).thenThrow(new DataAccessException("null album") {});
        try {
            service.getByAlbum(null);
            fail();
        } catch(DataAccessException e){
           //OK
        }
        
        verify(dao,never()).create(null);
        verify(dao,times(0)).getById(null);
        verify(dao,never()).update(null);
        verify(dao,times(1)).getByAlbum(null);
 
        GenreDTO genreDto = new GenreDTO();
        genreDto.setName("metal");
        List<Song> songs = new ArrayList();
        AlbumDTO albumDto1 = new AlbumDTO("fenomeno1","aaa",new DateTime(2011, 11, 11, 0, 0),new ArrayList<SongDTO>(),"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        AlbumDTO albumDto2 = new AlbumDTO("fenomeno2","aaa",new DateTime(2011, 11, 11, 0, 0),new ArrayList<SongDTO>(),"comment",new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>());
        ArtistDTO artistDto = new ArtistDTO();
        artistDto.setName("Robo Kazik");
        SongDTO songDto1 = new SongDTO("ttt1",144,1,"mmmedo1",genreDto,albumDto1,artistDto);
        SongDTO songDto2 = new SongDTO("ttt2",144,2,"mmmedo2",genreDto,albumDto1,artistDto);
        SongDTO songDto3 = new SongDTO("ttt3",144,3,"mmmedo3",genreDto,albumDto2,artistDto);
       
        songDto1.setId(1l);
        songDto2.setId(2l);
        songDto3.setId(3l);
       
        when(dao.getByAlbum(DTOMapper.toEntity(albumDto1))).thenReturn(new ArrayList<Song>());
        ArrayList<Song> list = new ArrayList<>();
        ArrayList<SongDTO> dtoList = new ArrayList<>();
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByAlbum(albumDto1)));
       
        list.add(DTOMapper.toEntity(songDto1));
        list.add(DTOMapper.toEntity(songDto2));
        list.add(DTOMapper.toEntity(songDto3));
       
        dtoList.add(songDto1);
        dtoList.add(songDto2);
        dtoList.add(songDto3);
       
        when(dao.getByAlbum(DTOMapper.toEntity(albumDto2))).thenReturn(list);
        assertEquals(DTOMapper.songsListToEntity(dtoList), DTOMapper.songsListToEntity(service.getByAlbum(albumDto2)));
       
        /*
        verify(dao,times(1)).getByGenre(DTOMapper.toEntity(genreDto1));
        verify(dao,times(1)).getByGenre(DTOMapper.toEntity(genreDto2));
        */
    }
    
    public void testGetArtistByNameSub(){
        ArrayList<Song> allList = new ArrayList<>();
        
        SongDTO expResult1 = new SongDTO("Armin Van Buuren");
        SongDTO expResult2 = new SongDTO("Paul van Dyk");
        SongDTO expResult3 = new SongDTO("Sean Paul");
        
        expResult1.setId(1l);
        expResult2.setId(2l);
        expResult3.setId(3l);
       
        allList.add(DTOMapper.toEntity(expResult1));
        allList.add(DTOMapper.toEntity(expResult2));
        allList.add(DTOMapper.toEntity(expResult3));
        
        ArrayList<Song> paulList = new ArrayList<>();
        paulList.add(DTOMapper.toEntity(expResult2));
        paulList.add(DTOMapper.toEntity(expResult3));

        ArrayList<Song> vanList = new ArrayList<>();
        vanList.add(DTOMapper.toEntity(expResult1));
        vanList.add(DTOMapper.toEntity(expResult2));
        
        when(dao.getAll()).thenReturn(allList);
        
        assertEquals(paulList, DTOMapper.songsListToEntity(service.getSongsByTitleSub("pAUL")));
        assertEquals(vanList, DTOMapper.songsListToEntity(service.getSongsByTitleSub("van")));
    }
    
}
