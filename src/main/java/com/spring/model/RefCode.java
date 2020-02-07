package com.spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ref_code")
public class RefCode implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String date_attr;

    @OneToOne
    @JoinColumn(name = "id_code",referencedColumnName = "id")
    private Code code;

    @OneToOne
    @JoinColumn(name = "id_ref",referencedColumnName = "id")
    private Reference reference;

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

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Reference getReference() {
        return reference;
    }

    public void setReference(Reference reference) {
        this.reference = reference;
    }
}
