package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
import java.util.List;
import java.util.Map;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 * Action bean for Playlist.
 *
 * @author Tomas Smetanka
 */
@UrlBinding("/playlist/else/{$event}/{playlist.id}")
public class PlaylistActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected PlaylistService playlistService;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private PlaylistDTO playlist;
    private List<PlaylistDTO> playlists;

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public PlaylistDTO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }
    protected SongService songService;
    private SongDTO song;
    private Map<Integer, SongDTO> songs;

    public SongDTO getSong() {
        return song;
    }

    public void setSong(SongDTO song) {
        this.song = song;
    }

    public Map<Integer, SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(Map<Integer, SongDTO> songs) {
        this.songs = songs;
    }

    @DefaultHandler
    public Resolution show() {
        playlists = playlistService.getAll();
        songs = playlist.getSongs();
        return new ForwardResolution("/playlist/id.jsp");
    }

    public Resolution add() {
        playlistService.create(playlist);
        Long ids = playlist.getId();
        return new RedirectResolution("/playlist/else/show/" + ids);
    }

    public Resolution cancel() {
        return new RedirectResolution("/explore");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        playlists = playlistService.getAll();
        return null;
    }

    public Resolution delete() {
        playlist = playlistService.getById(playlist.getId());
        playlistService.delete(playlist);
        return new RedirectResolution("/explore");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"save", "add", "show"})
    public void loadPlaylistFromDatabase() {
        String ids = getContext().getRequest().getParameter("playlist.id");
        if (ids == null) {
            return;
        }
        playlist = playlistService.getById(Long.parseLong(ids));
    }

    public Resolution save() {
        playlistService.update(playlist);
        Long ids = playlist.getId();
        return new RedirectResolution("/playlist/else/show/" + ids);
    }
}
