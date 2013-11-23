package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.services.GenreService;
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
 * Action bean for Genre.
 *
 * @author Tomas Smetanka
 */
@UrlBinding("/genres/{$event}/{genre.id}")
public class GenreActionBean extends BaseActionBean implements ValidationErrorHandler {
    
    @SpringBean
    protected GenreService genreService;
    
    
    //--- part for showing a list of genres ----
    private List<GenreDTO> genres;
    
    
    @DefaultHandler
    public Resolution list() {
        //log.debug("list()");
        genres = genreService.getAll();
        return new ForwardResolution("/genre/list.jsp");
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    //--- part for adding ----
    @ValidateNestedProperties(value = {
        @Validate(on = {"add", "save"}, field = "name", required = true)
    })
    private GenreDTO genre;
    
    public Resolution add() {
//        log.debug("add() genre={}", genre);
        genreService.create(genre);
//        getContext().getMessages().add(new LocalizableMessage("genre.add.message",escapeHTML(genre.getName())));
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution cancel() {
//        log.debug("cancel() genre={}", genre);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        genres = genreService.getAll();
        return null;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }
    
    //--- part for deleting a genre ----
    public Resolution delete() {
        //log.debug("delete({})", genre.getId());
        //only id is filled by the form
        genre = genreService.getById(genre.getId());
        genreService.delete(genre);
        //getContext().getMessages().add(new LocalizableMessage("genre.delete.message", escapeHTML(genre.getTitle()), escapeHTML(genre.getAuthor())));
        return new RedirectResolution(this.getClass(), "list");
    }

    //--- part for editing a genre ----
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadGenreFromDatabase() {
        String ids = getContext().getRequest().getParameter("genre.id");
        if (ids == null) {
            return;
        }
        genre = genreService.getById(Long.parseLong(ids));
    }

    public Resolution edit() {
        //log.debug("edit() genre={}", genre);
        return new ForwardResolution("/genre/edit.jsp");
    }

    public Resolution save() {
        //log.debug("save() genre={}", genre);
        genreService.update(genre);
        return new RedirectResolution(this.getClass(), "list");
    }
}
