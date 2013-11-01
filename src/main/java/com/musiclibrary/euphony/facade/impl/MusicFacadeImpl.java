package com.musiclibrary.euphony.facade.impl;

import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.PlaylistDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.facade.MusicFacade;
import com.musiclibrary.euphony.services.AlbumService;
import com.musiclibrary.euphony.services.PlaylistService;
import com.musiclibrary.euphony.util.DTOMapper;
import com.musiclibrary.euphony.util.Util;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of facade layer.
 * 
 * @author Tomas Smetanka #396209
 */
@Service
public class MusicFacadeImpl implements MusicFacade {

    @Autowired
    private PlaylistService playlistService;
    
    @Autowired
    private AlbumService albumService;

    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    public void createPlaylist(PlaylistDTO playlist) {
        playlistService.create(playlist);
    }    

    public void createAlbum(AlbumDTO album) {
        albumService.create(album);
    }
    
    
    @Override
    public Boolean isSongInPlaylist(SongDTO song, PlaylistDTO playlist) throws DataAccessException {

        try {
            Util.validatePlaylist(DTOMapper.toEntity(playlist));
        } catch (IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage()) {};
        }

        if (song.getId() == null) {
            throw new DataAccessException("Song's id is null.") {};
        }
        if (playlist.getId() == null) {
            throw new DataAccessException("Playlist's id is null.") {};
        }

        if (playlist.getSongs() == null) {
            return false;
        } else {
            return playlist.getSongs().containsValue(song);
        }

    }

    @Override
    public Boolean isSongInAlbum(SongDTO song, AlbumDTO album) {

        try {
            Util.validateAlbum(DTOMapper.toEntity(album));
        } catch (IllegalArgumentException ex) {
            throw new DataAccessException(ex.getMessage()) {};
        }

        if (song.getId() == null) {
            throw new DataAccessException("Song's id is null.") {};
        }
        if (album.getId() == null) {
            throw new DataAccessException("Playlist's id is null.") {};
        }

        if (album.getSongs() == null) {
            return false;
        } else {
            return album.getSongs().contains(song);
        }

    }

    @Override
    @Transactional
    public void addSongToPlaylist(SongDTO song, PlaylistDTO playlist) {

        if (isSongInPlaylist(song, playlist)) {
            throw new DataAccessException("The song is already in playlist.") {};
        } else {
            TreeMap<Integer, SongDTO> songs = new TreeMap<>();
            songs.putAll(playlist.getSongs());
            playlist.getSongs().put((Integer) songs.lastKey() + 1, song);
            playlistService.update(playlist);
        }

    }

    @Override
    @Transactional
    public void addSongToAlbum(SongDTO song, AlbumDTO album) {

        if (isSongInAlbum(song, album)) {
            throw new DataAccessException("The song is already in album.") {};
        } else {
            album.getSongs().add(song);
            albumService.update(album);
        }

    }

    @Override
    @Transactional
    public void removeSongFromPlaylist(SongDTO song, PlaylistDTO playlist) {

        if (isSongInPlaylist(song, playlist)) {
            Integer keyToRemove = null;

            for (Map.Entry<Integer, SongDTO> entry : playlist.getSongs().entrySet()) {
                if (song == entry.getValue()) {
                    keyToRemove = entry.getKey();
                    break;
                }
            }

            playlist.getSongs().remove(keyToRemove);
            playlistService.update(playlist);
        } else {
            throw new DataAccessException("The song is not in playlist.") {};
        }

    }

    @Override
    @Transactional
    public void removeSongFromAlbum(SongDTO song, AlbumDTO album) {

        if (isSongInAlbum(song, album)) {
            album.getSongs().remove(song);
            albumService.update(album);
        } else {
            throw new DataAccessException("The song is not in album.") {};
        }

    }
    
}
