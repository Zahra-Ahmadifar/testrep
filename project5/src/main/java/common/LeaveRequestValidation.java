package common;

import model.entity.Employee;
import model.entity.EmployeeLeave;
import model.service.EmployeeService;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LeaveRequestValidation {
    static Logger logger = Logger.getRootLogger();

    private LeaveRequestValidation() {
    }

    private static LeaveRequestValidation leaveRequestValidation = new LeaveRequestValidation();

    public static LeaveRequestValidation getInstance() {
        return leaveRequestValidation;
    }

    public boolean validLeave(String leaveFrom, String leaveTo, Employee employee) {
       LocalDate leaveStartDate= stringToLocalDateConverter(leaveFrom);
        LocalDate leaveEndDate= stringToLocalDateConverter(leaveTo);
        boolean isValid = true;
        List<EmployeeLeave> leaveEmployeeSet = EmployeeService.getInstance().findLeaveEmployee(employee);
        if (leaveEmployeeSet != null) {
            for (EmployeeLeave employeeLeave : leaveEmployeeSet) {
                LocalDate startLeave = stringToLocalDateConverter(employeeLeave.getStartDate());
                LocalDate endLeave = stringToLocalDateConverter(employeeLeave.getEndDate());
                if (!leaveStartDate.isAfter(endLeave) && !startLeave.isAfter(leaveEndDate)) {
                    logger.info("overlap occoured !!");
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    public LocalDate stringToLocalDateConverter(String strLocalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = null;
        if (strLocalDate != null && !strLocalDate.equals("")) {
            localDate = LocalDate.parse(strLocalDate, formatter);
        }
        return localDate;
    }
}
