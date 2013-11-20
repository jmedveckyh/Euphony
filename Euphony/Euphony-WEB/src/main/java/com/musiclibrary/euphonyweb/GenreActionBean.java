package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.services.GenreService;
import java.util.List;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
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
    private GenreDTO genre;
    private List<GenreDTO> genres;
    
    //--- part for adding ----

    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "name", required = true)
    })

    public Resolution add() {
//        log.debug("add() book={}", book);
        genreService.create(genre);
//        getContext().getMessages().add(new LocalizableMessage("book.add.message",escapeHTML(book.getTitle()),escapeHTML(book.getAuthor())));
        return new RedirectResolution(this.getClass(), "");
    }
    
    public Resolution cancel() {
//        log.debug("cancel() book={}", book);
        return new RedirectResolution(this.getClass(), "");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        genres = genreService.getAll();
        return null;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setBook(GenreDTO genre) {
        this.genre = genre;
    }
    
}
