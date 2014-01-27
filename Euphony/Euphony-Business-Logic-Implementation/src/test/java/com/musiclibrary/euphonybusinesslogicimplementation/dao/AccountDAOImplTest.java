package com.musiclibrary.euphonybusinesslogicimplementation.dao;

import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.AccountDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.AlbumDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.ArtistDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.dao.impl.GenreDAOImpl;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Account;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Album;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Artist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Genre;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Playlist;
import com.musiclibrary.euphonybusinesslogicimplementation.entities.Song;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import junit.framework.TestCase;
import org.joda.time.DateTime;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Brano
 */
public class AccountDAOImplTest extends TestCase {

    private EntityManagerFactory emf;
    private EntityManager em;
    private AccountDAOImpl accountDAOImpl;
    private ArtistDAOImpl artistDao;
    private GenreDAOImpl genreDao;
    private AlbumDAOImpl albumDao;

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("testEuphonyPU");
        em = emf.createEntityManager();
        accountDAOImpl = new AccountDAOImpl(em);
        artistDao = new ArtistDAOImpl(em);
        genreDao = new GenreDAOImpl(em);
        albumDao = new AlbumDAOImpl(em);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateAccount() {
        
        //test create null account
        try {
            em.getTransaction().begin();
            accountDAOImpl.create(null);
            fail("Null account");
        } catch (DataAccessException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        //test account with not null id
        em.getTransaction().begin();
        try {
            Account acc = new Account("Pato", "abc123xx", Boolean.FALSE, new ArrayList<Playlist>());
            acc.setId(new Long(1l));
            accountDAOImpl.create(acc);
            fail("account id set!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test Create account With Null Attributes
        em.getTransaction().begin();
        try {
            Account acc = new Account(null, null, null, null);
            accountDAOImpl.create(acc);
            fail("account with null attributes!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null username
        em.getTransaction().begin();
        try {
            Account acc = new Account(null, "abc123xx", Boolean.FALSE, new ArrayList<Playlist>());
            accountDAOImpl.create(acc);
            fail("account with null username!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null password
        em.getTransaction().begin();
        try {
            Account acc = new Account("Pato", null, Boolean.FALSE, new ArrayList<Playlist>());
            accountDAOImpl.create(acc);
            fail("account with null password!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null isAdmin attribute
        em.getTransaction().begin();
        try {
            Account acc = new Account("Pato", "abc123xx", null, new ArrayList<Playlist>());
            accountDAOImpl.create(acc);
            fail("account with null isAdmin attribute!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test with null playlists 
        em.getTransaction().begin();
        try {
            Account acc = new Account("Pato", "abc123xx", Boolean.FALSE, null);
            accountDAOImpl.create(acc);
            fail("account with null playlists!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        Map<Integer, Song> songs = new TreeMap<>();
        Genre genreTemp = new Genre("Pop");
        Album albumTemp = new Album("The Fox", "cover.jpg", new DateTime(2009, 1, 1, 0, 0), "nehehe");
        Artist artistTemp = new Artist("Ylvis");

        em.getTransaction().begin();
        genreDao.create(genreTemp);
        albumDao.create(albumTemp);
        artistDao.create(artistTemp);
        em.getTransaction().commit();

        songs.put(1, new Song("The Fox", 180, 1, "", genreTemp, albumTemp, artistTemp));
        songs.put(2, new Song("Someone Like Me", 180, 2, "", genreTemp, albumTemp, artistTemp));
        songs.put(3, new Song("Stonehenge", 180, 3, "", genreTemp, albumTemp, artistTemp));

        Playlist playlist = new Playlist("Myfirst", songs);
        List<Playlist> pList = new ArrayList<>();
        pList.add(playlist);

        em.getTransaction().begin();
        Account account = new Account("Pato", "abc123xx", Boolean.FALSE, pList);
        accountDAOImpl.create(account);
        em.getTransaction().commit();

        Long id = account.getId();
        assertNotNull(id);
        Account account2 = accountDAOImpl.getById(id);
        assertDeepEquals(account, account2);
    }

    public void testUpdateAccount() {

        //test null account
        em.getTransaction().begin();
        try {
            accountDAOImpl.update(null);
            fail("null account update!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test account with null args
        em.getTransaction().begin();
        try {
            accountDAOImpl.update(new Account(null, null, null, null));
            fail("account with null args update!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test account with null username
        em.getTransaction().begin();
        try {
            accountDAOImpl.update(new Account(null, "abc123xx", Boolean.FALSE, new ArrayList<Playlist>()));
            fail("account with null username update!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test account with null password
        em.getTransaction().begin();
        try {
            accountDAOImpl.update(new Account("Al", null, Boolean.FALSE, new ArrayList<Playlist>()));
            fail("account with null password update!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test account with null isAdmin
        em.getTransaction().begin();
        try {
            accountDAOImpl.update(new Account("Al", "abc123xx", null, new ArrayList<Playlist>()));
            fail("account with null isAdmin update!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test account with null playlists
        em.getTransaction().begin();
        try {
            accountDAOImpl.update(new Account(null, "abc123xx", Boolean.FALSE, null));
            fail("account with null playlists update!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test account with null id
        em.getTransaction().begin();
        try {
            accountDAOImpl.update(new Account("Pato", "abc123xx", Boolean.FALSE, new ArrayList<Playlist>()));
            em.getTransaction().commit();
            fail("account with null id update!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        //test account with id not assigned by database
        em.getTransaction().begin();
        try {
            Account acc = new Account("Pato", "abc123xx", Boolean.FALSE, new ArrayList<Playlist>());
            acc.setId(new Long(1000));
            accountDAOImpl.update(acc);
            fail("account with assigned id update!");
        } catch (DataAccessException ex) {
            //OK
        }
        em.getTransaction().commit();
        em.clear();

        em.getTransaction().begin();
        Account acc = new Account("Pato", "abc123xx", Boolean.FALSE, new ArrayList<Playlist>());
        accountDAOImpl.create(acc);
        em.getTransaction().commit();
        em.clear();
        acc.setUsername("Brano");
        em.getTransaction().begin();
        accountDAOImpl.update(acc);
        em.getTransaction().commit();
        em.clear();
        Account acc2 = accountDAOImpl.getById(acc.getId());
        assertDeepEquals(acc, acc2);



    }

    public void testDeleteAccount() {
        em.getTransaction().begin();
        Account acc = new Account("Pato", "abc123xx", Boolean.FALSE, new ArrayList<Playlist>());
        accountDAOImpl.create(acc);
        em.getTransaction().commit();
        em.clear();

        Account acc2 = new Account("Pato", "abc123xx", Boolean.FALSE, new ArrayList<Playlist>());
        Long id = acc.getId();
        assertNotNull(id);

        acc2.setId(id);

        em.getTransaction().begin();
        accountDAOImpl.delete(acc2);
        em.getTransaction().commit();
        em.clear();

        if (accountDAOImpl.getById(id) != null) {
            fail("Delete failed.");
        }

    }

    public void testGetAccount() {

        //test with null id
        em.getTransaction().begin();
        try {
            accountDAOImpl.getById(null);
            fail("id null get!");
        } catch (DataAccessException ex) {
            //ok
        }
        em.getTransaction().commit();
        em.clear();

        //test id not assigned by db
        em.getTransaction().begin();
        Account acc0 = accountDAOImpl.getById(new Long(6543));
        em.getTransaction().commit();
        assertNull(acc0);
        
        //test get by id
        em.getTransaction().begin();
        Account acc = new Account("Pato", "abc123xx", Boolean.FALSE, new ArrayList<Playlist>());
        accountDAOImpl.create(acc);
        em.getTransaction().commit();
        em.clear();

        assertNotNull(acc.getId());
        Long accId = acc.getId();
        
        em.getTransaction().begin();
        Account res = accountDAOImpl.getById(accId);
        em.getTransaction().commit();
        em.clear();

        assertDeepEquals(res, acc);
        
        //test get by username
        em.getTransaction().begin();
        Account acc2 = new Account("Brano", "abc123xx", Boolean.FALSE, new ArrayList<Playlist>());
        accountDAOImpl.create(acc2);
        em.getTransaction().commit();
        em.clear();
        
        em.getTransaction().begin();
        Account acc3 = new Account("Ivan", "78945600", Boolean.FALSE, new ArrayList<Playlist>());
        accountDAOImpl.create(acc3);
        em.getTransaction().commit();
        em.clear();
        
        Account res1 = accountDAOImpl.getByUsername("Brano");
        assertDeepEquals(res1, acc2);
    }

    public static void assertDeepEquals(Account expected, Account actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getIsAdmin(), actual.getIsAdmin());
    }
}
