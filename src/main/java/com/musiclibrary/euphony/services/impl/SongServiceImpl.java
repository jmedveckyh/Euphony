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
    public void createSong(SongDTO song) {
        Song songEntity = DTOMapper.toEntity(song);
        songDao.create(songEntity);
        song.setId(songEntity.getId());
    }

    @Override
    public void updateSong(SongDTO song) {
        Song songEntity = DTOMapper.toEntity(song);
        songDao.update(songEntity);
        song = DTOMapper.toDTO(songEntity);
    }

    @Override
    public void deleteSong(SongDTO song) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SongDTO get(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDTO> byTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDTO> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDTO> byGenre(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDTO> byArtist(Artist artist) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SongDTO> byAlbum(Album album) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
