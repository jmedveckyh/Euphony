package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import java.util.List;
import javax.servlet.http.HttpSession;
import net.sourceforge.stripes.action.After;
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
 * Action bean for Genre.
 *
 * @author Medo
 */
@AdminOnly
@UrlBinding("/genres/{$event}/{genre.id}")
public class GenreActionBean extends BaseActionBean implements ValidationErrorHandler {

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
    private List<GenreDTO> genres;

    @DefaultHandler
    public Resolution list() {
        genres = musicFacade.getAllGenres();
        HttpSession session = getContext().getRequest().getSession();        
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return new ForwardResolution("/genre/list.jsp");
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private GenreDTO genre;

    public Resolution add() {
        musicFacade.create(genre);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    @After(stages = LifecycleStage.RequestComplete, on = {"delete"})
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        genres = musicFacade.getAllGenres();
        HttpSession session = getContext().getRequest().getSession();        
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return null;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public Resolution delete() throws Exception {
        genre = musicFacade.getGenreById(genre.getId());
        try {
            musicFacade.delete(genre);
        } catch (DataAccessException ex) {
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadGenreFromDatabase() {
        String ids = getContext().getRequest().getParameter("genre.id");
        if (ids == null) {
            return;
        }
        genre = musicFacade.getGenreById(Long.parseLong(ids));
    }

    public Resolution edit() {
        HttpSession session = getContext().getRequest().getSession();        
        playlists = musicFacade.getPlaylistsByAccount((String) session.getAttribute("username"));
        return new ForwardResolution("/genre/edit.jsp");
    }

    public Resolution save() {
        musicFacade.update(genre);
        return new RedirectResolution(this.getClass(), "list");
    }
}
