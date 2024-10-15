package com.sene.scolarite_api.controller;

import com.sene.scolarite_api.dto.ClasseDto;
import com.sene.scolarite_api.mapper.ClasseMapper;
import com.sene.scolarite_api.model.Classe;
import com.sene.scolarite_api.model.Filiere;
import com.sene.scolarite_api.model.Niveau;
import com.sene.scolarite_api.repository.ClasseRepository;
import com.sene.scolarite_api.repository.FiliereRepository;
import com.sene.scolarite_api.repository.NiveauRepository;
import com.sene.scolarite_api.service.ClasseService;
import com.sene.scolarite_api.service.FiliereService;
import com.sene.scolarite_api.service.ValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ClasseController {
    private final FiliereRepository filiereRepository;
    private final NiveauRepository niveauRepository;
    private final ClasseRepository classeRepository;
    private final ClasseMapper classeMapper;
    private final ClasseService classeService;
    private final ValidatorService validatorService;
    private final FiliereService filiereService;

    public ClasseController(FiliereRepository filiereRepository, NiveauRepository niveauRepository, ClasseRepository classeRepository, ClasseMapper classeMapper, ClasseService classeService, ValidatorService validatorService, FiliereService filiereService) {
        this.filiereRepository = filiereRepository;
        this.niveauRepository = niveauRepository;
        this.classeRepository = classeRepository;
        this.classeMapper = classeMapper;
        this.classeService = classeService;
        this.validatorService = validatorService;
        this.filiereService = filiereService;
    }

    //liste des classes
    @GetMapping("/classes")
    public ResponseEntity<?> getClasses(){
        List<Classe> classes = classeRepository.findAll();
        if (classes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas de classes dans la base de données");
        }else {
            return ResponseEntity.ok(classes);
        }
    }

   //liste des filieres
    @GetMapping("/filieres")
    public ResponseEntity<?> getFilieres(){
        List<Filiere> filieres = filiereRepository.findAll();
        if (filieres.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas de filieres dans la base de données");
        }else {
            return ResponseEntity.ok(filieres);
        }
    }
    //liste des niveaux
    @GetMapping("/niveaux")
    public ResponseEntity<?> getNiveaux() {
        List<Niveau> niveaux = niveauRepository.findAll();
        if (niveaux.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pas de niveau dans la base de données");
        } else {
            return ResponseEntity.ok(niveaux);
        }
    }

    // Récupère une classe à partir de son id
    @GetMapping("/classe/by-id/{id}")
    public ResponseEntity<?> getClasse(@PathVariable int id) {
        Optional<Classe> classe = classeRepository.findById(id);
        if (classe.isPresent()) {
            return ResponseEntity.ok(classe.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("cette classe n'existe pas");
        }
    }
    // Récupère une classe à partir de son code
    @GetMapping("/classe/by-code/{code}")
    public ResponseEntity<?> getClasseByCode(@PathVariable String code) {
        Optional<Classe> classe = classeRepository.findByCode(code);
        if (classe.isPresent()) {
            return ResponseEntity.ok(classe.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("une classe ayant ce code n'existe pas");
        }
    }
    // Liste des classes associées à une filière par son ID
    @GetMapping("/filiere/{filiereId}/classes")
    public ResponseEntity<?> getClassesByFiliereId(@PathVariable int filiereId) {
        List<Classe> classes = classeRepository.findByFiliereId(filiereId);
        if (classes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucune classe trouvée pour cette filière");
        } else {
            return ResponseEntity.ok(classes);
        }
    }

    @PostMapping("/classes")
    public ResponseEntity<?> addClasse(@RequestBody ClasseDto classeDto) {
        try {
            // Valider le libellé unique
            classeService.validateUniqueLibelle(classeDto.getLibelleDto());
            // Valider que le libellé est obligatoire
            validatorService.isVide(classeDto.getLibelleDto());
            // Valider que la mensualité est positive
            classeService.validatePositiveping(classeDto.getMensualiteDto());
            // Valider que les autres frais sont positifs
            classeService.validatePositiveping(classeDto.getAutreFraisDto());
            // Valider que les frais d'inscription sont positifs
            classeService.validatePositiveping(classeDto.getFraisInscriptionDto());
            validatorService.isNull(classeDto.getFiliereDto());
            validatorService.isNull(classeDto.getNiveauDto());
            Optional<Filiere> filiere = filiereRepository.findById(classeDto.getFiliereDto().getIdDto());
            Optional<Niveau> niveau = niveauRepository.findById(classeDto.getNiveauDto().getIdDto());
            if (!filiereService.isFiliereExists(classeDto.getFiliereDto().getIdDto())) {
                filiereService.Affiche();
            }
            // Valider que le niveau existe
            if (!filiereService.isNiveauExists(classeDto.getNiveauDto().getIdDto())) {
                filiereService.Affiche();
            }
            // Construire le code de la classe
            String code = niveau.get().getLibelle() + "-" + filiere.get().getLibelle();

            // Mapper le DTO vers l'entité
            Classe classe = classeMapper.entityClasseDtoToClasse(classeDto);
            classe.setFiliere(filiere.get());
            classe.setNiveau(niveau.get());
            classe.setCode(code);

            // Sauvegarder la nouvelle classe
            classeRepository.save(classe);
            return ResponseEntity.status(HttpStatus.CREATED).body(classe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
