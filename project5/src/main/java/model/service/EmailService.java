package model.service;

import model.entity.Email;
import model.entity.Employee;
import model.repository.EmailDA;

import java.util.List;

/**
 * Created by Asus on 5/2/2021.
 */
public class EmailService {
    private EmailService() {
    }

    private static EmailService emailService = new EmailService();

    public static EmailService getInstance() {
        return emailService;
    }

    public void saveEmail(Email email){
        EmailDA emailDao = new EmailDA();
        emailDao.save(email);
    }
    public List<Email> receivedEmails(Employee employee){
        EmailDA emailDA = new EmailDA();
        return emailDA.receivedEmails(employee);
    }
    public List<Object[]> receivedEmailsInfo(Employee employee){
        EmailDA emailDA = new EmailDA();
        return emailDA.receivedEmailsInfo(employee);
    }
    public List<Object[]> sentEmailsInfo(Employee employee){
        EmailDA emailDA = new EmailDA();
        return emailDA.sentEmailsInfo(employee);
    }
}
