import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

public class FormTest {

    private static Form form;

    private static String name;
    private static String surname;
    private static String email;
    private static String number;
    private static String[] roles;
    private static Logger logger;

    @BeforeClass
    public static void setUp() {
        form = new Form();
        name = "TestName";
        surname = "TestSurname";
        email = "test@mail.com";
        number = "+375257585677";
        roles = new String[]{"admin, user"};
        logger= Logger.getLogger(FormTest.class);
    }


    @Test
    public void enterFields() {
        form.enterFields(name,surname,email,number,roles);
    }

    @Test
    public void matcherEmail() {
        form.matcherEmail(email);
    }

    @Test
    public void matcherNumber() {
        form.matcherNumber(number);
    }
}

