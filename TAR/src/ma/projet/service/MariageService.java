package ma.projet.service;

import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entities.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MariageService implements IDao<Mariage>{
      @Override
    public boolean create(Mariage o) {
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(o);
            tx.commit();
            return true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    @Override
    public List<Mariage> getAll() {
        List<Mariage> mariages = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            mariages = session.createQuery("from Mariage").list();
            tx.commit();
            return mariages;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return mariages;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    @Override
    public Mariage getById(int id) {
        Mariage mariage = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            mariage = (Mariage) session.get(Mariage.class, id);
            tx.commit();
            return mariage;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return mariage;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
