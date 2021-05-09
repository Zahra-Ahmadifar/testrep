package model.repository;


import common.HibernateUtil;
import model.entity.CategoryElement;
import org.hibernate.Session;
import javax.persistence.Query;

public class CategoryElementDA {

    public CategoryElement findByCode(String code) {

        CategoryElement categoryElement = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Query query = session.createQuery("select ce from CategoryElement ce where ce.code=:categoryCode");
            query.setParameter("categoryCode", code);
            categoryElement = (CategoryElement) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryElement;
    }
}
