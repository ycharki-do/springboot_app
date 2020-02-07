package com.spring.repository;

import com.spring.model.Produit;
import com.spring.model.Reference;
import com.spring.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    List<Produit> findByNom(String nom);

    @Query("select op from Produit op INNER JOIN op.projet pr where pr.id=8")
    List<Produit> findByProjetNull();
}
