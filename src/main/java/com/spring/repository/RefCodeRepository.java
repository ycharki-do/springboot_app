package com.spring.repository;

import com.spring.model.Code;
import com.spring.model.RefCode;
import com.spring.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RefCodeRepository extends JpaRepository<RefCode,Integer> {
    List<RefCode> findByCodeAndReference(Code code, Reference reference);
    List<RefCode> findByCode(Code code);
    List<RefCode> findByReference(Reference reference);
}
