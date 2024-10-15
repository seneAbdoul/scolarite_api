package com.sene.scolarite_api.controller;

import com.sene.scolarite_api.dto.FiliereDto;
import com.sene.scolarite_api.mapper.FiliereMapper;
import com.sene.scolarite_api.model.Filiere;
import com.sene.scolarite_api.repository.FiliereRepository;
import com.sene.scolarite_api.service.FiliereService;
import com.sene.scolarite_api.service.ValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FiliereController {
    private final FiliereMapper filiereMapper;
    private final FiliereRepository filiereRepository;
    private final ValidatorService validatorService;
    private final FiliereService filiereService;

    public FiliereController(FiliereMapper filiereMapper, FiliereRepository filiereRepository, ValidatorService validatorService, FiliereService filiereService) {
        this.filiereMapper = filiereMapper;
        this.filiereRepository = filiereRepository;
        this.validatorService = validatorService;
        this.filiereService = filiereService;
    }

    @PostMapping("/filieres")
    public ResponseEntity<?> addFiliere(@RequestBody FiliereDto filiereDto) {
        try {
            // Valider le champ obligatoire
            validatorService.isVide(filiereDto.getLibelleDto());
            // Valider l'unicité du libellé
            filiereService.validateUniqueLibelle(filiereDto.getLibelleDto());
            // Mapper le DTO vers l'entité
            Filiere filiere = filiereMapper.dtoToFiliere(filiereDto);
            filiereRepository.save(filiere);
            return ResponseEntity.status(HttpStatus.CREATED).body(filiere);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
