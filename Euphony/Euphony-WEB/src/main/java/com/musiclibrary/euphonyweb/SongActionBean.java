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
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonyapi.services.GenreService;
import com.musiclibrary.euphonyapi.services.SongService;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import org.joda.time.DateTime;

/**
 *
 * @author Sebastian
 */
@UrlBinding("/songs/{$event}/{song.id}")
public class SongActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean //Spring can inject even to private and protected fields
    protected SongService songService;
    @SpringBean
    protected AlbumService albumService;
    @SpringBean
    protected GenreService genreService;
    @SpringBean
    protected ArtistService artistService;
    
    private List<SongDTO> songs;
    private List<AlbumDTO> albums;
    private List<GenreDTO> genres;
    private List<ArtistDTO> artists;
    private SongDTO song;

    @DefaultHandler
    public Resolution list() {
        songs = songService.getAll();
        albums = albumService.getAllAlbums();
        genres = genreService.getAll();
        artists = artistService.getAll();
        return new ForwardResolution("/song/list.jsp");
    }

    public List<SongDTO> getSongs() {
        return songs;
    }

    //--- part for adding a book ----

    /*
    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "author", required = true),
            @Validate(on = {"add", "save"}, field = "title", required = true),
            @Validate(on = {"add", "save"}, field = "year", required = true, minvalue = 800)
    })
    * */

    public Resolution add() {
        song.setArtist(new ArtistDTO("Jozo Psenica"));
        song.setGenre(new GenreDTO("DNB"));
        song.setAlbum(new AlbumDTO("title", "cover.jpg", DateTime.now() , new ArrayList<SongDTO>(), "" ,new ArrayList<ArtistDTO>(), new ArrayList<GenreDTO>()));
        songService.create(song);
        //getContext().getMessages().add(new LocalizableMessage("book.add.message",escapeHTML(book.getTitle()),escapeHTML(book.getAuthor())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        songs = songService.getAll();
        //return null to let the event handling continue
        return null;
    }

    public SongDTO getSong() {
        return song;
    }

    public void setSong(SongDTO song) {
        this.song = song;
    }

    //--- part for deleting a book ----

    /*
    public Resolution delete() {
        log.debug("delete({})", book.getId());
        //only id is filled by the form
        book = bookLibrary.getBook(book.getId());
        bookLibrary.deleteBook(book.getId());
        getContext().getMessages().add(new LocalizableMessage("book.delete.message",escapeHTML(book.getTitle()),escapeHTML(book.getAuthor())));
        return new RedirectResolution(this.getClass(), "list");
    }
    */

    //--- part for editing a book ----

    /*
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadBookFromDatabase() {
        String ids = getContext().getRequest().getParameter("book.id");
        if (ids == null) return;
        book = bookLibrary.getBook(Long.parseLong(ids));
    }

    public Resolution edit() {
        log.debug("edit() book={}", book);
        return new ForwardResolution("/book/edit.jsp");
    }

    public Resolution save() {
        log.debug("save() book={}", book);
        bookLibrary.updateBook(book);
        return new RedirectResolution(this.getClass(), "list");
    }
    */
}
