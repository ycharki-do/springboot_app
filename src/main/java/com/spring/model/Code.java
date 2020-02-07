package com.spring.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "code")
public class Code implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String numero;
    private int statut;
    @Column(name = "date_attr")
    @JsonProperty("date_attr")
    private String dateAttr;

    @ManyToMany
    @JoinTable(name = "ref_code",joinColumns = @JoinColumn(name = "id_code",referencedColumnName = "id")
            ,inverseJoinColumns = @JoinColumn(name = "id_ref",referencedColumnName = "id"))
    @JsonBackReference("code_ref")
    private List<Reference> references;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public String getDateAttr() {
        return dateAttr;
    }

    public void setDateAttr(String dateAttr) {
        this.dateAttr = dateAttr;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }
}
