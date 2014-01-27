package com.musiclibrary.euphonybusinesslogicimplementation.util;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Account;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * DTO to Entity and Entity to DTO mapper.
 *
 * @author Everyone
 */
public class DTOMapper {

    public static Song toEntity(SongDTO songDto) {

        if (songDto == null) {
            return null;
        }
        Song song = new Song();
        song.setAlbum(toEntity(songDto.getAlbum()));
        song.setBitrate(songDto.getBitrate());
        song.setComment(songDto.getComment());
        song.setGenre(toEntity(songDto.getGenre()));
        song.setTitle(songDto.getTitle());
        song.setTrackNumber(songDto.getTrackNumber());
        song.setId(songDto.getId());
        song.setArtist(toEntity(songDto.getArtist()));
        return song;

    }

    public static SongDTO toDTO(Song song) {

        if (song == null) {
            return null;
        }
        SongDTO songDto = new SongDTO();
        songDto.setAlbum(toDTO(song.getAlbum()));
        songDto.setBitrate(song.getBitrate());
        songDto.setComment(song.getComment());
        songDto.setGenre(toDTO(song.getGenre()));
        songDto.setTitle(song.getTitle());
        songDto.setTrackNumber(song.getTrackNumber());
        songDto.setId(song.getId());
        songDto.setArtist(toDTO(song.getArtist()));
        return songDto;

    }

    public static List<SongDTO> songsListToDTO(List<Song> songs) {

        if (songs == null) {
            return null;
        }
        List<SongDTO> songsDTO = new ArrayList();
        for (Song song : songs) {
            songsDTO.add(DTOMapper.toDTO(song));
        }
        return songsDTO;
    }

    public static List<Song> songsListToEntity(List<SongDTO> songsDto) {

        if (songsDto == null) {
            return null;
        }
        List<Song> songs = new ArrayList();
        for (SongDTO songDto : songsDto) {
            songs.add(DTOMapper.toEntity(songDto));
        }
        return songs;
    }

    public static Genre toEntity(GenreDTO genreDto) {

        if (genreDto == null) {
            return null;
        }
        Genre genre = new Genre();
        genre.setName(genreDto.getName());
        genre.setId(genreDto.getId());
        return genre;

    }

    public static GenreDTO toDTO(Genre genre) {

        if (genre == null) {
            return null;
        }
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;

    }

    public static List<GenreDTO> genresListToDTO(List<Genre> genres) {

        if (genres == null) {
            return null;
        }
        List<GenreDTO> genresDTO = new ArrayList();
        for (Genre genre : genres) {
            genresDTO.add(DTOMapper.toDTO(genre));
        }
        return genresDTO;
    }

    public static List<Genre> genresListToEntity(List<GenreDTO> genresDto) {

        if (genresDto == null) {
            return null;
        }
        List<Genre> genres = new ArrayList();
        for (GenreDTO genreDto : genresDto) {
            genres.add(DTOMapper.toEntity(genreDto));
        }
        return genres;
    }

    public static Playlist toEntity(PlaylistDTO playlistDto) {

        if (playlistDto == null) {
            return null;
        }
        Playlist playlist = new Playlist();
        playlist.setName(playlistDto.getName());
        playlist.setId(playlistDto.getId());
        playlist.setSongs(DTOMapper.songsInPlaylistMapToEntity(playlistDto.getSongs()));
        return playlist;

    }

    public static PlaylistDTO toDTO(Playlist playlist) {

        if (playlist == null) {
            return null;
        }
        PlaylistDTO playlistDTO = new PlaylistDTO();
        playlistDTO.setId(playlist.getId());
        playlistDTO.setName(playlist.getName());
        playlistDTO.setSongs(songsInPlaylistMapToDTO(playlist.getSongs()));
        return playlistDTO;

    }

    public static Map<Integer, SongDTO> songsInPlaylistMapToDTO(Map<Integer, Song> songs) {

        if (songs == null) {
            return null;
        }
        Map<Integer, SongDTO> songsDTO = new TreeMap<>();
        for (Map.Entry<Integer, Song> entry : songs.entrySet()) {
            songsDTO.put(entry.getKey(), DTOMapper.toDTO(entry.getValue()));
        }
        return songsDTO;

    }

    public static Map<Integer, Song> songsInPlaylistMapToEntity(Map<Integer, SongDTO> songsDTO) {

        if (songsDTO == null) {
            return null;
        }
        Map<Integer, Song> songs = new TreeMap<>();
        for (Map.Entry<Integer, SongDTO> entry : songsDTO.entrySet()) {
            songs.put(entry.getKey(), DTOMapper.toEntity(entry.getValue()));
        }
        return songs;

    }

    public static List<PlaylistDTO> playlistListToDTO(List<Playlist> playlists) {

        if (playlists == null) {
            return null;
        }
        List<PlaylistDTO> playlistsDTO = new ArrayList();
        for (Playlist playlist : playlists) {
            playlistsDTO.add(DTOMapper.toDTO(playlist));
        }
        return playlistsDTO;

    }

    public static List<Playlist> playlistListToEntity(List<PlaylistDTO> playlistsDTO) {

        if (playlistsDTO == null) {
            return null;
        }
        List<Playlist> playlists = new ArrayList();
        for (PlaylistDTO playlistDTO : playlistsDTO) {
            playlists.add(DTOMapper.toEntity(playlistDTO));
        }
        return playlists;

    }

    public static Album toEntity(AlbumDTO albumDTO) {

        if (albumDTO == null) {
            return null;
        }
        Album album = new Album();
        album.setId(albumDTO.getId());
        album.setCover(albumDTO.getCover());
        album.setReleaseDate(albumDTO.getReleaseDate());
        album.setTitle(albumDTO.getTitle());
        album.setComment(albumDTO.getComment());
        return album;

    }

    public static AlbumDTO toDTO(Album album) {

        if (album == null) {
            return null;
        }
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setCover(album.getCover());
        albumDTO.setReleaseDate(album.getReleaseDate());
        albumDTO.setTitle(album.getTitle());
        albumDTO.setComment(album.getComment());
        return albumDTO;

    }

    public static Artist toEntity(ArtistDTO artistDto) {

        if (artistDto == null) {
            return null;
        }
        Artist artist = new Artist();
        artist.setName(artistDto.getName());
        artist.setId(artistDto.getId());
        return artist;

    }

    public static ArtistDTO toDTO(Artist artist) {

        if (artist == null) {
            return null;
        }
        ArtistDTO artistDTO = new ArtistDTO();
        artistDTO.setId(artist.getId());
        artistDTO.setName(artist.getName());
        return artistDTO;

    }

    public static List<AlbumDTO> albumListToDTO(List<Album> albums) {

        if (albums == null) {
            return null;
        }
        List<AlbumDTO> albumsDTO = new ArrayList();
        for (Album album : albums) {
            albumsDTO.add(DTOMapper.toDTO(album));
        }
        return albumsDTO;

    }

    public static List<Album> albumListToEntity(List<AlbumDTO> albumsDTO) {

        if (albumsDTO == null) {
            return null;
        }
        List<Album> albums = new ArrayList();
        for (AlbumDTO album : albumsDTO) {
            albums.add(DTOMapper.toEntity(album));
        }
        return albums;

    }

    public static List<ArtistDTO> artistsListToDTO(List<Artist> artists) {

        if (artists == null) {
            return null;
        }
        List<ArtistDTO> artistsDTO = new ArrayList();
        for (Artist artist : artists) {
            artistsDTO.add(DTOMapper.toDTO(artist));
        }
        return artistsDTO;

    }

    public static List<Artist> artistsListToEntity(List<ArtistDTO> artistsDto) {

        if (artistsDto == null) {
            return null;
        }
        List<Artist> artists = new ArrayList();
        for (ArtistDTO artistDto : artistsDto) {
            artists.add(DTOMapper.toEntity(artistDto));
        }
        return artists;
    }

    public static AccountDTO ToDTO(Account account) {

        if (account == null) {
            return null;
        }
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setIsAdmin(account.getIsAdmin());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setPlaylists(playlistListToDTO(account.getPlaylists()));
        return accountDTO;
    }
    
    public static Account toEntity(AccountDTO accDto){
        
        if (accDto == null) {
            return null;
        }
        Account account = new Account();
        account.setId(accDto.getId());
        account.setUsername(accDto.getUsername());
        account.setIsAdmin(accDto.getIsAdmin());
        account.setPassword(accDto.getPassword());
        account.setPlaylists(playlistListToEntity(accDto.getPlaylists()));
        return account;

    }
    
 
}
