package com.sene.scolarite_api.service;

import com.sene.scolarite_api.model.Classe;
import com.sene.scolarite_api.model.Etudiant;
import com.sene.scolarite_api.model.Niveau;
import com.sene.scolarite_api.model.PeriodeInscription;
import com.sene.scolarite_api.repository.ClasseRepository;
import com.sene.scolarite_api.repository.EtudiantRepository;
import com.sene.scolarite_api.repository.PeriodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Service
public class InscriptionService {
    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;
    private final PeriodeRepository periodeRepository;

    public InscriptionService(EtudiantRepository etudiantRepository, ClasseRepository classeRepository, PeriodeRepository periodeRepository) {
        this.etudiantRepository = etudiantRepository;
        this.classeRepository = classeRepository;
        this.periodeRepository = periodeRepository;
    }

    public boolean isEtudiantExists(int etudiantId) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(etudiantId);
        return etudiant.isPresent();
    }
    public boolean isClasseExists(int classeId) {
        Optional<Classe> classe = classeRepository.findById(classeId);
        return classe.isPresent();
    }
    public boolean isPeriodeExists(int periodeInscriptionId) {
        Optional<PeriodeInscription> periodeInscription = periodeRepository.findById(periodeInscriptionId);
        return periodeInscription.isPresent();
    }
    public String Affiche(){
        throw new IllegalArgumentException("objet introuvable");
    }

    public void validateDateInAnneeScolaire(LocalDate dateInscription, String anneeScolaire) {
        String[] years = anneeScolaire.split("[-/]");
        int startYear = Integer.parseInt(years[0]);
        int endYear = Integer.parseInt(years[1]);

        if (dateInscription.getYear() < startYear || dateInscription.getYear() > endYear) {
            throw new IllegalArgumentException("La date d'inscription doit être dans l'année scolaire");
        }
    }
    public void validateDateWithinRange(LocalDate dateInscription, String anneeScolaire) {
        String[] years = anneeScolaire.split("[-/]");
        int startYear = Integer.parseInt(years[0]);
        int endYear = Integer.parseInt(years[1]);

        if (!((dateInscription.getYear() == startYear && dateInscription.getMonthValue() >= Month.OCTOBER.getValue()) ||
                (dateInscription.getYear() == endYear && dateInscription.getMonthValue() <= Month.MARCH.getValue()))) {
            throw new IllegalArgumentException("La date d'inscription doit être dans les 3 derniers mois de la première année ou les 3 premiers mois de la deuxième année");
        }
    }

}
