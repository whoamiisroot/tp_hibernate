package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entities.Femme;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ILYASS BENNANE
 */
public class FemmeService implements IDao<Femme>{
      @Override
    public boolean create(Femme o) {
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
    public List<Femme> getAll() {
        List<Femme> femmes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femmes = session.createQuery("from Femme").list();
            tx.commit();
            return femmes;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return femmes;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Femme getById(int id) {
        Femme femme = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            femme = (Femme) session.get(Femme.class, id);
            tx.commit();
            return femme;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return femme;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    /*5. Créer une méthode dans la classe FemmeService permettant de faire appel à la requête
de la question 4. */

public int countEnfantsFemmeEntreDates(int femmeId, Date dateDebut, Date dateFin) {
    Session session = null;
    Transaction tx = null;
    int count = 0; // Initialize count as Integer

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Query query = session.getNamedQuery("countEnfantsFemmeEntreDates")
            .setParameter("femmeId", femmeId)
            .setParameter("dateDebut", dateDebut)
            .setParameter("dateFin", dateFin);

         count = ((Number) query.uniqueResult()).intValue();//to convert


        tx.commit();
    } catch (HibernateException ex) {
        if (tx != null) {
            tx.rollback();
        }
    } finally {
        if (session != null) {
            session.close();
        }
    }

    return count;
}

public void displayCountEnfantsFemmeEntreDates(int femmeId, Date dateDebut, Date dateFin) {
    Integer resultat = countEnfantsFemmeEntreDates(femmeId, dateDebut, dateFin);
    Femme femme12=getById(femmeId);
    System.out.println("--------------------------------------------------------");
    System.out.println("Le nombre d'enfants de la femme  ->>>>> "+femme12.getNom()+" "+femme12.getPrenom()+"<<<<-- entre les dates spécifiées est :->>>> " + resultat+" <<<<<--");
    System.out.println("--------------------------------------------------------");
}

  public List<Femme> findWomenMarriedMultipleTimes() {
    Session session = null;
    Transaction tx = null;
    List<Femme> women = new ArrayList<>();

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        Query query = session.getNamedQuery("findWomenMarriedMultipleTimes");
        women = query.list();

        tx.commit();
    } catch (HibernateException ex) {
        if (tx != null) {
            tx.rollback();
        }
    } finally {
        if (session != null) {
            session.close();
        }
    }

    return women;
}

  public void displayWomenMarriedMultipleTimes() {
    List<Femme> marriedWomen = findWomenMarriedMultipleTimes();

    if (marriedWomen.isEmpty()) {
        System.out.println("No women have been married two or more times.");
    } else {
        System.out.println("Women married two or more times:");
        for (Femme woman : marriedWomen) {
             System.out.println("id: " + woman.getId() );
            System.out.println("Name: " + woman.getNom() + " " + woman.getPrenom());
            System.out.println("-------------------------------------");
        }
    }
}

  
  
}
