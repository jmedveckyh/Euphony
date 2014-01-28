package com.musiclibrary.euphonybusinesslogicimplementation.facade.impl;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import com.musiclibrary.euphonyapi.services.AccountService;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonyapi.services.GenreService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
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
public class MusicFacadeImpl implements MusicFacade {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private SongService songService;

    @Autowired
    private PlaylistService playlistService;

    @Override
    public List<PlaylistDTO> getPlaylistsByAccount(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        }
        AccountDTO acc = accountService.getByUsername(username);
        return acc.getPlaylists();
    }

    @Override
    public void addPlaylistToAccount(String username, PlaylistDTO playlist) {
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
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public void setGenreService(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public void setSongService(SongService songService) {
        this.songService = songService;
    }

    @Override
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Override
    public AccountDTO register(AccountDTO account) {
        return accountService.register(account);
    }

    @Override
    public AccountDTO login(String username, String password) {
        return accountService.login(username, password);
    }

    @Override
    public AccountDTO getAccountByUsername(String username) {
        return accountService.getByUsername(username);
    }

    @Override
    public void update(AccountDTO account) {
        accountService.update(account);
    }

    @Override
    public void create(AlbumDTO albumDTO) {
        albumService.create(albumDTO);
    }

    @Override
    public void update(AlbumDTO albumDTO) {
        albumService.update(albumDTO);
    }

    @Override
    public void delete(AlbumDTO albumDTO) {
        albumService.delete(albumDTO);
    }

    @Override
    public AlbumDTO getAlbumById(Long id) {
        return albumService.getById(id);
    }

    @Override
    public AlbumDTO getAlbumByTitle(String title) {
        return albumService.getByTitle(title);
    }

    @Override
    public List<AlbumDTO> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @Override
    public List<AlbumDTO> getAlbumsByTitleSub(String phrase) {
        return albumService.getAlbumsByTitleSub(phrase);
    }

    @Override
    public void create(ArtistDTO artistDTO) {
        artistService.create(artistDTO);
    }

    @Override
    public void update(ArtistDTO artistDTO) {
        artistService.update(artistDTO);
    }

    @Override
    public void delete(ArtistDTO artistDTO) {
        artistService.delete(artistDTO);
    }

    @Override
    public ArtistDTO getArtistById(Long id) {
        return artistService.getById(id);
    }

    @Override
    public List<ArtistDTO> getAllArtists() {
        return artistService.getAll();
    }

    @Override
    public ArtistDTO getArtistByName(String name) {
        return artistService.getByName(name);
    }

    @Override
    public List<ArtistDTO> getArtistsByNameSub(String phrase) {
        return artistService.getArtistsByNameSub(phrase);
    }

    @Override
    public void create(GenreDTO genreDTO) {
        genreService.create(genreDTO);
    }

    @Override
    public void update(GenreDTO genreDTO) {
        genreService.update(genreDTO);
    }

    @Override
    public void delete(GenreDTO genreDTO) {
        genreService.delete(genreDTO);
    }

    @Override
    public GenreDTO getGenreById(Long id) {
        return genreService.getById(id);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreService.getAll();
    }

    @Override
    public GenreDTO getGenreByName(String name) {
        return genreService.getByName(name);
    }

    @Override
    public void create(PlaylistDTO playlistDTO) {
        playlistService.create(playlistDTO);
    }

    @Override
    public void update(PlaylistDTO playlistDTO) {
        playlistService.update(playlistDTO);
    }

    @Override
    public void delete(PlaylistDTO playlistDTO) {
        playlistService.delete(playlistDTO);
    }

    @Override
    public PlaylistDTO getPlaylistById(Long id) {
        return playlistService.getById(id);
    }

    @Override
    public PlaylistDTO getPlaylistByName(String name) {
        return playlistService.getByName(name);
    }

    @Override
    public List<PlaylistDTO> getPlaylistBySong(SongDTO songDTO) {
        return playlistService.getBySong(songDTO);
    }

    @Override
    public List<PlaylistDTO> getAllPlaylists() {
        return playlistService.getAll();
    }

    @Override
    public void create(SongDTO song) {
        songService.create(song);
    }

    @Override
    public void update(SongDTO song) {
        songService.update(song);
    }

    @Override
    public void delete(SongDTO song) {
        songService.delete(song);
    }

    @Override
    public SongDTO getSongById(Long id) {
        return songService.getById(id);
    }

    @Override
    public List<SongDTO> getAllSongs() {
        return songService.getAll();
    }

    @Override
    public List<SongDTO> getSongsByTitle(String title) {
        return songService.getByTitle(title);
    }

    @Override
    public List<SongDTO> getSongsByGenre(GenreDTO genre) {
        return songService.getByGenre(genre);
    }

    @Override
    public List<SongDTO> getSongsByArtist(ArtistDTO artist) {
        return songService.getByArtist(artist);
    }

    @Override
    public List<SongDTO> getSongsByAlbum(AlbumDTO album) {
        return songService.getByAlbum(album);
    }

    @Override
    public List<SongDTO> getSongsByTitleSub(String phrase) {
        return songService.getSongsByTitleSub(phrase);
    }
}
