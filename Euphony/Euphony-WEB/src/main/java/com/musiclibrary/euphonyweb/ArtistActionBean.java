package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.LocalizableMessage;
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
 * Action bean for Artist.
 *
 * @author Tomas Smetanka
 */
@UrlBinding("/artists/{$event}/{artist.id}")
public class ArtistActionBean extends BaseActionBean implements ValidationErrorHandler {
    
    @SpringBean
    protected ArtistService artistService;
    
    @SpringBean
    protected PlaylistService playlistService;
    private List<PlaylistDTO> playlists;
    private PlaylistDTO playlist;

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public PlaylistDTO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }
    
    
    //--- part for showing a list of artists ----
    private List<ArtistDTO> artists;
    
    
    @DefaultHandler
    public Resolution list() {
        //log.debug("list()");
        artists = artistService.getAll();
        playlists = playlistService.getAll();
        return new ForwardResolution("/artist/list.jsp");
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    //--- part for adding ----
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private ArtistDTO artist;
    
    public Resolution add() {
//        log.debug("add() artist={}", artist);
        artistService.create(artist);
//        getContext().getMessages().add(new LocalizableMessage("artist.add.message",escapeHTML(artist.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution cancel() {
//        log.debug("cancel() artist={}", artist);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        artists = artistService.getAll();
        return null;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }
    
    //--- part for deleting a artist ----
    public Resolution delete() {
        //log.debug("delete({})", artist.getId());
        //only id is filled by the form
        artist = artistService.getById(artist.getId());
        artistService.delete(artist);
        //getContext().getMessages().add(new LocalizableMessage("artist.delete.message", escapeHTML(artist.getTitle()), escapeHTML(artist.getAuthor())));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a artist ----
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadArtistFromDatabase() {
        String ids = getContext().getRequest().getParameter("artist.id");
        if (ids == null) {
            return;
        }
        artist = artistService.getById(Long.parseLong(ids));
    }

    public Resolution edit() {
        //log.debug("edit() artist={}", artist);
        return new ForwardResolution("/artist/edit.jsp");
    }

    public Resolution save() {
        //log.debug("save() artist={}", artist);
        artistService.update(artist);
        return new RedirectResolution(this.getClass(), "list");
    }
}
