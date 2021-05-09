package common;

import model.service.ManagerService;
import org.apache.log4j.Logger;

import java.util.List;

public class UsernameValidation {
    Logger logger = Logger.getRootLogger();

    public boolean duplicateUsername(String employeeUsername) {
        boolean invalidUsername = false;
        List<String> allSavedUsernames = ManagerService.getInstance().findAllUsernameEmployee();
        for (String username : allSavedUsernames) {
            if (employeeUsername.equals(username)) {
                invalidUsername = true;
                logger.info("username " + employeeUsername + " is used before.");
            }
        }
        return invalidUsername;
    }
}
