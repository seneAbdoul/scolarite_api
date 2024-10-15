package com.sene.scolarite_api.service;

import com.sene.scolarite_api.repository.ClasseRepository;
import org.springframework.stereotype.Service;

@Service
public class ClasseService {
 private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }
    public void validateUniqueLibelle(String libelle) {
        if (classeRepository.existsByLibelle(libelle)) {
            throw new IllegalArgumentException("Le libellé de la classe existe déjà");
        }
    }

    public void validatePositiveping(int ping) {
        if (ping <= 0) {
            throw new IllegalArgumentException("Le champ doit être positive");
        }
    }
}
