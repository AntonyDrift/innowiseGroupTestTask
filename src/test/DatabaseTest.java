import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class DatabaseTest {

    private static Database database;
    private static User anton;
    private static User evgenya;
    private static User testUser;
    private static Logger logger;

    @BeforeClass
    public static void setUp() {
        database = new Database();
        logger = Logger.getLogger(DatabaseTest.class);
    }

    @Before
    public void initUsers() {
        anton = new User("Anton", "Zhurkov",
                "antonydrift96@hotmail.com", "+375257585677", new String[]{"admin, user"});
        evgenya = new User("Evgenya", "Kolobkova",
                "kolobkova_evgenya@yandex.ru", "+375447915891", new String[]{"user"});
        testUser = new User("testName", "testSurname",
                "test@mail.com", "+375172635443", new String[]{"guest"});
        database.open("/home/antonydrift/IdeaProjects/innowiseGroupTestTask/src/resource/users.dat");
    }

    @Test
    public void getDMap() {


        Map<Integer, User> expected = database.getDbMap();
        Map<Integer, User> actual = new HashMap<>();
        //true result
        actual.put(0, anton);
        actual.put(1, evgenya);
        //false result
//        actual.put(1, evgenya);
//        actual.put(2, anton);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void add() {

        Map<Integer, User> expected = database.getDbMap();
        Map<Integer, User> actual = new HashMap<>();

        database.add(testUser);
        //true result
        actual.put(2, testUser);
        actual.put(0, anton);
        actual.put(1, evgenya);
        //false result
//        actual.put(1, testUser);
//        actual.put(2, anton);
//        actual.put(3, evgenya);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void edit() {

        Map<Integer, User> expected = database.getDbMap();
        Map<Integer, User> actual = new HashMap<>();

        database.edit(0, testUser);
        //true result
        actual.put(0, testUser);
        actual.put(1, evgenya);
        //false result
//        actual.put(1, anton);
//        actual.put(2, evgenya);
//        actual.replace(2, testUser);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void save() {
        database.edit(0, testUser);
        database.edit(1,null);
        database.save("/home/antonydrift/IdeaProjects/innowiseGroupTestTask/src/resource/usersTest.dat");
        //False result
//        database.open("/home/antonydrift/IdeaProjects/innowiseGroupTestTask/src/resource/users.dat");
        //True result
        database.open("/home/antonydrift/IdeaProjects/innowiseGroupTestTask/src/resource/usersTest.dat");
        Map<Integer, User> expected = database.getDbMap();
        Map<Integer, User> actual = new HashMap<>();

        actual.put(0, testUser);
        actual.put(1, null);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void open() {
        //true result
      database.open("/home/antonydrift/IdeaProjects/innowiseGroupTestTask/src/resource/usersTest.dat");
        //false result
//        database.open("dsds");
        Map<Integer, User> expected = database.getDbMap();
        Map<Integer, User> actual = new HashMap<>();

        actual.put(0, testUser);
        actual.put(1, null);

        Assert.assertEquals(expected, actual);

    }
}
