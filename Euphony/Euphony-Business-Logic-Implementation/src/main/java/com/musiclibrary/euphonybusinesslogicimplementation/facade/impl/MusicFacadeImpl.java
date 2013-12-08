package com.musiclibrary.euphonybusinesslogicimplementation.facade.impl;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import com.musiclibrary.euphonybusinesslogicimplementation.util.MusicErrorException;
import com.musiclibrary.euphonybusinesslogicimplementation.util.Util;
import java.util.Map;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of facade layer.
 * 
 * @author Tomas Smetanka
 */
@Service
public class MusicFacadeImpl implements MusicFacade {

    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private AlbumService albumService;

    @Override
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
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
    @Transactional
    public Boolean isSongInPlaylist(SongDTO song, PlaylistDTO playlist) {

        Util.validatePlaylist(DTOMapper.toEntity(playlist));

        if (song.getId() == null) {
            throw new IllegalArgumentException("Song's id is null.");
        }
        if (playlist.getId() == null) {
            throw new IllegalArgumentException("Playlist's id is null.");
        }

        if (playlist.getSongs() == null) {
            return false;
        } else {
            for (Entry<Integer, SongDTO> entry : playlist.getSongs().entrySet()) {
                if (song.equals(entry.getValue())) {
                    return true;
                }
            }
            return false;
        }

    }

    @Override
    @Transactional
    public Boolean isSongInAlbum(SongDTO song, AlbumDTO album) {

        Util.validateAlbum(DTOMapper.toEntity(album));

        if (song.getId() == null) {
            throw new IllegalArgumentException("Song's id is null.");
        }
        if (album.getId() == null) {
            throw new IllegalArgumentException("Playlist's id is null.");
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
            throw new IllegalArgumentException("The song is already in playlist.");
        } else {
            Integer newKey = 0;
            for (Entry<Integer, SongDTO> entry : playlist.getSongs().entrySet()) {
                newKey = entry.getKey();
            }
            playlist.getSongs().put(newKey + 1, song);
            playlistService.update(playlist);
        }

    }

    @Override
    @Transactional
    public void addSongToAlbum(SongDTO song, AlbumDTO album) {

        if (isSongInAlbum(song, album)) {
            throw new IllegalArgumentException("The song is already in album.");
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

            for (Entry<Integer, SongDTO> entry : playlist.getSongs().entrySet()) {
                if (song.equals(entry.getValue())) {
                    keyToRemove = entry.getKey();
                    break;
                }
            }

            playlist.getSongs().remove(keyToRemove);
            playlistService.update(playlist);
        } else {
            throw new IllegalArgumentException("The song is not in playlist.");
        }

    }

    @Override
    @Transactional
    public void removeSongFromAlbum(SongDTO song, AlbumDTO album) {

        if (isSongInAlbum(song, album)) {
            album.getSongs().remove(song);
            albumService.update(album);
        } else {
            throw new IllegalArgumentException("The song is not in album.");
        }

    }
}
