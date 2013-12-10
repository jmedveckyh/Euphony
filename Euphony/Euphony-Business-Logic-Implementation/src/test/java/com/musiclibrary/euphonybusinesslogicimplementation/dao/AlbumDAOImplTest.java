package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.ArtistDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.GenreDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.SongDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import org.springframework.dao.DataAccessException;

/**
 * Unit tests for Album DAO implementation.
 *
 * @author Sebastian Lazon #395990
 */
public class AlbumDAOImplTest extends TestCase {

    private EntityManagerFactory emf;
    private EntityManager em;
    private AlbumDAOImpl albumDao;
    private SongDAOImpl songDao;
    private ArtistDAOImpl artistDao;
    private GenreDAOImpl genreDao;

    public AlbumDAOImplTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        songDao = new SongDAOImpl(em);
        artistDao = new ArtistDAOImpl(em);
        genreDao = new GenreDAOImpl(em);
        albumDao = new AlbumDAOImpl(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Tests of creating new album
     */
    public void testCreateAlbum() {

        em.getTransaction().begin();
        try {
            albumDao.create(null);
            fail("Null album create");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Album album = null;
        try {
            albumDao.create(album);
            fail("Empty album create");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album(null, "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album title null");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album("", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album title empty");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album("title", "cover.jpg", null, new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album date null");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album("title", "cover.jpg", DateTime.now(), null, "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album songs null");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", null, new ArrayList<Genre>());
        try {
            albumDao.create(album);
            fail("Album artists null");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), null);
        try {
            albumDao.create(album);
            fail("Album genres null");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        albumDao.create(album);
        em.getTransaction().commit();
        em.clear();
        Long id = album.getId();
        assertNotNull(id);
        assertDeepEquals(album, albumDao.getById(id));


        em.getTransaction().begin();
        try {
            albumDao.create(album);
            fail("Album already in db");
        } catch (DataAccessException e) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();
    }

    /**
     * Tests of getting an album
     */
    public void testGetAlbum() {

        em.getTransaction().begin();
        try {
            albumDao.getById(null);
            fail("null id");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        Album album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());

        em.getTransaction().begin();
        try {
            albumDao.getById(album.getId());
            fail("id not in db");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        assertDeepEquals(album, albumDao.getById(album.getId()));
        em.clear();

    }

    /**
     * Tests of updating album
     */
    public void testUpdateAlbum() {
        em.getTransaction().begin();
        try {
            albumDao.update(null);
            fail("null album update");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        try {
            albumDao.update(new Album());
            fail("empty album update");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Album album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.update(album);
            fail("Item not in db");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();

        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        album.setTitle("title2");
        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        album.setCover("cover2.jpg");
        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        album.setReleaseDate(DateTime.now());
        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

        album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        album.setComment("comment2");
        em.getTransaction().begin();
        albumDao.update(album);
        em.getTransaction().commit();
        em.clear();
        assertDeepEquals(album, albumDao.getById(album.getId()));

    }

    /*
     * Testing of deleting album
     */
    public void testDeleteAlbum() {
        em.getTransaction().begin();
        try {
            albumDao.delete(null);
            fail("deleting null album");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Album album = new Album("title", "cover.jpg", DateTime.now(), new ArrayList<Song>(), "comment", new ArrayList<Artist>(), new ArrayList<Genre>());
        try {
            albumDao.delete(album);
            fail("album not in db");
        } catch (DataAccessException ex) {
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        albumDao.create(album);
        em.getTransaction().commit();
        Long id = album.getId();
        em.getTransaction().begin();
        albumDao.delete(album);
        em.getTransaction().commit();
        em.clear();
        assertNull(albumDao.getById(id));
    }

    /*
     * Testing of getting all albums
     */
    public void testGetAllAlbums() {
        assertTrue(albumDao.getAll().isEmpty());

        Album album1 = new Album("title1", "cover1.jpg", DateTime.now(), new ArrayList<Song>(), "comment1", new ArrayList<Artist>(), new ArrayList<Genre>());
        Album album2 = new Album("title2", "cover2.jpg", DateTime.now(), new ArrayList<Song>(), "comment2", new ArrayList<Artist>(), new ArrayList<Genre>());

        List<Album> albums = new ArrayList();
        albums.add(album1);
        albums.add(album2);
        em.getTransaction().begin();
        albumDao.create(album1);
        albumDao.create(album2);
        em.getTransaction().commit();
        assertEquals(albums, albumDao.getAll());
        em.clear();
    }

    /*
     * Get with null name
     */
    public void testGetByTitleWithNullName() {
        em.getTransaction().begin();
        try {
            albumDao.getByTitle(null);              //title is null
        } catch (DataAccessException e) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();
    }

    public void testGetArtistByNameWithNotAssignedName() {
        em.getTransaction().begin();
        Album nullResult = albumDao.getByTitle("Mirage");              //getAlbumByName with not assigned name, should return null
        em.getTransaction().commit();
        em.clear();
        assertNull(nullResult);
    }

    public void testGetAlbumByTitle() {

        em.getTransaction().begin();
        Artist tiesto = new Artist("Tiesto");
        artistDao.create(tiesto);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Album expResult = new Album("Kaleidoscope", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(tiesto);
        albumDao.create(expResult);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());

        Album result = albumDao.getByTitle(expResult.getTitle());              //correct
        assertDeepEquals(expResult, result);
    }

    public void testGetAlbumByGenre() {

        em.getTransaction().begin();
        Artist tiesto = new Artist("Tiesto");
        Artist armin = new Artist("Armin Van Buuren");
        Genre tranceGen = new Genre("Trance");
        Genre technoGen = new Genre("Techno");
        Genre houseGen = new Genre("House");
        artistDao.create(tiesto);
        artistDao.create(armin);
        genreDao.create(technoGen);
        genreDao.create(tranceGen);
        genreDao.create(houseGen);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Album expResult = new Album("Kaleidoscope", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(tiesto);
        expResult.getGenres().add(tranceGen);
        expResult.getGenres().add(houseGen);
        expResult.getGenres().add(technoGen);
        albumDao.create(expResult);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Album expResult2 = new Album("Mirage", "cover1.jpg", new DateTime(2010, 1, 1, 0, 0), new ArrayList<Song>(), "hehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult2.getArtists().add(armin);
        expResult2.getGenres().add(tranceGen);
        expResult2.getGenres().add(houseGen);
        albumDao.create(expResult2);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());
        assertNotNull(expResult2.getId());

        List<Album> resultList = albumDao.getByGenre(technoGen);
        assertEquals(1, resultList.size());
        List<Album> technoList = new ArrayList<Album>();
        technoList.add(expResult);
        assertEquals(technoList, resultList);

        List<Album> resultList2 = albumDao.getByGenre(houseGen);
        List<Album> houseList = new ArrayList<Album>();
        houseList.add(expResult);
        houseList.add(expResult2);
        assertEquals(2, resultList2.size());
        assertEquals(houseList, resultList2);
    }

    public void testGetAlbumByArtist() {
        em.getTransaction().begin();
        Album expResult = new Album("Spaceman", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        Artist tiesto = new Artist("Tiesto");
        Artist hardwell = new Artist("Hardwell");
        Artist dyro = new Artist("Dyro");

        artistDao.create(tiesto);
        artistDao.create(hardwell);
        artistDao.create(dyro);
        em.getTransaction().commit();

        em.getTransaction().begin();
        expResult.getArtists().add(tiesto);
        expResult.getArtists().add(hardwell);
        expResult.getArtists().add(dyro);

        albumDao.create(expResult);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Album expResult2 = new Album("Imagine", "cover1.jpg", new DateTime(2010, 1, 1, 0, 0), new ArrayList<Song>(), "hehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult2.getArtists().add(dyro);
        expResult2.getArtists().add(hardwell);
        albumDao.create(expResult2);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());
        assertNotNull(expResult2.getId());

        List<Album> resultList = albumDao.getByArtist(tiesto);
        assertEquals(1, resultList.size());
        List<Album> tiestosList = new ArrayList<Album>();
        tiestosList.add(expResult);
        assertEquals(tiestosList, resultList);

        List<Album> resultList2 = albumDao.getByArtist(hardwell);
        List<Album> hardwellList = new ArrayList<Album>();
        hardwellList.add(expResult);
        hardwellList.add(expResult2);
        assertEquals(2, resultList2.size());
        assertEquals(hardwellList, resultList2);   
    }

    public void testGetAlbumByReleaseYear() {
        em.getTransaction().begin();
        Album expResult = new Album("Club Life", "cover.jpg", new DateTime(2011, 10, 1, 0, 0), new ArrayList<Song>(), "nehehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        Artist tiesto = new Artist("Tiesto");
        artistDao.create(tiesto);
        expResult.getArtists().add(tiesto);
        em.getTransaction().commit();

        em.getTransaction().begin();
        albumDao.create(expResult);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Album expResult2 = new Album("Club Life Alfa", "cover1.jpg", new DateTime(2011, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(tiesto);
        albumDao.create(expResult2);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Album expResult3 = new Album("Club Life Final Release", "cover.jpg", new DateTime(2011, 12, 31, 23, 59), new ArrayList<Song>(), "nehehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(tiesto);
        albumDao.create(expResult3);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Album expResult4 = new Album("Club Life Final Release 2010", "cover.jpg", new DateTime(2010, 12, 31, 23, 59), new ArrayList<Song>(), "nehehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(tiesto);
        albumDao.create(expResult4);
        em.getTransaction().commit();

        em.getTransaction().begin();
        Album expResult5 = new Album("Club Life Alfa 2012", "cover.jpg", new DateTime(2012, 1, 1, 0, 0), new ArrayList<Song>(), "nehehe",
                new ArrayList<Artist>(), new ArrayList<Genre>());
        expResult.getArtists().add(tiesto);
        albumDao.create(expResult5);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(expResult.getId());
        assertNotNull(expResult2.getId());
        assertNotNull(expResult3.getId());
        assertNotNull(expResult4.getId());
        assertNotNull(expResult5.getId());

        List<Album> resultList = albumDao.getByReleaseYear(new Integer(2011));
        assertEquals(3, resultList.size());
        List<Album> twoOelevenList = new ArrayList<Album>();
        twoOelevenList.add(expResult);
        twoOelevenList.add(expResult2);
        twoOelevenList.add(expResult3);

        assertEquals(twoOelevenList, resultList);

        List<Album> resultList2 = albumDao.getByReleaseYear(new Integer(2012));
        List<Album> twoOtwelveList = new ArrayList<Album>();
        twoOtwelveList.add(expResult5);
        assertEquals(twoOtwelveList, resultList2);
    }
    
    private void assertDeepEquals(Album a1, Album a2) {
        assertEquals(a1.getId(), a2.getId());
        assertDeepEquals(a1.getArtists(), a2.getArtists());
        assertEquals(a1.getComment(), a2.getComment());
        assertEquals(a1.getCover(), a2.getCover());
        assertDeepEquals(a1.getGenres(), a2.getGenres());
        assertEquals(a1.getReleaseDate(), a2.getReleaseDate());
        assertDeepEquals(a1.getSongs(), a2.getSongs());
        assertEquals(a1.getTitle(), a2.getTitle());
    }

    private <T> void assertDeepEquals(List<T> expList, List<T> actList) {
        if (expList.size() != actList.size()) {
            fail();
        }
        for (int i = 0; i < expList.size(); i++) {
            T exp = expList.get(i);
            T act = actList.get(i);
            if (exp instanceof Genre && act instanceof Genre) {
                Genre expected = (Genre) exp;
                Genre actual = (Genre) act;
                GenreDAOImplTest.assertDeepEquals(expected, actual);
            }
            if (exp instanceof Artist && act instanceof Artist) {
                Artist expected = (Artist) exp;
                Artist actual = (Artist) act;
                ArtistDAOImplTest.assertDeepEquals(expected, actual);
            }
            if (exp instanceof Song && act instanceof Song) {
                Song expected = (Song) exp;
                Song actual = (Song) act;
                SongDAOImplTest.assertDeepEquals(expected, actual);
            }
        }
    }
}
