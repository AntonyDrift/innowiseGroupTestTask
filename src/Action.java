import org.apache.log4j.Logger;

public class Action {

    private User user = new User();
    private Form form = new Form();
    private Database database = new Database();
    private static final Logger logger = Logger.getLogger(Action.class);

    public void addUser(User user) {

        try {
            database.add(user);
            logger.info("Successful! Action:add");
        } catch (Exception e) {
            logger.error("Error. Action:add. " + e.getMessage());
        }
    }

    public boolean searchUser(Integer index) {
        try {
            boolean result = database.search(index);
            logger.info("User (ID=" + index + ") is found? It's" + result);
            return result;
        } catch (Exception e) {
            logger.error("Error. Action:searchUser " + e.getMessage());
            return database.search(index);
        }
    }

    public void editUser(Integer index, User user) {
        try {
            database.edit(index, user);
            logger.info("Successful! Action:edit");
        } catch (Exception e) {
            logger.error("Error. Action:edit. " + e.getMessage());
        }
    }

    public void viewUsersList() {
        try {
            System.out.println(database.getDbMap());
            logger.info("Successful! Action:view");
        } catch (Exception e) {
            logger.error("Error. Action:view. " + e.getMessage());
        }
    }

    public void openUsersList(String path) {
        try {
            database.open(path);
            logger.info("Successful!  Action:openList");
        } catch (Exception e) {
            logger.error("Error. Action:openList" + e.getMessage());
        }
    }

    public void saveUsersList(String path) {
        try {
            database.save(path);
            logger.info("Successful!  Action:saveList");
        } catch (Exception e) {
            logger.error("Error. " + e.getMessage());
        }
    }
}

