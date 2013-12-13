/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.musiclibrary.euphonyweb;

import com.musiclibrary.euphonyapi.dto.AlbumDTO;
import com.musiclibrary.euphonyapi.dto.PlaylistDTO;
import com.musiclibrary.euphonyapi.dto.SongDTO;
import com.musiclibrary.euphonyapi.services.AlbumService;
import com.musiclibrary.euphonyapi.services.ArtistService;
import com.musiclibrary.euphonyapi.services.GenreService;
import com.musiclibrary.euphonyapi.services.PlaylistService;
import com.musiclibrary.euphonyapi.services.SongService;
import java.io.File;
import java.io.IOException;
import java.util.List;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;
import net.sourceforge.stripes.validation.ValidationState;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Sebastian Lazon
 */
@UrlBinding("/albums/{$event}/{album.id}")
public class AlbumActionBean extends BaseActionBean implements ValidationErrorHandler {

    @SpringBean
    protected AlbumService albumService;
    @SpringBean
    protected PlaylistService playlistService;
    @SpringBean
    protected SongService songService;
    @SpringBean
    protected ArtistService artistService;
    @SpringBean
    protected GenreService genreService;
    private List<PlaylistDTO> playlists;
    private List<SongDTO> songs;
    private PlaylistDTO playlist;

    public void setSongs(List<SongDTO> songs) {
        this.songs = songs;
    }

    public List<SongDTO> getSongs() {
        return songs;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public PlaylistDTO getPlaylist() {
        return playlist;
    }
    private FileBean cover;

    public FileBean getCover() {
        return cover;
    }

    public void setCover(FileBean cover) {
        this.cover = cover;
    }

    private void handleFileUpload() throws IOException {
        if (cover == null) {
            return;
        }
        String url = getContext().getServletContext().getRealPath("/upload/" + cover.getFileName());
        File file = new File(url);
        cover.save(file);
        album.setCover(cover.getFileName());
    }

    private void handleFileRemoval() {
        if (album.getCover() != null) {
            String url = getContext().getServletContext().getRealPath("/upload/" + album.getCover());
            File file = new File(url);
            file.delete();
        }
    }

    public void setPlaylist(PlaylistDTO playlist) {
        this.playlist = playlist;
    }
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
        playlists = playlistService.getAll();
        return new ForwardResolution("/album/list.jsp");
    }

    public Resolution deleted() {
        albums = albumService.getAllAlbums();
        playlists = playlistService.getAll();
        return new ForwardResolution("/album/list.jsp");
    }

    public List<AlbumDTO> getAlbums() {
        return albums;
    }

    public Resolution add() throws IOException {
        album.setReleaseDate(new DateTime(Integer.parseInt(releaseDate.substring(6)), Integer.parseInt(releaseDate.substring(3, 5)), Integer.parseInt(releaseDate.substring(0, 2)), 0, 0));
        handleFileUpload();
        albumService.create(album);
        return new RedirectResolution(this.getClass(), "list");
    }

    @Override
    public Resolution handleValidationErrors(ValidationErrors errors) throws Exception {
        albums = albumService.getAllAlbums();
        playlists = playlistService.getAll();
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

    public Resolution delete() throws Exception {
        album = albumService.getById(album.getId());
        handleFileRemoval();
        try {
            albumService.delete(album);
        } catch (DataAccessException ex) {
        }
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution deleteCover() {
        if (album.getCover() != null) {
            String url = getContext().getServletContext().getRealPath("/upload/" + album.getCover());
            album.setCover(null);
        }
        return new ForwardResolution("/album/edit.jsp");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save", "deleteCover", "details"})
    public void loadAlbumFromDatabase() {
        String ids = getContext().getRequest().getParameter("album.id");
        if (ids == null) {
            return;
        }
        album = albumService.getById(Long.parseLong(ids));
    }

    public Resolution edit() {
        albums = albumService.getAllAlbums();
        playlists = playlistService.getAll();
        return new ForwardResolution("/album/edit.jsp");
    }

    public Resolution save() throws IOException {
        album.setReleaseDate(new DateTime(Integer.parseInt(releaseDate.substring(6)), Integer.parseInt(releaseDate.substring(3, 5)), Integer.parseInt(releaseDate.substring(0, 2)), 0, 0));
        if (cover != null && !cover.getFileName().equals(album.getCover())) {
            handleFileRemoval();
        }
        handleFileUpload();
        albumService.update(album);
        return new RedirectResolution(this.getClass(), "list");
    }

    public Resolution cancel() {
        return new RedirectResolution(this.getClass(), "list");
    }

    @ValidationMethod(when = ValidationState.ALWAYS, on = {"add", "save"})
    public void validateCover(ValidationErrors errors) {
        if (cover != null) {
            if (!(cover.getContentType().equals("image/jpeg") || cover.getContentType().equals("image/png") || cover.getContentType().equals("image/gif"))) {
                errors.add("cover", new LocalizableError("validation.file"));
            }
            String url = getContext().getServletContext().getRealPath("/upload/" + cover.getFileName());
            File file = new File(url);
            if (file.exists()) {
                errors.add("cover", new LocalizableError("validation.fileexists"));
            }
        }
    }
}
