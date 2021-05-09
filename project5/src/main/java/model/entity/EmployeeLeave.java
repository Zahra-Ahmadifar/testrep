package model.entity;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by Asus on 4/7/2021.
 */
@Entity
@Table(name = "t_employeeLeave")
public class EmployeeLeave extends model.entity.Entity {

    @Column(name = "c_startDate", columnDefinition = "VARCHAR(255")
    private String startDate;

    @Column(name = "c_endDate", columnDefinition = "VARCHAR(255")
    private String endDate;

    @ManyToOne()
    @JoinColumn(name = "c_leaveStatus")
    private CategoryElement leaveStatus;


    public EmployeeLeave(String startDate, String endDate, CategoryElement leaveStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveStatus = leaveStatus;

    }

    public EmployeeLeave() {

    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public CategoryElement getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(CategoryElement leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

}