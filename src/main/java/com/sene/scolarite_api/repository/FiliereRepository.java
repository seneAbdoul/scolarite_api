package com.sene.scolarite_api.repository;

import com.sene.scolarite_api.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Integer> {
    boolean existsByLibelle(String libelle);
}
