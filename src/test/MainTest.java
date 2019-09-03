import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Scanner;

public class MainTest {

    private static Main main;
    private static Logger logger;

    @BeforeClass
    public static void setUp() {
        main = new Main();
        logger=Logger.getLogger(MainTest.class);
    }

    @Test
    public void mainMenu() {
        main.mainMenu();
    }

    @Test
    public void returnMenu() {
        main.returnMenu("*");
    }

    @Test
    public void formMenu() {
        main.formMenu();
    }
}
