package com.musiclibrary.euphonybusinesslogicimplementation.service;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.AlbumDAO;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.services.impl.AlbumServiceImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import static org.mockito.Mockito.*;
import org.springframework.dao.DataAccessException;


/**
 *
 * @author Branislav Novotny <br.novotny@gmail.com> #396152
 */
public class AlbumServiceImplTest extends TestCase {
    
    private AlbumService service;
    private AlbumDAO dao;
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        service = new AlbumServiceImpl();
        dao = mock(AlbumDAOImpl.class);
        ((AlbumServiceImpl) service).setDAO(dao);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    public void testCreateAlbum(){
        doThrow(new DataAccessException("null album") {}).when(dao).create(null);
        
        try{
            service.create(null);              
            fail();
        } catch(DataAccessException e){
            //ok
        }
        verify(dao, times(1)).create(null);
        verifyNoMoreInteractions(dao);
        
        AlbumDTO album = new AlbumDTO("Club Life", "cover.jpg", new DateTime(2012,1,1,0,0), "nehehe");
        doNothing().when(dao).create(DTOMapper.toEntity(album));
        service.create(album);

        verify(dao, times(1)).create(DTOMapper.toEntity(album));
        verifyNoMoreInteractions(dao);
    }
    
    public void testGetByTitle(){
        AlbumDTO expResult = new AlbumDTO("Club Life", "cover.jpg", new DateTime(2012,1,1,0,0),"nehehe");
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
        
        AlbumDTO expResult1 = new AlbumDTO("Club Life", "cover1.jpg", new DateTime(2012,1,1,0,0), "nehe");
        AlbumDTO expResult2 = new AlbumDTO("Club Life 2", "cover1.jpg", new DateTime(2012,1,1,0,0), "nehehe");
        AlbumDTO expResult3 = new AlbumDTO("Club Life 3", "cover1.jpg", new DateTime(2012,1,1,0,0), "ne");
        
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

        AlbumDTO album = new AlbumDTO("Club Life", "cover1.jpg", new DateTime(2012,1,1,0,0), "nehe");
        AlbumDTO upAlbum = new AlbumDTO("Club Life 2", "cover1.jpg", new DateTime(2012,1,1,0,0), "nehehe");
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

        AlbumDTO album = new AlbumDTO("Eleve11", "cover11.jpg", new DateTime(2012,1,1,0,0), "nehe");
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
        AlbumDTO expResult = new AlbumDTO("Eleve11", "cover11.jpg", new DateTime(2012,1,1,0,0), "nehe");
        expResult.setId(1l);

        when(dao.getById(1l)).thenReturn(DTOMapper.toEntity(expResult));
        AlbumDTO result = service.getById(expResult.getId());              //ok
        verify(dao, times(1)).getById(expResult.getId());
        
        assertDeepEquals(expResult, result);

        verifyNoMoreInteractions(dao);
    }
    
   public void testGetByTitleSub(){
       
        AlbumDTO expResult = new AlbumDTO("Kaleidoscope", "cover.jpg", new DateTime(2012,10,1,0,0), "nehehe");

        AlbumDTO expResult2 = new AlbumDTO("Endoscope", "cover1.jpg", new DateTime(2011,1,1,0,0), "nehehe");

        AlbumDTO expResult3 = new AlbumDTO("End of the world", "cover.jpg", new DateTime(2009,12,31,23,59), "nehehe");
        
        expResult.setId(1l);
        expResult2.setId(2l);
        expResult3.setId(3l);
        
        List<Album> allList = new ArrayList<Album>();
        allList.add(DTOMapper.toEntity(expResult));
        allList.add(DTOMapper.toEntity(expResult2));
        allList.add(DTOMapper.toEntity(expResult3));
        
        List<Album> scopeList = new ArrayList<Album>();
        scopeList.add(DTOMapper.toEntity(expResult));
        scopeList.add(DTOMapper.toEntity(expResult2));
        
        List<Album> endList = new ArrayList<Album>();
        endList.add(DTOMapper.toEntity(expResult2));
        endList.add(DTOMapper.toEntity(expResult3));
        
        when(dao.getAll()).thenReturn(allList);
        
        assertEquals(scopeList, DTOMapper.albumListToEntity(service.getAlbumsByTitleSub("scope")));
        assertEquals(endList, DTOMapper.albumListToEntity(service.getAlbumsByTitleSub("end")));
       
    }
       
    private void assertDeepEquals(AlbumDTO a1,AlbumDTO a2){
        assertEquals(a1.getId(),a2.getId());
        assertEquals(a1.getComment(),a2.getComment());
        assertEquals(a1.getCover(),a2.getCover());
        assertEquals(a1.getReleaseDate(),a2.getReleaseDate());
        assertEquals(a1.getTitle(),a2.getTitle());
    }
}
