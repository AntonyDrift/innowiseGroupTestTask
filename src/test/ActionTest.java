import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ActionTest {


    private static Action action;
    private static User anton;
    private static User evgenya;
    private static User testUser;
    private static Logger logger;
    private static String path;

    @BeforeClass
    public static void setUp() {
        action = new Action();
        logger = Logger.getLogger(ActionTest.class);
        path = "/home/antonydrift/IdeaProjects/innowiseGroupTestTask/src/resource/usersTest.dat";

    }

    @Before
    public void initUsers() {
        anton = new User("Anton", "Zhurkov",
                "antonydrift96@hotmail.com", "+375257585677", new String[]{"admin, user"});
        evgenya = new User("Evgenya", "Kolobkova",
                "kolobkova_evgenya@yandex.ru", "+375447915891", new String[]{"user"});
        testUser = new User("testName", "testSurname",
                "test@mail.com", "+375172635443", new String[]{"guest"});

    }

    @Test
    public void add() {
        action.addUser(testUser);
    }
    @Test
    public void save() {
        action.saveUsersList(path);
    }
    @Test
    public void open() {
        action.openUsersList(path);
    }
    @Test
    public void edit() {
        action.editUser(0, testUser);
    }
    @Test
     public void search() {
         action.searchUser(0);
     }
     @Test
      public void view() {
        action.viewUsersList();
    }




}
