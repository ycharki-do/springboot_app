package com.spring.repository;

import com.spring.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code , Integer> {
    Optional<Code> findByNumero(String num);
    List<Code> findByStatut(int num);
}
