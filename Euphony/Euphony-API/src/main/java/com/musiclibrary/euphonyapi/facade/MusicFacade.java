package com.musiclibrary.euphonyapi.facade;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;

/**
 * Interface for facade layer. 
 * Other methods that communicate with UI layer will come later.
 * 
 * @author Tomáš Smetanka
 */
public interface MusicFacade {
    
    /**
     * Checks if the playlist contains the song.
     * 
     * @param song to be checked, cannot be null.
     * @param playlist to be checked, cannot be null.
     * @return true if the song is already in the playlist, otherwise false.
     */
    public Boolean isSongInPlaylist(SongDTO song, PlaylistDTO playlist);
    
    /**
     * Checks if the album contains the song.
     * 
     * @param song to be checked, cannot be null.
     * @param album to be checked, cannot be null.
     * @return true if the song is already in the album, otherwise false.
     */
    public Boolean isSongInAlbum(SongDTO song, AlbumDTO album);
    
    /**
     * Adds the song into the playlist.
     * 
     * @param song to be added, cannot be null.
     * @param playlist to be added to, cannot be null.
     */
    public void addSongToPlaylist(SongDTO song, PlaylistDTO playlist);
    
    /**
     * Adds the song into the album.
     * 
     * @param song to be added, cannot be null.
     * @param album to be added to, cannot be null.
     */
    public void addSongToAlbum(SongDTO song, AlbumDTO album);
    
    /**
     * Removes the song from the playlist.
     * 
     * @param song to be removed, cannot be null.
     * @param playlist to be removed from, cannot be null.
     */
    public void removeSongFromPlaylist(SongDTO song, PlaylistDTO playlist);
    
    /**
     * Removes the song into the album.
     * 
     * @param song to be removed, cannot be null.
     * @param album to be removed from, cannot be null.
     */
    public void removeSongFromAlbum(SongDTO song, AlbumDTO album);

    public void setPlaylistService(PlaylistService playlistService);

    public void setAlbumService(AlbumService albumService);
    
    public void setSongService(SongService songService);
    
}
