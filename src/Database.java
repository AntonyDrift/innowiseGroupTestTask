import lombok.Getter;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


public class Database {
    private static int id = 0;
    String positiveMessage= null;
    String negativeMessage=null;

    private static final Logger logger = Logger.getLogger(Database.class);

    @Getter
    private Map<Integer, User> dbMap = new HashMap<>();

    public void add(User user) {

        id = checkId(id);
        try {
            dbMap.put(id, user);
            positiveMessage="Add user: ID=" + id + ";User=" + user;
            System.out.println(positiveMessage);
            logger.warn(positiveMessage);
        } catch (Exception e) {
           negativeMessage=("User isn't added. " + e.getMessage());
            System.out.println(negativeMessage);
            logger.error(negativeMessage);
        }
        id++;
    }

    public void edit(Integer index, User user) {

        try {
            dbMap.replace(index, user);
            System.out.println("User is changed");
            positiveMessage=("Edit user: Current ID=" + index + ";User=" + user);
            System.out.println(positiveMessage);
            logger.warn(positiveMessage);
        } catch (Exception e) {
            negativeMessage=("User isn't changed. " + e.getMessage());
            System.out.println(negativeMessage);
            logger.error(negativeMessage);
        }
    }

    public Integer checkId(Integer index) {
    try {
        if (dbMap.containsKey(index)) {

            Object[] keys = dbMap.keySet().toArray();

            for (int i = 0; i <= keys.length - 1; i++) {

                if (index < (Integer) keys[i]) {
                    index = (Integer) keys[i];
                }
            }
            index++;
        }
        logger.warn("Existing index:" + index);
    } catch (Exception e) {
        logger.error("Index isn't checked. " + e.getMessage());
    }
        return index;
    }


    public boolean search(Integer index) {
        try {
            boolean result = dbMap.get(index) != null;
            logger.warn("This user already exist? (ID=" + index + ") It's " + result);
            return result;
        } catch (Exception e) {
            logger.error("Can't find user with this ID" + e.getMessage());
            return dbMap.get(index) != null;
        }
    }

    public void save(String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dbMap);
            positiveMessage = "Userlist is saved from path:" + path;
            System.out.println(positiveMessage);
            logger.warn(positiveMessage);
        } catch (
                Exception e) {
            negativeMessage=("Can't save userlist from this path." + e.getMessage());
            System.out.println(negativeMessage);
            logger.error(negativeMessage);
        }
    }

    public void open(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            dbMap = ((Map<Integer, User>) object);
            positiveMessage=("Userlist is opened from path:" + path);
            System.out.println(positiveMessage);
            logger.warn(positiveMessage);
        } catch (
                Exception e) {
           negativeMessage=("Can't open userlist from this path." + e.getMessage());
            System.out.println(negativeMessage);
            logger.error(negativeMessage);
        }
    }
}
