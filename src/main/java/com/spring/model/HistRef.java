
package com.spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hist_ref")
public class HistRef implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date_debut;
    private String date_fin;

    @OneToOne
    @JoinColumn(name = "id_ref",referencedColumnName = "id")
    private Reference reference;

    @OneToOne
    @JoinColumn(name = "id_prod", referencedColumnName = "id")
    private Produit produit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
