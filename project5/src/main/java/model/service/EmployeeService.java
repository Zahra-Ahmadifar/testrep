package model.service;

import model.entity.Employee;
import model.entity.EmployeeLeave;
import model.repository.EmployeeDA;

import java.util.List;

/**
 * Created by Asus on 5/2/2021.
 */
public class EmployeeService {
    private EmployeeService(){}

    private static EmployeeService employeeService = new EmployeeService();

    public static EmployeeService getInstance() {
        return employeeService;
    }

    public Employee findEmployeeByUsername(String username){
        EmployeeDA employeeDA = new EmployeeDA();
        return employeeDA.findByUsername(username);
    }
    public void updateEmployee(Employee employee){
        EmployeeDA employeeDA =new EmployeeDA();
        employeeDA.updateEmployee(employee);
    }
    public void updateEmployeeLeave(Employee employee, EmployeeLeave employeeLeave){
        EmployeeDA employeeDA = new EmployeeDA();
        employeeDA.updateLeaveEmployee(employee,employeeLeave);
    }

    public List<EmployeeLeave> findLeaveEmployee(Employee employee){
        EmployeeDA employeeDA = new EmployeeDA();
        return employeeDA.findLeaveEmployee(employee);
    }
    public List<Employee> getAllEmployees(){
        EmployeeDA employeeDA =new EmployeeDA();
        return employeeDA.getAllEmployees();
    }
    public Employee findEmployeeById(int id){
        EmployeeDA employeeDA = new EmployeeDA();
        return employeeDA.findById(id);
    }
    public List<Employee> receivedEmailEmployees(List<Integer> employeeIds){
        EmployeeDA employeeDA = new EmployeeDA();
        return employeeDA.receivedEmailEmployees(employeeIds);
    }
}
