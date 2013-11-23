/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.AlbumService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.DateTypeConverter;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;

/**
 *
 * @author Sebastian
 */
@UrlBinding("/albums/{$event}/{album.id}")
public class AlbumActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected AlbumService albumService;
    
    private List<AlbumDTO> albums;
    
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "title", required = true)
    })
    private AlbumDTO album;
    
    @Validate(on = {"add", "save"}, required = true)
    private String releaseDate;

    @DefaultHandler
    public Resolution list() {
        albums = albumService.getAllAlbums();
        return new ForwardResolution("/album/list.jsp");
    }

    public List<AlbumDTO> getAlbums() {
        return albums;
    }

    public Resolution add() {
        album.setArtists(new ArrayList<ArtistDTO>());
        album.setGenres(new ArrayList<GenreDTO>());
        album.setSongs(new ArrayList<SongDTO>());
        //NACITAVANIE CASU
        album.setReleaseDate(new DateTime(Integer.parseInt(releaseDate.substring(6)), Integer.parseInt(releaseDate.substring(3, 5)), Integer.parseInt(releaseDate.substring(0, 2)), 0, 0));
        albumService.create(album);
        //getContext().getMessages().add(new LocalizableMessage("book.add.message",escapeHTML(book.getTitle()),escapeHTML(book.getAuthor())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        albums = albumService.getAllAlbums();
        //return null to let the event handling continue
        return null;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }

    @Validate
    public Resolution delete() {
        album = albumService.getById(album.getId());
        albumService.delete(album);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadSongFromDatabase() {
        String ids = getContext().getRequest().getParameter("album.id");
        if (ids == null) {
            return;
        }
        album = albumService.getById(Long.parseLong(ids));
    }

    public Resolution edit() {
        return new ForwardResolution("/album/edit.jsp");
    }

    public Resolution save() {
        album.setReleaseDate(new DateTime(Integer.parseInt(releaseDate.substring(6)), Integer.parseInt(releaseDate.substring(3, 5)), Integer.parseInt(releaseDate.substring(0, 2)), 0, 0));
        albumService.update(album);
        return new RedirectResolution(this.getClass(), "list");
    }
}
