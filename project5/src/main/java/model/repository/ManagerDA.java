package model.repository;

import common.HibernateUtil;
import model.entity.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 4/25/2021.
 */
public class ManagerDA {
    public Employee validate(String username, String password) {
        Transaction transaction = null;
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            employee = (Employee) session.createQuery("select e from Employee e where e.username =: username and e.password =: password")
                    .setParameter("username", username).setParameter("password", password).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return employee;
    }

    public void save(Employee employee) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employee> findAll() {
        List<Employee> employeeList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT e FROM Employee e where e.active=:content", Employee.class);
            query.setParameter("content", "true");
            employeeList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void update(Employee employee) {
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

    public List<Employee> getAllManager() {
        List<Employee> managerEmployeeList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select me from Employee me where me.position.code =: positionCode");
            query.setParameter("positionCode", "manager");
            managerEmployeeList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return managerEmployeeList;
    }

    public Employee getSelectedManager(String firstName, String lastName) {
        Employee selectedManager = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select me from Employee me where me.position.code =: positionCode and me.firstName =:firstName and me.lastName =: lastName");
            query.setParameter("positionCode", "manager");
            query.setParameter("firstName", firstName);
            query.setParameter("lastName", lastName);
            selectedManager = (Employee) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return selectedManager;
    }

    public void inactive(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query employeeQuery = session.createQuery("select e from Employee e where e.id =: id");
            employeeQuery.setParameter("id", id);
            Employee employee = (Employee) employeeQuery.getSingleResult();

            employee.setActive(false);
            session.update(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Employee> search(Employee employee) {

        List<Employee> employeeList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);

            Predicate predicate = criteriaBuilder.conjunction();

            if (employee.getfirstName() != null && !employee.getfirstName().equals("")) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("firstName"), employee.getfirstName()));
            }
            if (employee.getLastName() != null && !employee.getLastName().equals("")) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("lastName"), employee.getLastName()));
            }
            if (employee.getUsername() != null && !employee.getUsername().equals("")) {
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("username"), employee.getUsername()));
            }
            criteriaQuery.select(root).where(predicate);

            org.hibernate.Query<Employee> query = session.createQuery(criteriaQuery);
            employeeList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public Employee findById(int id) {
        Employee employee = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select e from Employee e where e.id =: id");
            query.setParameter("id", id);
            employee = (Employee) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<String> findAllUsername() {
        List<String> usernames = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select e.username from Employee e ");
            usernames = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usernames;
    }

    public List<Employee> findAllBeneathEmployee(Employee managerEmployee) {
        List<Employee> employeeList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("select distinct e from Employee e join fetch e.leaveList le " +
                    "where le.leaveStatus.code =:registered  and e.manager =: manager ");
            query.setParameter("manager", managerEmployee);
            query.setParameter("registered", "registered");
            employeeList = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

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
}
