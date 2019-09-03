import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Form {

    private static final Logger logger = Logger.getLogger(Form.class);


    public User enterFields(String name, String surname, String email, String number, String[] roles) {

        User user = new User();

        try {
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setNumber(number);
            user.setRoles(roles);
            logger.info("Successful. Got next user: " + user);
        } catch (Exception e) {
            logger.error("Can't get user. " + e.getMessage());
        }
        return user;

    }

    public boolean matcherEmail(String email) {
        try {
            String reqex = "^[^@]+" +
                    "@{1}" +
                    "[a-zA-Z]+" +
                    "[.]{1}" +
                    "[a-zA-Z]+$";
            Pattern pattern = Pattern.compile(reqex);
            Matcher matcher = pattern.matcher(email);
            boolean result = matcher.find();
            logger.info("It's email? " + result);
            return result;
        } catch (Exception e) {
            {
                logger.error("Can't read this field=" + email + ". " + e.getMessage());
                return false;
            }
        }
    }

    public boolean matcherNumber(String number) {
        try {
            String reqex = "^[+375]{4}" +
                    "([25]|[29]|[44]|[17]|[33]){2}" +
                    "[0-9]{7}$";
            Pattern pattern = Pattern.compile(reqex);
            Matcher matcher = pattern.matcher(number);
            boolean result = matcher.find();
            logger.info("It's number? " + result);
            return result;
        } catch (Exception e) {
            {
                logger.error("Can't read this field=" + number + ". " + e.getMessage());
                return false;
            }
        }
    }
    }
