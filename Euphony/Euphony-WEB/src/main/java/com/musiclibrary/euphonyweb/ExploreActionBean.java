package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.ArtistDTO;
import com.musiclibrary.euphonyapi.dto.GenreDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.facade.MusicFacade;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonyapi.services.GenreService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
import java.util.List;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

/**
 * Explore action bean for showing songs, albums and artists in index.
 * 
 * @author Tomas Smetanka #396209
 */
@UrlBinding("/explore/{$event}")
public class ExploreActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected MusicFacade facade;
    @SpringBean
    protected SongService songService;
    protected GenreService genreService;
    @SpringBean
    protected AlbumService albumService;
    @SpringBean
    protected ArtistService artistService;
    @SpringBean
    protected PlaylistService playlistService;
    private GenreDTO genre;
    private SongDTO song;
    private AlbumDTO album;
    private ArtistDTO artist;
    private PlaylistDTO playlist;
    private List<GenreDTO> genres;
    private List<SongDTO> songs;
    private List<AlbumDTO> albums;
    private List<ArtistDTO> artists;
    private List<PlaylistDTO> playlists;
    
//    @Validate(required = true, on = {"song2playlist"})
//    private List<SongDTO> selectedSongs;
//
//    public List<SongDTO> getSelectedSongs() {
//        return selectedSongs;
//    }
//
//    public void setSelectedSongs(List<SongDTO> selectedSongs) {
//        this.selectedSongs = selectedSongs;
//    }

//    @Validate(required = true, on="song2playlist")
//    private PlaylistDTO selectedPlaylist;
//
//    public PlaylistDTO getSelectedPlaylist() {
//        return selectedPlaylist;
//    }
//
//    public void setSelectedPlaylist(PlaylistDTO selectedPlaylist) {
//        this.selectedPlaylist = selectedPlaylist;
//    }
    
    
    @DefaultHandler
    public Resolution songs() {
        //log.debug("list()");
        songs = songService.getAll();
        playlists = playlistService.getAll();
        return new ForwardResolution("/explore/songs.jsp");
    }

//    public Resolution song2playlist() {
//        for (SongDTO selectedSong : selectedSongs) {
//            facade.addSongToPlaylist(selectedSong, playlistService.getById(new Long(2)));
//        }
//        return new ForwardResolution("/explore/songs.jsp");
//    }

    public Resolution albums() {
        //log.debug("list()");
        albums = albumService.getAllAlbums();
        playlists = playlistService.getAll();
        return new ForwardResolution("/explore/albums.jsp");
    }

    public Resolution artists() {
        //log.debug("list()");
        artists = artistService.getAll();
        playlists = playlistService.getAll();
        return new ForwardResolution("/explore/artists.jsp");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        songs = songService.getAll();
        return null;
    }

//    @Before(stages = LifecycleStage.BindingAndValidation, on = {"song2playlist"})
//    public void loadFromDatabase() {
//        String idPlaylist = getContext().getRequest().getParameter("playlist.id");
//        String idSong = getContext().getRequest().getParameter("song.id");
//        if (idPlaylist == null || idSong == null) {
//            return;
//        }
//        playlist = playlistService.getById(Long.parseLong(idPlaylist));
//        song = songService.getById(Long.parseLong(idSong));
//    }

    //--- getters and setters ----
    public List<GenreDTO> getGenres() {
        return genres;
    }

    public List<AlbumDTO> getAlbums() {
        return albums;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public List<SongDTO> getSongs() {
        return songs;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public SongDTO getSong() {
        return song;
    }

    public void setSong(SongDTO song) {
        this.song = song;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtis(ArtistDTO artist) {
        this.artist = artist;
    }

    public PlaylistDTO getPlaylist() {
        return playlist;
    }

    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }
}
