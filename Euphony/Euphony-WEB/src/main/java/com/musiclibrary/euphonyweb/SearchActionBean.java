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
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
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
    /*
    @Before(stages = LifecycleStage.BindingAndValidation, on = {"search"})
    public void loadPhraseFromDatabase() {
        phrase = getContext().getRequest().getParameter("phrase");
    }
    */
    @DefaultHandler
    public Resolution search() {
        playlists = playlistService.getAll();
        songs = getSongsByTitleSub(phrase);
        albums = getAlbumsByTitleSub(phrase);
        artists = getArtistsByNameSub(phrase);
        playlists = getPlaylistsByNameSub(phrase);
        
        return new ForwardResolution("/search.jsp");
    }
    
    public String removeDiacritics(String str){
        if(str == null){
            return "";
        }
        return Normalizer.normalize(str, Form.NFD)
               .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
    
    public List<SongDTO> getSongsByTitleSub(String subword){
        List<SongDTO> tmpSongs = songService.getAll();
        List<SongDTO> resSongs = new ArrayList<>();
        for (SongDTO song : tmpSongs) {
            
            if(removeDiacritics(song.getTitle().toLowerCase())
               .contains(removeDiacritics(phrase).toLowerCase())){
                resSongs.add(song);
            }
        }
        return resSongs;
    }
    
    public List<AlbumDTO> getAlbumsByTitleSub(String subword){
        List<AlbumDTO> tmpAlbums = albumService.getAllAlbums();
        List<AlbumDTO> resAlbums = new ArrayList<>();
        for (AlbumDTO album : tmpAlbums) {
            if(removeDiacritics(album.getTitle().toLowerCase())
               .contains(removeDiacritics(phrase).toLowerCase())){
                resAlbums.add(album);
            }
        }
        return resAlbums;
    }
    
    public List<ArtistDTO> getArtistsByNameSub(String subword){
        List<ArtistDTO> tmpArtists = artistService.getAll();
        List<ArtistDTO> resArtists = new ArrayList<>();
        for (ArtistDTO album : tmpArtists) {
            if(removeDiacritics(album.getName().toLowerCase())
               .contains(removeDiacritics(phrase).toLowerCase())){
                resArtists.add(album);
            }
        }
        return resArtists;
    }
    
    public List<PlaylistDTO> getPlaylistsByNameSub(String subword){
        List<PlaylistDTO> tmpPlaylists = playlistService.getAll();
        List<PlaylistDTO> resPlaylists = new ArrayList<>();
        for (PlaylistDTO playlist : tmpPlaylists) {
            if(removeDiacritics(playlist.getName().toLowerCase())
               .contains(removeDiacritics(phrase).toLowerCase())){
                resPlaylists.add(playlist);
            }
        }
        return resPlaylists;
    }
}
