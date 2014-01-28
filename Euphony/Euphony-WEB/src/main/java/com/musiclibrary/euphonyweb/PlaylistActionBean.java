package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AccountDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
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
    protected MusicFacade musicFacade;
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private PlaylistDTO playlist;
    private List<PlaylistDTO> playlists;

    private AccountDTO account;

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public PlaylistDTO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }

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
        HttpSession session = getContext().getRequest().getSession();
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        songs = playlist.getSongs();
        return new ForwardResolution("/playlist/id.jsp");
    }

    public Resolution add() {
        HttpSession session = getContext().getRequest().getSession();
        musicFacade.addPlaylistToAccount((String) session.getAttribute("username"), playlist);
        Long ids = playlist.getId();
        return new RedirectResolution("/playlist/else/show/" + ids);
    }

    public Resolution cancel() {
        return new RedirectResolution("/explore");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        playlists = musicFacade.getAllPlaylists();
        return null;
    }

    public Resolution delete() {
        playlist = musicFacade.getPlaylistById(playlist.getId());
        musicFacade.delete(playlist);
        return new RedirectResolution("/explore");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"save", "add", "show"})
    public void loadPlaylistFromDatabase() {
        String ids = getContext().getRequest().getParameter("playlist.id");
        if (ids == null) {
            return;
        }
        playlist = musicFacade.getPlaylistById(Long.parseLong(ids));
    }

    public Resolution save() {
        musicFacade.update(playlist);
        Long ids = playlist.getId();
        return new RedirectResolution("/playlist/else/show/" + ids);
    }
}
