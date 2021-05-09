package model.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Asus on 4/7/2021.
 */
@Entity
@Table(name = "t_email")
public class Email extends model.entity.Entity {

    @ManyToOne
    @JoinColumn(name = "c_id")
    private Employee employee;

    @Column(name = "c_emailsubject", columnDefinition = "VARCHAR(255)")
    private String emailSubject;

    @Column(name = "c_emailbody", columnDefinition = "VARCHAR(255)")
    private String emailBody;

    @Column(name = "c_filePath" ,columnDefinition = "VARCHAR(255)")
    private String filePath;

    @OneToMany()
    @JoinTable(name = "t_Employee_Email",
            joinColumns = {@JoinColumn(name = "c_emailReceiverId")},
            inverseJoinColumns = {@JoinColumn(name = "c_receiverEmailId")})
    private Set<Employee> receiverEmployees = new HashSet<Employee>();

    public Email(Employee employee, String emailSubject, String emailBody, List<String> attachment, Set<Employee> receiverEmployees) {
        this.employee = employee;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
        this.receiverEmployees = receiverEmployees;
    }

    public Email() {
    }

    public Set<Employee> getReceiverEmployees() {
        return receiverEmployees;
    }

    public void setReceiverEmployees(Set<Employee> receiverEmployees) {
        this.receiverEmployees = receiverEmployees;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
