package com.sene.scolarite_api.repository;

import com.sene.scolarite_api.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Integer> {
    Optional<Classe> findByCode(String code);
    List<Classe> findByFiliereId(int filiereId);

    boolean existsByLibelle(String libelle);
    boolean existsByCode(String code);

}
