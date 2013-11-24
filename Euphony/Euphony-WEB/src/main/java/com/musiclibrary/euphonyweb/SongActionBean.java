package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonyapi.services.GenreService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
import java.util.List;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

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
    
    @ValidateNestedProperties(value = {
            @Validate(on = {"add", "save"}, field = "title", required = true),
            @Validate(on = {"add", "save"}, field = "bitrate", required = true, minvalue = 1, maxvalue=2500),
            @Validate(on = {"add", "save"}, field = "trackNumber", required = true, minvalue = 1)
    })
    private SongDTO song;
    
    @Validate(on = {"add", "save"}, required = true)
    private Long album;
    
    @Validate(on = {"add", "save"}, required = true)
    private Long genre;
    
    @Validate(on = {"add", "save"}, required = true)
    private Long artist;

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
    
    @DefaultHandler
    public Resolution list() {
        songs = songService.getAll();
        albums = albumService.getAllAlbums();
        genres = genreService.getAll();
        artists = artistService.getAll();
        playlists = playlistService.getAll();
        return new ForwardResolution("/song/list.jsp");
    }

    public List<SongDTO> getSongs() {
        return songs;
    }
    
    public List<GenreDTO> getGenres() {
        return genres;
    }
    
    public List<AlbumDTO> getAlbums() {
        return albums;
    }
    
    public List<ArtistDTO> getArtists() {
        return artists;
    }
    
    public void setAlbum(long album){
        this.album=album;
    }
    
    public void setArtist(long artist){
        this.artist=artist;
    }
    
    public void setGenre(long genre){
        this.genre=genre;
    }

    public Resolution add() {
        song.setAlbum(albumService.getById(album));
        song.setArtist(artistService.getById(artist));
        song.setGenre(genreService.getById(genre));
        
        songService.create(song);
        //getContext().getMessages().add(new LocalizableMessage("book.add.message",escapeHTML(book.getTitle()),escapeHTML(book.getAuthor())));
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        //fill up the data for the table if validation errors occured
        //return null to let the event handling continue
        songs = songService.getAll();
        albums = albumService.getAllAlbums();
        genres = genreService.getAll();
        artists = artistService.getAll();
        playlists = playlistService.getAll();
        return null;
    }

    public SongDTO getSong() {
        return song;
    }

    public void setSong(SongDTO song) {
        this.song = song;
    }

    public Resolution delete() {
        song = songService.getById(song.getId());
        songService.delete(song);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadSongFromDatabase() {
        String ids = getContext().getRequest().getParameter("song.id");
        if (ids == null) return;
        song = songService.getById(Long.parseLong(ids));
    }

    public Resolution edit() {
        albums = albumService.getAllAlbums();
        genres = genreService.getAll();
        artists = artistService.getAll();
        return new ForwardResolution("/song/edit.jsp");
    }

    public Resolution save() {
        song.setAlbum(albumService.getById(album));
        song.setArtist(artistService.getById(artist));
        song.setGenre(genreService.getById(genre));
        songService.update(song);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution cancel() {
//        log.debug("cancel() artist={}", artist);
        return new RedirectResolution(this.getClass(), "list");
    }
}
