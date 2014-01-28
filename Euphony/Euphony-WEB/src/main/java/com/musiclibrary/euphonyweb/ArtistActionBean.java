package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import java.util.List;
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
import org.springframework.dao.DataAccessException;

/**
 * Action bean for Artist.
 *
 * @author Medo
 */
@AdminOnly
@UrlBinding("/artists/{$event}/{artist.id}")
public class ArtistActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected MusicFacade musicFacade;

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
    private List<ArtistDTO> artists;

    @DefaultHandler
    public Resolution list() {
        artists = musicFacade.getAllArtists();
        HttpSession session = getContext().getRequest().getSession();        
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return new ForwardResolution("/artist/list.jsp");
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private ArtistDTO artist;

    public Resolution add() {
        musicFacade.create(artist);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        artists = musicFacade.getAllArtists();
        HttpSession session = getContext().getRequest().getSession();        
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return null;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    public Resolution delete() {
        artist = musicFacade.getArtistById(artist.getId());
        try {
            musicFacade.delete(artist);
        } catch (DataAccessException ex) {
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadArtistFromDatabase() {
        String ids = getContext().getRequest().getParameter("artist.id");
        if (ids == null) {
            return;
        }
        artist = musicFacade.getArtistById(Long.parseLong(ids));
    }

    public Resolution edit() {
        HttpSession session = getContext().getRequest().getSession();        
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return new ForwardResolution("/artist/edit.jsp");
    }

    public Resolution save() {
        musicFacade.update(artist);
        return new RedirectResolution(this.getClass(), "list");
    }
}
