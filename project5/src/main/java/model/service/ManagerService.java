package model.service;

import model.entity.Employee;
import model.repository.ManagerDA;

import java.util.List;

/**
 * Created by Asus on 5/2/2021.
 */
public class ManagerService {
    private static ManagerService managerService = new ManagerService();

    public static ManagerService getInstance() {
        return managerService;
    }

    private ManagerService() {
    }

    public Employee login(String username, String password) {
        ManagerDA managerDA = new ManagerDA();
        Employee employee = managerDA.validate(username, password);
        return employee;
    }
    public void saveEmployee(Employee employee){
        ManagerDA managerDA =new ManagerDA();
        managerDA.save(employee);

    }
    public List<Employee> findAllEmployee(){
        ManagerDA managerDA =new ManagerDA();
        return managerDA.findAll();
    }
    public void updateEmployee(Employee employee){
        ManagerDA managerDA =new ManagerDA();
        managerDA.update(employee);
    }

    public List<Employee> getAllManagerEmployee(){
        ManagerDA managerDA = new ManagerDA();
        return managerDA.getAllManager();
    }
    public Employee getSelectedEmployeeManager(String firstName , String lastName){
        ManagerDA managerDA =new ManagerDA();
        return managerDA.getSelectedManager(firstName,lastName);
    }
    public void inactiveEmployee(int employeeId){
        ManagerDA managerDA = new ManagerDA();
        managerDA.inactive(employeeId);
    }
    public List<Employee> searchEmployee(Employee employee){
        ManagerDA managerDA=new ManagerDA();
        return managerDA.search(employee);
    }
    public Employee findEmployeeById(int id){
        ManagerDA managerDA = new ManagerDA();
        return managerDA.findById(id);
    }
    public List<String> findAllUsernameEmployee(){
        ManagerDA managerDA = new ManagerDA();
        return managerDA.findAllUsername();
    }
    public List<Employee> findAllBeneathEmployee(Employee manager){
        ManagerDA managerDA = new ManagerDA();
        return managerDA.findAllBeneathEmployee(manager);
    }
    public Employee findManagetByUsername(String username){
        ManagerDA managerDA = new ManagerDA();
        return managerDA.findByUsername(username);
    }
}
