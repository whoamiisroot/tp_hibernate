/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ILYASS BENNANE
 */
@Entity
public class Mariage  implements Serializable {

    @EmbeddedId
    private MariageKey id;
    @JoinColumn(name = "homme", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Homme homme;
    @JoinColumn(name = "femme", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Femme femme;

    public Mariage() {
    }

    public Mariage(MariageKey id) {
        this.id = id;
    }

    public MariageKey getId() {
        return id;
    }

    public void setId(MariageKey id) {
        this.id = id;
    }

    public Homme getHomme() {
        return homme;
    }

    public void setHomme(Homme homme) {
        this.homme = homme;
    }

    public Femme getFemme() {
        return femme;
    }

    public void setFemme(Femme femme) {
        this.femme = femme;
    }

    @Override
    public String toString() {
        return "Mariage{" + "id=" + id + ", homme=" + homme + ", femme=" + femme + '}';
    }

}
