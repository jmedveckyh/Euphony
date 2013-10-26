/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphony.util;

import com.musiclibrary.euphony.dto.SongDTO;
import com.musiclibrary.euphony.entities.Song;

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
}
