package model.repository;


import common.HibernateUtil;
import model.entity.Email;
import model.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EmailDA {
    public void save(Email email) {
        Transaction transaction = null;
        Integer emailId = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(email);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Email> receivedEmails(Employee employee) {
        List<Email> receivedEmails = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select email from Email email join email.receiverEmployees employee  " +
                    "  where employee.id =: id");
            query.setParameter("id", employee.getId());
            receivedEmails = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receivedEmails;
    }

    public List<Object[]> receivedEmailsInfo(Employee employee) {
        List<Object[]> receivedEmailsInfo = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select email.id , email.version," +
                    " sender.firstName , sender.lastName , email.emailSubject,email.emailBody , email.attachment from Email email , Employee sender join email.receiverEmployees receiver join sender.sentEmails se " +
                    "  where receiver.id =: id AND se.id = email.id");
            query.setParameter("id", employee.getId());
            receivedEmailsInfo = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receivedEmailsInfo;
    }

    public List<Object[]> sentEmailsInfo(Employee employee) {
        List<Object[]> sentEmailsInfo = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createSQLQuery("select email.id ,email.c_creationDataTime ,receiver.c_firstName,receiver.c_lastName,email.c_content,email.c_filePath \n" +
                    "from dotin5.t_employee receiver inner join \n" +
                    "dotin5.m_employee_email employee_email on receiver.id = employee_email.c_emailReceiverId inner join dotin5.t_email email on email.id=employee_email.c_receivedEmailId inner join\n" +
                    "dotin5.t_employee sender on sender.id = email.c_emailSenderId where sender.id = :id");
            query.setParameter("id", employee.getId());
            sentEmailsInfo = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sentEmailsInfo;
    }

}
