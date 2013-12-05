package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 * *** action bean for showing songs, albums and artists in index.
 * 
 * @author Tomas Smetanka #396209
 */

public class Song2PlaylistActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected MusicFacade facade;
    @SpringBean
    protected PlaylistService playlistService;
    @SpringBean
    protected SongService songService;
    
    @ValidateNestedProperties(value = {
        @Validate(required = true, on = {"song2playlist"})
    })
    private List<Long> selectedSongs;
    
    public List<Long> getSelectedSongs() {
        return selectedSongs;
    }

    public void setSelectedSongs(List<Long> selectedSongs) {
        this.selectedSongs = selectedSongs;
    }
//    private List<SongDTO> selectedSongs;
//
//    public List<SongDTO> getSelectedSongs() {
//        return selectedSongs;
//    }
//
//    public void setSelectedSongs(List<SongDTO> selectedSongs) {
//        this.selectedSongs = selectedSongs;
////        this.selectedSongs.clear();
////        for (SongDTO selectedSong : selectedSongs) {
////            this.selectedSongs.add(songService.getById(Long.parseLong(selectedSong.getTitle())));
////        }
//    }

    
    @ValidateNestedProperties(value = {
        @Validate(required = true, on = "song2playlist")  
    })  
    private Long selectedPlaylist;

    public Long getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(Long selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }
//    private PlaylistDTO selectedPlaylist;
//
//    public PlaylistDTO getSelectedPlaylist() {
//        return selectedPlaylist;
//    }
//
//    public void setSelectedPlaylist(PlaylistDTO selectedPlaylist) {
//        this.selectedPlaylist = selectedPlaylist;
////        this.selectedPlaylist = playlistService.getById(Long.parseLong(selectedPlaylist.getName()));
//    }
   
    
    private List<PlaylistDTO> playlists;

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }
    private List<SongDTO> songs;

    public List<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDTO> songs) {
        this.songs = songs;
    }

    public Resolution song2playlist() {
        songs = songService.getAll();
        playlists = playlistService.getAll();
//        for (SongDTO selectedSong : selectedSongs) {
//            facade.addSongToPlaylist(selectedSong, selectedPlaylist);
//        }
        for (Long selectedSong : selectedSongs) {
            facade.addSongToPlaylist(songService.getById(selectedSong), playlistService.getById(selectedPlaylist));
        }
//        return new ForwardResolution("/explore/songs.jsp");
        return new RedirectResolution("/explore"); 
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {

        return null;
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"song2playlist"})
    public void loadFromDatabase() {
//        for (SongDTO selectedSong : selectedSongs) {
//            newSelectedSongs.add(songService.getById(Long.parseLong(selectedSong.getTitle())));
//            selectedPlaylist = playlistService.getById(Long.parseLong(selectedPlaylist.getName()));
//        }
        
//        String idPlaylist = getContext().getRequest().getParameter("playlist.id");
//        String idSong = getContext().getRequest().getParameter("song.id");
//        if (idPlaylist == null || idSong == null) {
//            return;
//        }
//        playlist = playlistService.getById(Long.parseLong(idPlaylist));
//        song = songService.getById(Long.parseLong(idSong));
    }

    //--- getters and setters ----

}
