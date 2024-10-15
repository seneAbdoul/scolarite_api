package com.sene.scolarite_api.repository;

import com.sene.scolarite_api.model.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {
    boolean existsByAnneeScolaire(String anneeScolaire);
    //recherches la liste des inscrit dans une annee
    List<Inscription> findInscritsByClasseIdAndAnneeScolaire(int classeId, String anneeScolaire);

   //recupere l'inscription en cours
   @Query("SELECT i FROM Inscription i WHERE i.etudiant.id = :etudiantId AND i.periodeInscription.statut = :statut")
   Optional<Inscription> findInscriptionEtudiantEnCours(@Param("etudiantId") int etudiantId, @Param("statut") int statut);

    //recherches la liste des inscriptions d'un' etudiant durant tout son cursus
    @Query("SELECT i FROM Inscription i WHERE i.etudiant.id = :etudiantId")
    List<Inscription> findInscriptionsEtudiant(@Param("etudiantId") int etudiantId);

    //recherches la liste des inscriptions d'un' etudiant par matricule
    @Query("SELECT i FROM Inscription i WHERE i.etudiant.matricule = :matricule")
    List<Inscription> findInscriptionsByMatricule(@Param("matricule") String matricule);
}
