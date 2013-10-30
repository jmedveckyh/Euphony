package com.musiclibrary.euphony.util;

import com.musiclibrary.euphony.dto.AlbumDTO;
import com.musiclibrary.euphony.dto.ArtistDTO;
import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.dto.PlaylistDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.entities.Album;
import com.musiclibrary.euphony.entities.Artist;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Playlist;
import com.musiclibrary.euphony.entities.Song;
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

        List<SongDTO> songsDTO = new ArrayList();
        for (Song song : songs) {
            songsDTO.add(DTOMapper.toDTO(song));
        }
        return songsDTO;
    }

    public static List<Song> songsListToEntity(List<SongDTO> songsDto) {

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

        List<GenreDTO> genresDTO = new ArrayList();
        for (Genre genre : genres) {
            genresDTO.add(DTOMapper.toDTO(genre));
        }
        return genresDTO;
    }

    public static List<Genre> genresListToEntity(List<GenreDTO> genresDto) {

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

        List<PlaylistDTO> playlistsDTO = new ArrayList();
        for (Playlist playlist : playlists) {
            playlistsDTO.add(DTOMapper.toDTO(playlist));
        }
        return playlistsDTO;

    }

    public static List<Playlist> playlistListToEntity(List<PlaylistDTO> playlistsDTO) {

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
        album.setSongs(albumDTO.getSongs());
        album.setTitle(albumDTO.getTitle());
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
        albumDTO.setSongs(album.getSongs());
        albumDTO.setTitle(album.getTitle());
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

    public static List<ArtistDTO> artistsListToDTO(List<Artist> artists) {

        List<ArtistDTO> artistsDTO = new ArrayList();
        for (Artist artist : artists) {
            artistsDTO.add(DTOMapper.toDTO(artist));
        }
        return artistsDTO;

    }

    public static List<Artist> artistsListToEntity(List<ArtistDTO> artistsDto) {

        List<Artist> artists = new ArrayList();
        for (ArtistDTO artistDto : artistsDto) {
            artists.add(DTOMapper.toEntity(artistDto));
        }
        return artists;
    }
}
