package model.repository;


import common.HibernateUtil;
import model.entity.CategoryElement;
import model.entity.EmployeeLeave;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

public class EmployeeLeaveDA {
    public void save(EmployeeLeave leaveEmployee){
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        session.save(leaveEmployee);
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}
    public void changeStatusToAccepted(int leaveId ){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();


            Query categoryElementQuery = session.createQuery("select ce from CategoryElement ce where ce.code =: code");
            categoryElementQuery.setParameter("code", "accepted");
            CategoryElement accepted = (CategoryElement) categoryElementQuery.getSingleResult();

            Query query=session.createQuery("update EmployeeLeave leave set leave.leaveStatus =:accepted , leave.version =:version where leave.id =:id");
            query.setParameter("accepted",accepted);
            query.setParameter("id",leaveId);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public void changeStatusToRejected(int leaveId ){
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query categoryElementQuery = session.createQuery("select ce from CategoryElement ce where ce.code =: code");
            categoryElementQuery.setParameter("code", "rejected");
            CategoryElement rejected = (CategoryElement) categoryElementQuery.getSingleResult();

            Query query=session.createQuery("update EmployeeLeave leave set leave.leaveStatus =:rejected , leave.version =:version where leave.id =:id");
            query.setParameter("rejected",rejected);
            query.setParameter("id",leaveId);

            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
