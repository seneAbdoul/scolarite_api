package com.sene.scolarite_api.service;

import com.sene.scolarite_api.model.Filiere;
import com.sene.scolarite_api.model.Niveau;
import com.sene.scolarite_api.repository.FiliereRepository;
import com.sene.scolarite_api.repository.NiveauRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FiliereService {

    private final FiliereRepository filiereRepository;
    private final NiveauRepository niveauRepository;

    public FiliereService(FiliereRepository filiereRepository, NiveauRepository niveauRepository) {
        this.filiereRepository = filiereRepository;
        this.niveauRepository = niveauRepository;
    }


    public void validateUniqueLibelle(String libelle) {
        if (filiereRepository.existsByLibelle(libelle)) {
            throw new IllegalArgumentException("Le libellé de la filière existe déjà");
        }
    }

    public boolean isFiliereExists(int filiereId) {
        Optional<Filiere> filiere = filiereRepository.findById(filiereId);
        return filiere.isPresent();
    }

    public boolean isNiveauExists(int niveauId) {
        Optional<Niveau> niveau = niveauRepository.findById(niveauId);
        return niveau.isPresent();
    }
    public String Affiche(){
        throw new IllegalArgumentException("objet introuvable");
    }
}
