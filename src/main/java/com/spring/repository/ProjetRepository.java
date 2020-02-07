package com.spring.repository;

import com.spring.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProjetRepository extends JpaRepository<Projet, Integer> {

}


