package com.spring.repository;

import com.spring.model.ProdRef;
import com.spring.model.Produit;
import com.spring.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface ProdRefRepository extends JpaRepository<ProdRef, Integer> {
    List<ProdRef> findByProduit(Produit produit);
    List<ProdRef> findByReference(Reference reference);


}
