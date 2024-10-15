package com.sene.scolarite_api.service;

import com.sene.scolarite_api.repository.FiliereRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidatorService {

    public void isVide(String libelle) {
        if (libelle == null || libelle.trim().isEmpty()) {
            throw new IllegalArgumentException("le champ est obligatoire");
        }
    }
    public void isNull(Object objet) {
        if (objet == null) {
            throw new IllegalArgumentException("objet obligatoire");
        }
    }
    public void isVideDate(LocalDate value, String fieldName) {
        if (value == null) {
            throw new IllegalArgumentException(fieldName + " est obligatoire");
        }
    }

    public void validateAnneeScolaireFormat(String anneeScolaire) {
        if (!anneeScolaire.matches("\\d{4}-\\d{4}") && !anneeScolaire.matches("\\d{4}/\\d{4}")) {
            throw new IllegalArgumentException("Le format de l'année scolaire est incorrect");
        }
    }


}
