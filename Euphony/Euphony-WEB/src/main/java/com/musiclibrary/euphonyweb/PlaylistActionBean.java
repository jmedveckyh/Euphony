package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import java.util.List;
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
@UrlBinding("/playlist/{playlist.id}")
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

    
    //--- displaying one playlist ----
    @DefaultHandler
    public Resolution show() {
        playlists = playlistService.getAll();
        return new ForwardResolution("/playlist/id.jsp");
    }
    
    //--- part for adding ----
   
    public Resolution add() {
//        log.debug("add() genre={}", genre);
        playlistService.create(playlist);
//        getContext().getMessages().add(new LocalizableMessage("genre.add.message",escapeHTML(genre.getName())));
        return new RedirectResolution(this.getClass(), "/explore");
    }
    
    public Resolution cancel() {
//        log.debug("cancel() genre={}", genre);
        return new RedirectResolution(this.getClass(), "/explore");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        playlists = playlistService.getAll();
        return null;
    }
    
    //--- part for deleting a genre ----
    public Resolution delete() {
        //log.debug("delete({})", genre.getId());
        //only id is filled by the form
        playlist = playlistService.getById(playlist.getId());
        playlistService.delete(playlist);
        //getContext().getMessages().add(new LocalizableMessage("genre.delete.message", escapeHTML(genre.getTitle()), escapeHTML(genre.getAuthor())));
        return new RedirectResolution(this.getClass(), "/explore");
    }

    //--- part for editing a genre ----
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"save", "show"})
    public void loadPlaylistFromDatabase() {
        String ids = getContext().getRequest().getParameter("playlist.id");
        if (ids == null) {
            return;
        }
        playlist = playlistService.getById(Long.parseLong(ids));
    }

    public Resolution save() {
        //log.debug("save() genre={}", genre);
        playlistService.update(playlist);
        return new RedirectResolution(this.getClass(), "/playlist/id.jsp");
    }
}
