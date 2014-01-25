package com.musiclibrary.euphonybusinesslogicimplementation.util;

import com.musiclibrary.euphonybusinesslogicimplementation.entities.Account;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import java.text.Normalizer;

/**
 *
 * @author Sebastian
 */
public class Util {

    public static void validateAlbum(Album album) {
        if (album == null) {
            throw new IllegalArgumentException("Album cannot be null.") {
            };
        }
        if (album.getReleaseDate() == null) {
            throw new IllegalArgumentException("Album's release date is null.") {
            };
        }
        if ("".equals(album.getTitle())) {
            throw new IllegalArgumentException("Album's title is empty.") {
            };
        }
        if (album.getTitle() == null) {
            throw new IllegalArgumentException("Album's title is null.") {
            };
        }
    }

    public static void validateArtist(Artist artist) {
        if (artist == null) {
            throw new IllegalArgumentException("Artist cannot be null.") {
            };
        }
        if (artist.getName() == null) {
            throw new IllegalArgumentException("Artist's name is null.") {
            };
        }
        if ("".equals(artist.getName())) {
            throw new IllegalArgumentException("Artist's name is empty.") {
            };
        }
    }

    public static void validateGenre(Genre genre) {
        if (genre == null) {
            throw new IllegalArgumentException("Genre cannot be null.") {
            };
        }
        if (genre.getName() == null) {
            throw new IllegalArgumentException("Genre's name is null.") {
            };
        }
        if ("".equals(genre.getName())) {
            throw new IllegalArgumentException("Genre's name is empty.") {
            };
        }
    }

    public static void validatePlaylist(Playlist playlist) {
        if (playlist == null) {
            throw new IllegalArgumentException("Playlist cannot be null.") {
            };
        }
        if (playlist.getName() == null) {
            throw new IllegalArgumentException("Playlist's name is null.") {
            };
        }
        if ("".equals(playlist.getName())) {
            throw new IllegalArgumentException("Playlist's name is empty.") {
            };
        }
    }

    public static void validateSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null.") {
            };
        }
        if (song.getTitle() == null) {
            throw new IllegalArgumentException("Song's title cannot be null.") {
            };
        }
        if ("".equals(song.getTitle())) {
            throw new IllegalArgumentException("Song's title cannot be empty.") {
            };
        }

        if (song.getGenre() == null) {
            throw new IllegalArgumentException("Song's genre cannot be null.") {
            };
        }
        if (song.getAlbum() == null) {
            throw new IllegalArgumentException("Song's album cannot be null.") {
            };
        }
        if (song.getArtist() == null) {
            throw new IllegalArgumentException("Song's artist cannot be null.") {
            };
        }

        if (song.getBitrate() < 1 || song.getBitrate() > 2500) {
            throw new IllegalArgumentException("Song's bitrate must be between 1 and 2500.") {
            };
        }
        if (song.getTrackNumber() <= 0) {
            throw new IllegalArgumentException("Song's track number must be bigger than 0.") {
            };
        }
    }
    
public static void validateAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Playlist cannot be null.") {
            };
        }
        if (account.getUsername()== null) {
            throw new IllegalArgumentException("Account's username is null.") {
            };
        }
        if (account.getIsAdmin()== null) {
            throw new IllegalArgumentException("Account's getIsAdmin is null.") {
            };
        }
        if ("".equals(account.getUsername())) {
            throw new IllegalArgumentException("Account's username is empty.") {
            };
        }
        if (account.getPassword()== null) {
            throw new IllegalArgumentException("Account's passwoed is null.") {
            };
        }
        if ("".equals(account.getPassword())) {
            throw new IllegalArgumentException("Account's passwoed is empty.") {
            };
        }
        if (account.getPlaylists()== null) {
            throw new IllegalArgumentException("Account's playlists is null.") {
            };
        }
    }

    public static String removeDiacritics(String str) {
        if (str == null) {
            return "";
        }
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
