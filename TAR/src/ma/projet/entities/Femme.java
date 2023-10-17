
package ma.projet.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author ILYASS BENNANE
 */

@Entity


@NamedQuery(
    name = "findWomenMarriedMultipleTimes",
    query = "SELECT f " +
            "FROM Femme f " +
            "JOIN f.mariages m " + 
            "GROUP BY f.id " +
            "HAVING COUNT(m.femme) >= 2"
)


@NamedNativeQuery(
    name = "countEnfantsFemmeEntreDates",
    query = "SELECT CAST(SUM(m.nbEnfant) AS SIGNED) FROM Mariage m WHERE m.femme = :femmeId AND m.dateDebut >= :dateDebut AND m.dateFin <= :dateFin"
)
public class Femme  extends Personne {
  @OneToMany(mappedBy="femme")
    private List<Mariage> mariages;

    public Femme() {
    }
    public Femme(String nom, String prenom, int telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
        this.mariages =new ArrayList<>();
    }   
}
