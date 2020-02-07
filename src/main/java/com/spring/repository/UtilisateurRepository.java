package com.spring.repository;

import com.spring.model.Utilisateur;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
        Utilisateur findByUserName(String username);
}
