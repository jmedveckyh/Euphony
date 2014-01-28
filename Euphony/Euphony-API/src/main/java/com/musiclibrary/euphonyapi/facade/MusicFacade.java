package com.musiclibrary.euphonyapi.facade;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.AccountService;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonyapi.services.GenreService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
import java.util.List;

/**
 * Interface for facade layer. Other methods that communicate with UI layer will
 * come later.
 *
 * @author Tomáš Smetanka
 */
public interface MusicFacade {

    void setPlaylistService(PlaylistService playlistService);
    
    void setAccountService(AccountService accountService);
    
    void setAlbumService(AlbumService albumService);
    
    void setArtistService(ArtistService artistService);
    
    void setGenreService(GenreService genreService);
    
    void setSongService(SongService songService);
    
    AccountDTO register (AccountDTO account);

    AccountDTO login (String username, String password);
    
    AccountDTO getAccountByUsername(String username);
    
    void create(AlbumDTO albumDTO);

    void update(AlbumDTO albumDTO);

    void delete(AlbumDTO albumDTO);

    AlbumDTO getAlbumById(Long id);

    AlbumDTO getAlbumByTitle(String title);

    List<AlbumDTO> getAllAlbums();

    List<AlbumDTO> getAlbumsByTitleSub(String phrase);
    
    void create(ArtistDTO artistDTO);

    void update(ArtistDTO artistDTO);

    void delete(ArtistDTO artistDTO);

    ArtistDTO getArtistById(Long id);

    List<ArtistDTO> getAllArtists();

    ArtistDTO getArtistByName(String name);

    List<ArtistDTO> getArtistsByNameSub(String phrase);
    
    void create(GenreDTO genreDTO);

    void update(GenreDTO genreDTO);

    void delete(GenreDTO genreDTO);

    GenreDTO getGenreById(Long id);

    List<GenreDTO> getAllGenres();

    GenreDTO getGenreByName(String name);
    
    void create(PlaylistDTO playlistDTO);

    void update(PlaylistDTO playlistDTO);

    void delete(PlaylistDTO playlistDTO);
    
    void removePlaylistFromAccount(String username, PlaylistDTO playlist);

    PlaylistDTO getPlaylistById(Long id);

    PlaylistDTO getPlaylistByName(String name);

    List<PlaylistDTO> getPlaylistBySong(SongDTO songDTO);

    List<PlaylistDTO> getAllPlaylists();
    
    void create(SongDTO song);

    void update(SongDTO song);

    void delete(SongDTO song);

    SongDTO getSongById(Long id);

    List<SongDTO> getAllSongs();

    List<SongDTO> getSongsByTitle(String title);

    List<SongDTO> getSongsByGenre(GenreDTO genre);

    List<SongDTO> getSongsByArtist(ArtistDTO artist);

    List<SongDTO> getSongsByAlbum(AlbumDTO album);

    List<SongDTO> getSongsByTitleSub(String phrase);
    
    /**
     * Checks if the playlist contains the song.
     *
     * @param song to be checked, cannot be null.
     * @param playlist to be checked, cannot be null.
     * @return true if the song is already in the playlist, otherwise false.
     */
    Boolean isSongInPlaylist(SongDTO song, PlaylistDTO playlist);

    /**
     * Adds the song into the playlist.
     *
     * @param song to be added, cannot be null.
     * @param playlist to be added to, cannot be null.
     */
    void addSongToPlaylist(SongDTO song, PlaylistDTO playlist);

    /**
     * Removes the song from the playlist.
     *
     * @param song to be removed, cannot be null.
     * @param playlist to be removed from, cannot be null.
     */
    void removeSongFromPlaylist(SongDTO song, PlaylistDTO playlist);

    void addPlaylistToAccount(String username, PlaylistDTO playlist);
    
    List<PlaylistDTO> getPlaylistsByAccount(String username);
}
