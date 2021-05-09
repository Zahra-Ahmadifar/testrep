package model.service;

import model.entity.EmployeeLeave;
import model.repository.EmployeeLeaveDA;

/**
 * Created by Asus on 5/2/2021.
 */
public class EmployeeLeaveService {
    private static EmployeeLeaveService leaveService = new EmployeeLeaveService();

    private EmployeeLeaveService() {
    }

    public static EmployeeLeaveService getInstance() {
        return leaveService;
    }

    public void leaveEmployeeSave(EmployeeLeave employeeLeave) {
        EmployeeLeaveDA employeeLeaveDA = new EmployeeLeaveDA();
        employeeLeaveDA.save(employeeLeave);
    }

    public void changeLeaveStatusToAccepted(int leaveId ) {
        EmployeeLeaveDA employeeLeaveDA = new EmployeeLeaveDA();
        employeeLeaveDA.changeStatusToAccepted(leaveId );
    }

    public void changeLeaveStatusToRejected(int leaveId ) {
        EmployeeLeaveDA employeeLeaveDA = new EmployeeLeaveDA();
        employeeLeaveDA.changeStatusToRejected(leaveId );
    }
}
