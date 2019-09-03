import org.apache.log4j.Logger;

import java.util.Scanner;

public class Main {

    Form form = new Form();
    Action action = new Action();

    Scanner scanner = new Scanner(System.in);
    String defaultPath = "/home/antonydrift/IdeaProjects/innowiseGroupTestTask/src/resource/users.dat";

    private static final Logger logger = Logger.getLogger(Main.class);


    public static void main(String[] args) {

        Log4jConfigurator log4jConfigurator = new Log4jConfigurator();
        log4jConfigurator.config();

        Main runner = new Main();
        runner.mainMenu();
    }

    public void mainMenu() {

        String position = null;
        Integer index = null;

        System.out.println(
                "1. Add user\n" +
                        "2. Edit user\n" +
                        "3. View userlist\n" +
                        "4. Save userlist\n" +
                        "5. Open userlist\n" +
                        "9. Exit");

        try {
            position = returnMenu(scanner.next());

            switch (position) {
                case "1": {
                    action.addUser(formMenu());
                    mainMenu();
                    break;
                }
                case "2": {
                    System.out.println("Enter userID");

                    index = Integer.parseInt
                            (returnMenu(scanner.next()));

                    if (action.searchUser(index)) {
                        action.editUser(index, formMenu());
                    } else {
                        System.out.println("Wrong ID");
                    }
                    mainMenu();
                    break;
                }
                case "3": {
                    action.viewUsersList();
                    mainMenu();
                    break;
                }
                case "4": {
                    System.out.println("Default path: " + defaultPath +
                            "\nWould you change path? \n1. Yes\n2. No");
                    switch (returnMenu(scanner.next())) {
                        case "1": {
                            System.out.println("Enter new path");
                            action.saveUsersList(returnMenu(scanner.next()));
                            mainMenu();
                            break;
                        }
                        case "2":
                            action.saveUsersList(defaultPath);
                            mainMenu();
                            break;
                    }
                }
                case "5": {

                    System.out.println("Default path: " + defaultPath +
                            "\nWould you change path? \n1. Yes\n2. No");

                    switch (returnMenu(scanner.next())) {
                        case "1": {
                            System.out.println("Enter new path");
                            action.openUsersList(returnMenu(scanner.next()));
                            mainMenu();
                            break;
                        }
                        case "2":
                            action.openUsersList(defaultPath);
                            mainMenu();
                            break;
                    }
                }
                case "9": {
                    System.exit(0);
                    break;
                }
            }
            logger.trace("Main menu correctly work");
        } catch (Exception e) {
            logger.error("Error. " + e.getMessage());
        }
    }

    public String returnMenu(String field) {
        try {

            while (field.equals("*")) {
                mainMenu();
                formMenu().setName(null);
                formMenu().setSurname(null);
                formMenu().setEmail(null);
                formMenu().setNumber(null);
                formMenu().setRoles(null);
                break;
            }
            logger.trace("Return menu correctly work");
            return field;
        } catch (Exception e) {
            logger.error("Error. " + e.getMessage());
        }
        return field;
    }

    public User formMenu() {

        Integer rolesCount = null;

        String name = null;
        String surname = null;
        String email = null;
        String number = null;
        String roles[] = null;

        boolean conditionEmail = false;
        boolean conditionNumber = false;

        try {

            System.out.println("Enter name");
            name = returnMenu(scanner.next());

            System.out.println("Enter surname");
            surname = returnMenu(scanner.next());

            while (!conditionEmail) {
                System.out.println("Enter email");
                email = returnMenu(scanner.next());

                if (form.matcherEmail(email)) {
                    conditionEmail = true;
                }
            }

            while (!conditionNumber) {
                System.out.println("Enter number");
                number = returnMenu(scanner.next());

                if (form.matcherNumber(number)) {
                    conditionNumber = true;
                }
            }

            System.out.println("How much roles? Default: 0-guest");
            rolesCount = Integer.parseInt
                    (returnMenu(scanner.next()));

            if (rolesCount > 0) {
                roles = new String[rolesCount];
                for (int i = 0; i <= rolesCount - 1; i++) {
                    System.out.println("Enter role");
                    roles[i] = scanner.next();
                }
            } else
                roles = new String[]{"quest"};

            logger.trace("Form menu correctly work");
        } catch (Exception e) {
            logger.error("Error. " + e.getMessage());
        }

        return form.enterFields(name, surname, email, number, roles);
    }
}



