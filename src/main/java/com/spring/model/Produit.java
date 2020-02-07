package com.spring.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Produit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String nom;

    private String date_ajout;

    @ManyToMany(mappedBy = "produits",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonBackReference("ref")
    private List<Reference> references;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_projet",referencedColumnName = "id")
//    @JsonBackReference("listProjets")
//    @JsonManagedReference("listProduits")
    @JsonIgnoreProperties("produits")
    private Projet projet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDate_ajout() {
        return date_ajout;
    }

    public void setDate_ajout(String date_ajout) {
        this.date_ajout = date_ajout;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", date_ajout='" + date_ajout + '\'' +
                '}';
    }
}
