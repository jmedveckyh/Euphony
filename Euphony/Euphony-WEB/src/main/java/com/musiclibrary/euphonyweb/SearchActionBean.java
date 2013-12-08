/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Branislav Novotny <br.novotny@gmail.com> #396152
 */
@UrlBinding("/search/{$event}/{phrase}")
public class SearchActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected PlaylistService playlistService;
    
    
    @Validate(on = {"search"}, required = true)
    private String phrase;
    
    private List<PlaylistDTO> playlists;

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors ve) throws Exception {
        throw new IllegalArgumentException("Not supported yet.");
    }
    
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"search"})
    public void loadPhraseFromDatabase() {
        phrase = getContext().getRequest().getParameter("phrase");
    }
    
    @DefaultHandler
    public Resolution search() {
        playlists = playlistService.getAll();
        return new ForwardResolution("/search.jsp");
    }
}
