package com.spring.repository;

import com.spring.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface CodeHistRepository extends JpaRepository<HistCode, Integer> {
    List<HistCode> findByCode(Code code);
    List<HistCode> findByReference(Reference reference);
}
