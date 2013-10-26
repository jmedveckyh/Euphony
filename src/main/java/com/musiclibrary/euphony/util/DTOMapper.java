/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.util;

//import com.musiclibrary.euphony.dto.GenreDTO;
import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.entities.Genre;
import com.musiclibrary.euphony.entities.Song;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian
 */
public class DTOMapper {
    public static Song toEntity(SongDTO songDto){
        if (songDto == null) {
            return null;
        }
        Song song = new Song();
        song.setAlbum(songDto.getAlbum());
        song.setBitrate(songDto.getBitrate());
        song.setComment(songDto.getComment());
        song.setGenre(songDto.getGenre());
        song.setTitle(songDto.getTitle());
        song.setTrackNumber(songDto.getTrackNumber());
        song.setId(songDto.getId());
        return song;
    }
    
    public static SongDTO toDTO(Song song){
        if (song == null) {
            return null;
        }
        SongDTO songDto = new SongDTO();
        songDto.setAlbum(song.getAlbum());
        songDto.setBitrate(song.getBitrate());
        songDto.setComment(song.getComment());
        songDto.setGenre(song.getGenre());
        songDto.setTitle(song.getTitle());
        songDto.setTrackNumber(song.getTrackNumber());
        songDto.setId(song.getId());
        return songDto;
    }
    
    public static List<SongDTO> toDTO(List<Song> songs){
        List<SongDTO> songsDTO = new ArrayList();
        for(Song song : songs){
            songsDTO.add(DTOMapper.toDTO(song));
        }
        return songsDTO;
    }
    
    /*
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
    */
}
