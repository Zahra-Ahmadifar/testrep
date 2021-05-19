package model.entity;

import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Asus on 4/7/2021.
 */
@Entity
@Table(name = "t_employee")
@SelectBeforeUpdate
public class Employee extends model.entity.Entity {

    @Column(name = "c_firstName", columnDefinition = "VARCHAR(255)")
    private String firstName;

    @Column(name = "c_lastName", columnDefinition = "VARCHAR(255)")
    private String lastName;

    @Column(name = "c_fatherName", columnDefinition = "VARCHAR(255)")
    private String fatherName;

    @ManyToOne()
    @JoinColumn(name = "c_position")
    private CategoryElement position;

    @Column(name = "c_email", columnDefinition = "VARCHAR(255)")
    private String email;

    @ManyToOne()
    @JoinColumn(name = "c_manager")
    private Employee manager;
    @OneToMany(mappedBy = "manager")
    private Set<Employee> employees = new HashSet<Employee>();

    @Column(name = "c_username", columnDefinition = "VARCHAR(255)")
    private String username;

    @Column(name = "c_password", columnDefinition = "VARCHAR(255)")
    private String password;

    @OneToMany(mappedBy = "employee")
    private Set<Email> sentEmails = new HashSet<Email>();

    @OneToMany()
    @JoinColumn(name = "c_employeeId")
    private Set<EmployeeLeave> leaveList = new HashSet<EmployeeLeave>();

    public Employee(int id, String firstName, String lastName, String fatherName, String email) {
        this.id =id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.email = email;

    }

    public Employee(String firstName, String lastName, String fatherName, CategoryElement position, String email, Employee manager, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.position = position;
        this.email = email;
        this.manager = manager;
        this.username = username;
        this.password = password;
    }

    public Employee(int id,String firstName, String lastName, String fatherName, CategoryElement position, String email, Employee manager, String username, String password) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.position = position;
        this.email = email;
        this.manager = manager;
    }

    public Employee(int id,boolean active,boolean disable,String email,String fatherName,String firstName, String lastName, Employee manager,String password, CategoryElement position,  String username) {
        this.id=id;
        this.active=active;
        this.disable=disable;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fatherName = fatherName;
        this.position = position;
        this.email = email;
        this.manager = manager;
        this.username=username;
        this.password=password;
    }

    public Employee(String firstName, String lastName, String username) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Employee() {
    }


    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public CategoryElement getPosition() {
        return position;
    }

    public void setPosition(CategoryElement position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Email> getSentEmails() {
        return sentEmails;
    }

    public void setSentEmails(Set<Email> sentEmails) {
        this.sentEmails = sentEmails;
    }

    public Set<EmployeeLeave> getLeaveList() {
        return leaveList;
    }

    public void setLeaveList(Set<EmployeeLeave> leaveList) {
        this.leaveList = leaveList;
    }
}
