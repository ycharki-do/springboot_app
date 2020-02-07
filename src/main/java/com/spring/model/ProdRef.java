package com.spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "prod_ref")
public class ProdRef implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date_attr;

    @OneToOne
    @JoinColumn(name = "id_prod",referencedColumnName = "id")
    private Produit produit;

    @OneToOne
    @JoinColumn(name = "id_ref",referencedColumnName = "id")
    private Reference reference;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_attr() {
        return date_attr;
    }

    public void setDate_attr(String date_attr) {
        this.date_attr = date_attr;
    }
}
