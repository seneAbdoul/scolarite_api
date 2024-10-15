package com.sene.scolarite_api.controller;

import com.sene.scolarite_api.dto.FiliereDto;
import com.sene.scolarite_api.dto.PeriodeInscriptionDto;
import com.sene.scolarite_api.mapper.PeriodeInscriptionMapper;
import com.sene.scolarite_api.model.Filiere;
import com.sene.scolarite_api.model.PeriodeInscription;
import com.sene.scolarite_api.repository.PeriodeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Controller
public class PeriodeInscriptionController {
    private final PeriodeInscriptionMapper periodeInscriptionMapper;
    private final PeriodeRepository periodeRepository;

    public PeriodeInscriptionController(PeriodeInscriptionMapper periodeInscriptionMapper, PeriodeRepository periodeRepository) {
        this.periodeInscriptionMapper = periodeInscriptionMapper;
        this.periodeRepository = periodeRepository;
    }
    @PostMapping("/periodes")
    public ResponseEntity<?> addPeriode(@RequestBody PeriodeInscriptionDto periodeInscriptionDto) {
        // Vérifier si la date de début est supérieure à la date de fin
        LocalDate dateDebut = periodeInscriptionDto.getDateDebutDto();
        LocalDate dateFin = periodeInscriptionDto.getDateFinDto();

        if (dateDebut == null || dateFin == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La date est obligatoire");
        }

        if (dateDebut.isAfter(dateFin)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La date de début ne doit pas être supérieure à la date de fin.");
        }

        if (dateFin.isBefore(dateDebut)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La date de fin ne doit pas être inférieure à la date de début.");
        }

        // Mapper le DTO vers l'entité
        PeriodeInscription periodeInscription = periodeInscriptionMapper.PeriodeInscriptionDtoToentity(periodeInscriptionDto);
        periodeRepository.save(periodeInscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(periodeInscription);
    }

}
