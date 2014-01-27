package com.musiclibrary.euphonybusinesslogicimplementation.facade.impl;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import com.musiclibrary.euphonyapi.services.AccountService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonybusinesslogicimplementation.util.DTOMapper;
import com.musiclibrary.euphonybusinesslogicimplementation.util.Util;
import java.util.HashMap;
import java.util.List;
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
@Transactional
public class MusicFacadeImpl implements MusicFacade {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private AccountService accountService;

    @Override
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    
    @Override
    public List<PlaylistDTO> getPlaylistsByAccount(String username){
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        }
        AccountDTO acc = accountService.getByUsername(username);
        if (acc.getIsAdmin()) {
            return playlistService.getAll();
        } 
        else 
            return acc.getPlaylists();    
    }
    
    @Override
    public void addPlaylistByAccount(String username, PlaylistDTO playlist){
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        }
        if (playlist == null) {
            throw new IllegalArgumentException("playlist is null");
        }
        
        playlistService.create(playlist);
        AccountDTO acc = accountService.getByUsername(username);
        List<PlaylistDTO> pLists = acc.getPlaylists();
        pLists.add(playlist);
        acc.setPlaylists(pLists);
        accountService.update(acc);
    }
    
    @Override
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
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
    public void addSongToPlaylist(SongDTO song, PlaylistDTO playlist) {

        if (isSongInPlaylist(song, playlist)) {
            throw new IllegalArgumentException("The song is already in playlist.");
        } else {
            Integer newKey = 0;
            if (playlist.getSongs() == null) {
                Map<Integer, SongDTO> emptyListOfSongs = new HashMap<>();
                emptyListOfSongs.put(newKey + 1, song);
                playlist.setSongs(emptyListOfSongs);
            } else {
                for (Entry<Integer, SongDTO> entry : playlist.getSongs().entrySet()) {
                    newKey = entry.getKey();
                }
                playlist.getSongs().put(newKey + 1, song);
            }
            playlistService.update(playlist);
        }

    }

    @Override
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
}
