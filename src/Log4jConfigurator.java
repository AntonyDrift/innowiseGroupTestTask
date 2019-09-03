import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Log4jConfigurator {

    private static final Logger logger = Logger.getRootLogger();

    public void config() {
        try {
            PatternLayout layout = new PatternLayout();
            String conversionPattern = "%-7p %d [%t] %c %x - %m%n";
            layout.setConversionPattern(conversionPattern);

            FileAppender fileAppender = new FileAppender();
            fileAppender.setFile("src/resource/logs/file.log");
            fileAppender.setLayout(layout);
            fileAppender.activateOptions();

            logger.setLevel(Level.DEBUG);
            logger.addAppender(fileAppender);

            logger.info("Successful setup a logger");
        } catch (Exception e) {
            logger.error("Error. " + e.getMessage());
        }
    }
}
