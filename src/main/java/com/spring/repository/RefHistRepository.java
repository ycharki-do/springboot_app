package com.spring.repository;


import com.spring.model.HistRef;
import com.spring.model.Produit;
import com.spring.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RefHistRepository extends JpaRepository<HistRef, Integer> {
    List<HistRef> findByProduit(Produit produit);
    List<HistRef> findByReference(Reference reference);
}
