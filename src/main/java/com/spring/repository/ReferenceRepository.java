package com.spring.repository;

import com.spring.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface ReferenceRepository extends JpaRepository<Reference, Integer> {
    List<Reference> findByStatut(int num);
}
