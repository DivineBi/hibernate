package fr.ensitech.model;

import fr.ensitech.entity.User;
import fr.ensitech.utils.Dates;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;
public class UserDaoTest {

    private final IUserDao userDao = new UserDao();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void createUser() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserNegatif() throws Exception {
        User _user = userDao.getUser(-5);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserZero() throws Exception {
        User _user = userDao.getUser(0);
    }

    @Test
    public void testGetUserPositif() throws Exception {
        User _user = userDao.getUser(4);
        assertEquals("BLAISE", _user.getNom());
        assertEquals("Pascal", _user.getPrenom());
        assertEquals("blaise@gmail.com", _user.getEmail());
        assertEquals(Dates.convertStringToDate("23/04/1940"), _user.getDateNaissance());


    }

    @Test
    public void testGetAllUsersTotal() throws Exception {
        List<User> _users = userDao.getAllUsers();
        assertNotNull(_users);
        assertEquals(2, _users.size());
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> _users = userDao.getAllUsers();

    }



}