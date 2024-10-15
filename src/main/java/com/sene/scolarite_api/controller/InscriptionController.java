package com.sene.scolarite_api.controller;

import com.sene.scolarite_api.dto.ClasseDto;
import com.sene.scolarite_api.dto.InscriptionDto;
import com.sene.scolarite_api.mapper.InscriptionMapper;
import com.sene.scolarite_api.model.*;
import com.sene.scolarite_api.repository.ClasseRepository;
import com.sene.scolarite_api.repository.EtudiantRepository;
import com.sene.scolarite_api.repository.InscriptionRepository;
import com.sene.scolarite_api.repository.PeriodeRepository;
import com.sene.scolarite_api.service.InscriptionService;
import com.sene.scolarite_api.service.ValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class InscriptionController {
    private final EtudiantRepository etudiantRepository;
    private final InscriptionRepository inscriptionRepository;
    private final ClasseRepository classeRepository;
    private final PeriodeRepository periodeRepository;
    private final InscriptionMapper inscriptionMapper;
    private final ValidatorService validatorService;
    private final InscriptionService inscriptionService;

    public InscriptionController(EtudiantRepository etudiantRepository, InscriptionRepository inscriptionRepository, ClasseRepository classeRepository, PeriodeRepository periodeRepository, InscriptionMapper inscriptionMapper, ValidatorService validatorService, InscriptionService inscriptionService) {
        this.etudiantRepository = etudiantRepository;
        this.inscriptionRepository = inscriptionRepository;
        this.classeRepository = classeRepository;
        this.periodeRepository = periodeRepository;
        this.inscriptionMapper = inscriptionMapper;
        this.validatorService = validatorService;
        this.inscriptionService = inscriptionService;
    }
    //recuperes tout les etudiants
    @GetMapping("/etudiants")
    public ResponseEntity<?> getEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        if (etudiants.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun étudiant trouvé dans la base de données");
        } else {
            return ResponseEntity.ok(etudiants);
        }
    }

    //recuperes tout les etudiants
    @GetMapping("/inscriptions")
    public ResponseEntity<?> getInscriptions() {
        List<Inscription> inscriptions = inscriptionRepository.findAll();
        if (inscriptions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune inscription trouvé dans la base de données");
        } else {
            return ResponseEntity.ok(inscriptions);
        }
    }
   //recupere un etudiant a partir de son matricule
    @GetMapping("/etudiant/by-matricule/{matricule}")
    public ResponseEntity<?> getEtudiantByMatricule(@PathVariable String matricule) {
        Optional<Etudiant> etudiant = etudiantRepository.findByMatricule(matricule);
        if (etudiant.isPresent()) {
            return ResponseEntity.ok(etudiant.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun étudiant trouvé avec le matricule " + matricule);
        }
    }
    //recupere les inscrits d'une dans une annee scolaire
    @GetMapping("/classe/inscriptions/{classeId}/{anneeScolaire}")
    public ResponseEntity<?> getInscriptionsByClasseAndAnneeScolaire(@PathVariable int classeId, @PathVariable String anneeScolaire) {
        if (!classeRepository.existsById(classeId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Classe avec id = " + classeId + " n'existe pas");
        }
        if (!anneeScolaire.matches("\\d{4}-\\d{4}") && !anneeScolaire.matches("\\d{4}/\\d{4}")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Année scolaire invalide. Formats acceptés : yyyy-yyyy ou yyyy/yyyy");
        }
        List<Inscription> inscriptions = inscriptionRepository.findInscritsByClasseIdAndAnneeScolaire(classeId, anneeScolaire);
        if (inscriptions.isEmpty()) {
            boolean anneeScolaireExists = inscriptionRepository.existsByAnneeScolaire(anneeScolaire);
            if (!anneeScolaireExists) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Année scolaire " + anneeScolaire + " n'existe pas dans la base de données");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune inscription trouvée pour la classe avec ID " + classeId + " pour l'année scolaire " + anneeScolaire);
        } else {
            return ResponseEntity.ok(inscriptions);
        }
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<?> getCurrentInscriptionByEtudiant(@PathVariable int etudiantId) {
        Optional<Inscription> inscription = inscriptionRepository.findInscriptionEtudiantEnCours(etudiantId, 1);
        if (inscription.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune inscription en cours trouvée pour l'étudiant avec ID " + etudiantId);
        } else {
            return ResponseEntity.ok(inscription.get());
        }
    }

    //recupere la liste des inscriptions dun etudiant
    @GetMapping("/id/{etudiantId}/inscriptions")
    public ResponseEntity<?> getInscriptionsByEtudiant(@PathVariable int etudiantId) {
        List<Inscription> inscriptions = inscriptionRepository.findInscriptionsEtudiant(etudiantId);
        if (inscriptions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune inscription trouvée pour l'étudiant avec ID " + etudiantId);
        } else {
            return ResponseEntity.ok(inscriptions);
        }
    }

    // Récupère la liste des inscriptions d'un étudiant à partir de son matricule
    @GetMapping("/matricule/{matricule}/inscriptions")
    public ResponseEntity<?> getInscriptionsByEtudiantMatricule(@PathVariable String matricule) {
        List<Inscription> inscriptions = inscriptionRepository.findInscriptionsByMatricule(matricule);
        if (inscriptions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune inscription trouvée pour l'étudiant avec le matricule " + matricule);
        } else {
            return ResponseEntity.ok(inscriptions);
        }
    }

    @PostMapping("/inscriptions")
    public ResponseEntity<?> addInscription(@RequestBody InscriptionDto inscriptionDto) {
        try {
            // Valider l'année scolaire
            validatorService.isVide(inscriptionDto.getAnneeScolaireDto());
            validatorService.isVideDate(inscriptionDto.getDateInscriptionDto(),"dateInscription");
            validatorService.validateAnneeScolaireFormat(inscriptionDto.getAnneeScolaireDto());
            inscriptionService.validateDateInAnneeScolaire(inscriptionDto.getDateInscriptionDto(),inscriptionDto.getAnneeScolaireDto());
            inscriptionService.validateDateWithinRange(inscriptionDto.getDateInscriptionDto(),inscriptionDto.getAnneeScolaireDto());

            // Valider classe ou etudiant ou periodeInscription obligatoire
            validatorService.isNull(inscriptionDto.getClasseDto());
            validatorService.isNull(inscriptionDto.getEtudiantDto());
            validatorService.isNull(inscriptionDto.getPeriodeInscriptionDto());

            // Vérifier l'existence de l'étudiant
            Optional<Etudiant> etudiant = etudiantRepository.findById(inscriptionDto.getEtudiantDto().getIdDto());
            if (!inscriptionService.isEtudiantExists(inscriptionDto.getEtudiantDto().getIdDto())) {
                inscriptionService.Affiche();
            }
            Optional<Classe> classe = classeRepository.findById(inscriptionDto.getClasseDto().getIdDto());
            if (!inscriptionService.isClasseExists(inscriptionDto.getClasseDto().getIdDto())) {
                inscriptionService.Affiche();
            }
            Optional<PeriodeInscription> periodeInscription = periodeRepository.findById(inscriptionDto.getPeriodeInscriptionDto().getIdDto());
            if (!inscriptionService.isPeriodeExists(inscriptionDto.getPeriodeInscriptionDto().getIdDto())) {
                inscriptionService.Affiche();
            }

            // Mapper le DTO vers l'entité
            Inscription inscription = inscriptionMapper.InscriptionDtoToentityInscription(inscriptionDto);
            inscription.setClasse(classe.get());
            inscription.setEtudiant(etudiant.get());
            inscription.setPeriodeInscription(periodeInscription.get());
            // Sauvegarder la nouvelle inscription
            inscriptionRepository.save(inscription);
            return ResponseEntity.status(HttpStatus.CREATED).body(inscription);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
