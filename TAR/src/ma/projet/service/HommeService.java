package ma.projet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.dao.IDao;
import ma.projet.entities.Femme;
import ma.projet.entities.Homme;
import ma.projet.entities.Mariage;
import ma.projet.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ILYASS BENNANE
 */
public class HommeService implements IDao<Homme> {

    @Override
    public boolean create(Homme o) {
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
    public List<Homme> getAll() {
        List<Homme> hommes = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            hommes = session.createQuery("from Homme").list();
            tx.commit();
            return hommes;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return hommes;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Homme getById(int id) {
        Homme homme = null;
        Session session = null;
        Transaction tx = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            homme = (Homme) session.get(Homme.class, id);
            tx.commit();
            return homme;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            return homme;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    /*3. Créer une méthode permettant d’afficher les épouses d’un homme passé en paramètre
     entre deux dates dans la classe HommeService.*/

    public List<Femme> getWives(int id, Date dateDebut, Date dateFin) throws ParseException {
        List<Femme> epouses = new ArrayList<>();
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            epouses = session.createCriteria(Femme.class)
                    .createAlias("mariages", "mar")
                    .createAlias("mar.homme", "hm")
                    .add(Restrictions.eq("hm.id", id))
                    .list();

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

        return epouses;
    }

    public void displayGetWives(int idHomme, Date dateDebut, Date dateFin) throws ParseException {
        List<Femme> epouses = getWives(idHomme, dateDebut, dateFin);

        if (epouses.isEmpty()) {
            System.out.println("Aucune épouse trouvée pour l'homme avec l'ID " + idHomme);
        } else {
            System.out.println("Épouses de l'homme avec l'ID " + idHomme + " entre " + dateDebut + " et " + dateFin + ":");
            for (Femme femme : epouses) {
                System.out.println("Nom: " + femme.getNom() + ", Prénom: " + femme.getPrenom());
            }
        }
    }

    /*   8--------Créer une méthode permettant de renvoyer le nombre des hommes qui sont mariés par 4
femmes entre deux dates en utilisant l’API CRITERIA. */
 public int countMenMarriedByFourWomenBetweenDates(Date startDate, Date endDate) {
    Session session = null;
    int count = 0;

    try {
        session = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(Mariage.class)
        .createAlias("homme", "h")
        .add(Restrictions.lt("id.dateFin", endDate))
        .add(Restrictions.gt("id.dateDebut", startDate));
  

        List<Mariage> mariages = criteria.list();

        for (Mariage mariage : mariages) {
           
            Homme homme = mariage.getHomme();

            if (homme.getMariages().size() >= 4) {
                count++;
            }
        }
        count=count/4;
    } finally {
        if (session != null) {
            session.close();
        }
    }

    return (count);
}


    public void displayMenMarriedByFourWomenBetweenDates(Date startDate, Date endDate) {
    int count = countMenMarriedByFourWomenBetweenDates(startDate, endDate);

    System.out.println("Nombre d'hommes mariés par quatre femmes entre " + startDate + " et " + endDate + ": " + count);
     System.out.println("-----------------------------------------------------------------");
}

    /*ex9 info homme marie
    */
//    
public void displayAllMarriagesOfMan(int hommeId) {
    Session session = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();

        Homme homme = (Homme) session.get(Homme.class, hommeId);

        if (homme != null) {
            System.out.println("Nom : " + homme.getNom() + " " + homme.getPrenom());

            List<Mariage> mariages = session.createCriteria(Mariage.class)
                    .add(Restrictions.eq("homme", homme))
                    .list();

            if (!mariages.isEmpty()) {
                System.out.println("Mariages de l'homme avec l'ID " + hommeId + ":");
                int mariageEnCoursCount = 1;
                int mariageEchoueCount = 1;
                System.out.println("---------------------------------");
                // Afficher les mariages en cours
                System.out.println("Mariages en cours :");
                System.out.println("---------------------------------");
                for (Mariage mariage : mariages) {
                    if (mariage.getId().getDateDebut() != null && mariage.getId().getDateFin() == null) {
                        System.out.println("Femme : " + mariage.getFemme().getNom() + " " + mariage.getFemme().getPrenom());
                        System.out.println("Date Début : " + mariage.getId().getDateDebut());
                        System.out.println();
                        mariageEnCoursCount++;
                    }
                }
                System.out.println("---------------------------------");
                // Afficher les mariages échoués
                System.out.println("Mariages échoués :");
                System.out.println("---------------------------------");
                for (Mariage mariage : mariages) {
                    if (mariage.getId().getDateDebut() != null && mariage.getId().getDateFin() != null) {
                        System.out.println("Femme : " + mariage.getFemme().getNom() + " " + mariage.getFemme().getPrenom());
                        System.out.println("Date Début : " + mariage.getId().getDateDebut());
                        System.out.println("Date Fin : " + mariage.getId().getDateFin());
                        System.out.println();
                        mariageEchoueCount++;
                    }
                }
            } else {
                System.out.println("Cet homme n'a pas de mariages enregistrés.");
            }
        } else {
            System.out.println("Aucun homme trouvé avec l'ID : " + hommeId);
        }
    } finally {
        if (session != null) {
            session.close();
        }
    }
}


    
    
}
