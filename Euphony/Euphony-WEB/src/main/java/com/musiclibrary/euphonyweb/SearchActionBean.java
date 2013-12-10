/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
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
    
    @SpringBean
    protected SongService songService;
    
    @SpringBean
    protected AlbumService albumService;
    
    @SpringBean
    protected ArtistService artistService;
    
    @Validate(on = {"search"}, required = true)
    private String phrase;
    
    private List<PlaylistDTO> playlists;

    private List<SongDTO> songs;

    private List<AlbumDTO> albums;

    private List<ArtistDTO> artists;

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }
    
    public List<AlbumDTO> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumDTO> albums) {
        this.albums = albums;
    }
    
    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }
    
     public List<SongDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDTO> songs) {
        this.songs = songs;
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

    @DefaultHandler
    public Resolution search() {
        playlists = playlistService.getAll();
        songs = songService.getSongsByTitleSub(phrase);
        albums = albumService.getAlbumsByTitleSub(phrase);
        artists = artistService.getArtistsByNameSub(phrase);
        
        return new ForwardResolution("/search.jsp");
    }
}