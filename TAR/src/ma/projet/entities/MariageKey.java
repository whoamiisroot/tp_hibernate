package ma.projet.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 *
 * @author ILYASS BENNANE
 */
@Embeddable
public class MariageKey  implements Serializable{
private int homme;
private int femme;
 @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;
  @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateFin;
    private int nbEnfant;

    public MariageKey() {
    }

    public MariageKey(int homme, int femme, Date dateDebut, Date dateFin) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nbEnfant=0;
    }
     public MariageKey(int homme, int femme, Date dateDebut) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        
         this.nbEnfant=0;
      
    }
 public MariageKey(int homme, int femme, Date dateDebut, int nbEnfant) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        
       
        this.nbEnfant = nbEnfant;
    }
    public MariageKey(int homme, int femme, Date dateDebut, Date dateFin, int nbEnfant) {
        this.homme = homme;
        this.femme = femme;
        this.dateDebut = dateDebut;
        
        this.dateFin = dateFin;
        this.nbEnfant = nbEnfant;
    }

    public int getHomme() {
        return homme;
    }

    public void setHomme(int homme) {
        this.homme = homme;
    }

    public int getFemme() {
        return femme;
    }

    public void setFemme(int femme) {
        this.femme = femme;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getNbEnfant() {
        return nbEnfant;
    }

    public void setNbEnfant(int nbEnfant) {
        this.nbEnfant = nbEnfant;
    }

  

}
