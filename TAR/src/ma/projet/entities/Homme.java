package ma.projet.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
/**
 *
 * @author ILYASS BENNANE
 */
@Entity
public class Homme extends Personne {
 @OneToMany(mappedBy="homme")
    private List<Mariage> mariages;

    public Homme() {
    }
    public Homme(String nom, String prenom, int telephone, String adresse, Date dateNaissance) {
        super(nom, prenom, telephone, adresse, dateNaissance);
        this.mariages =new ArrayList<>();
    }

    public List<Mariage> getMariages() {
        return mariages;
    }

    public void setMariages(List<Mariage> mariages) {
        this.mariages = mariages;
    }
 
    
    
}
