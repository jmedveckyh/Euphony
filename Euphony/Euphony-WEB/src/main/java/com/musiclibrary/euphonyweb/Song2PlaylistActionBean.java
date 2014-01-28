package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import com.musiclibrary.euphonyapi.services.AccountService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
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
    @Validate(required = true, on = {"song2playlist", "songFromPlaylist"})
    private List<Long> selectedSongs;

    public List<Long> getSelectedSongs() {
        return selectedSongs;
    }

    public void setSelectedSongs(List<Long> selectedSongs) {
        this.selectedSongs = selectedSongs;
    }
    @Validate(required = true, on = {"song2playlist", "songFromPlaylist"})
    private Long selectedPlaylist;

    public Long getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(Long selectedPlaylist) {
        this.selectedPlaylist = selectedPlaylist;
    }

    public Resolution song2playlist() {
        if (selectedPlaylist == null) {
            return new RedirectResolution("/explore");
        }
        songs = songService.getAll();
        HttpSession session = getContext().getRequest().getSession();
        playlists = facade.getPlaylistsByAccount((String) session.getAttribute("username"));        
        for (Long selectedSong : selectedSongs) {
            facade.addSongToPlaylist(songService.getById(selectedSong), playlistService.getById(selectedPlaylist));
        }

        return new RedirectResolution("/explore");
    }

    public Resolution songFromPlaylist() {
        songs = songService.getAll();
        HttpSession session = getContext().getRequest().getSession();
        playlists = facade.getPlaylistsByAccount((String) session.getAttribute("username"));   
        if (selectedSongs == null) {
            return new ForwardResolution("/playlist/else/show/" + selectedPlaylist);
        }
        for (Long selectedSong : selectedSongs) {
            facade.removeSongFromPlaylist(songService.getById(selectedSong), playlistService.getById(selectedPlaylist));
        }

        return new ForwardResolution("/playlist/else/show/" + selectedPlaylist);
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        HttpSession session = getContext().getRequest().getSession();
        playlists = facade.getPlaylistsByAccount((String) session.getAttribute("username"));        
        return null;
    }
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
}
