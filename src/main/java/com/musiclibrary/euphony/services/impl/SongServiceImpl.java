/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.services.impl;

import com.musiclibrary.euphony.dao.impl.SongDAOImpl;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Song;
import com.musiclibrary.euphony.services.SongService;
import com.musiclibrary.euphony.util.DTOMapper;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public class SongServiceImpl implements SongService{
    
    private SongDAOImpl songDao;
    
    public void setDAO(SongDAOImpl songDao){
        this.songDao=songDao;
    }

    @Override
    public void create(SongDTO song) {
        Song songEntity = DTOMapper.toEntity(song);
        songDao.create(songEntity);
        song.setId(songEntity.getId());
    }

    @Override
    public void update(SongDTO song) {
        Song songEntity = DTOMapper.toEntity(song);
        songDao.update(songEntity);
        song = DTOMapper.toDTO(songEntity);
    }

    @Override
    public void delete(SongDTO song) {
        songDao.delete(DTOMapper.toEntity(song));
    }

    @Override
    public SongDTO getById(Long id) {
        return DTOMapper.toDTO(songDao.getById(id));
    }
    
    @Override
    public List<SongDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDTO> getByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDTO> getByGenre(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDTO> getByArtist(Artist artist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDTO> getByAlbum(Album album) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
