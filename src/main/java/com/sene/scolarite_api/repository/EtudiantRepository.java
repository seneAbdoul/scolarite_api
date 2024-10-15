package com.sene.scolarite_api.repository;

import com.sene.scolarite_api.model.Classe;
import com.sene.scolarite_api.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
        Optional<Etudiant> findByMatricule(String matricule);
        Etudiant findTopByOrderByIdDesc();
        boolean existsByEmail(String email);
}
