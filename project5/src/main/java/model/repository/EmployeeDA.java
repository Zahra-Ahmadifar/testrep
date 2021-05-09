package model.repository;


import common.HibernateUtil;
import model.entity.Employee;
import model.entity.EmployeeLeave;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDA {
    public Employee findByUsername(String username) {
    Employee employee = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        Query query = session.createQuery("select e from Employee e where e.username =: username");
        query.setParameter("username", username);
        employee = (Employee) query.getSingleResult();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return employee;
}

    public void updateEmployee(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    public List<Employee> getAllEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select e from Employee e ");
            employeeList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public Employee findById(int id) {
        Employee employee = new Employee();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select e from Employee e where e.id =:id");
            query.setParameter("id", id);
            employee = (Employee) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }


    public List<Employee> receivedEmailEmployees(List<Integer> employeeIds) {
        List<Employee> receivedEmailEmployees = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            MultiIdentifierLoadAccess<Employee> multiLoadAccess = session.byMultipleIds(Employee.class);
            receivedEmailEmployees = multiLoadAccess.multiLoad(employeeIds);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return receivedEmailEmployees;
    }

    public void updateLeaveEmployee(Employee employee, EmployeeLeave employeeLeave) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Employee updatedEmployee = session.get(Employee.class, employee.getId());
            updatedEmployee.getLeaveList().add(employeeLeave);
            session.update(updatedEmployee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<EmployeeLeave> findLeaveEmployee(Employee employee) {
        List<EmployeeLeave> leaveEmployeeSet = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select e.leaveList from Employee e where e.id =: id");
            query.setParameter("id", employee.getId());
            leaveEmployeeSet = (List<EmployeeLeave>) query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return leaveEmployeeSet;
    }


}
