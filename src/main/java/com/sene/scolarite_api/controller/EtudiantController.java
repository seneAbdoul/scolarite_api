package com.sene.scolarite_api.controller;

import com.sene.scolarite_api.dto.ClasseDto;
import com.sene.scolarite_api.dto.EtudiantDto;
import com.sene.scolarite_api.mapper.EtudiantMapper;
import com.sene.scolarite_api.model.Classe;
import com.sene.scolarite_api.model.Etudiant;
import com.sene.scolarite_api.model.Filiere;
import com.sene.scolarite_api.model.Niveau;
import com.sene.scolarite_api.repository.EtudiantRepository;
import com.sene.scolarite_api.service.EtudiantService;
import com.sene.scolarite_api.service.ValidatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Controller
public class EtudiantController {
    private final EtudiantMapper etudiantMapper;
    private final EtudiantRepository etudiantRepository;
    private final ValidatorService validatorService;
    private final EtudiantService etudiantService;

    public EtudiantController(EtudiantMapper etudiantMapper, EtudiantRepository etudiantRepository, ValidatorService validatorService, EtudiantService etudiantService) {
        this.etudiantMapper = etudiantMapper;
        this.etudiantRepository = etudiantRepository;
        this.validatorService = validatorService;
        this.etudiantService = etudiantService;
    }
    @PostMapping("/etudiants")
    public ResponseEntity<?> addEtudiant(@RequestBody EtudiantDto etudiantDto) {
        try {
            // Valider les champs obligatoires et le format de l'email
            validatorService.isVide(etudiantDto.getNomDto());
            validatorService.isVide(etudiantDto.getPrenomDto());
            validatorService.isVide(etudiantDto.getTelphoneDto());
            validatorService.isVide(etudiantDto.getEmailDto());
            validatorService.isVide(etudiantDto.getAdresseDto());
            etudiantService.isValidEmail(etudiantDto.getEmailDto());
            validatorService.isVideDate(etudiantDto.getDateNaissanceDto(),"date Naissance");
            etudiantService.validateDateNaissance(etudiantDto.getDateNaissanceDto());
            // Récupérer le dernier matricule et incrémenter le numéro
            Etudiant lastEtudiant = etudiantRepository.findTopByOrderByIdDesc();
            int nextNumber = 1;
            if (lastEtudiant != null && lastEtudiant.getMatricule() != null) {
                String lastMatricule = lastEtudiant.getMatricule();
                String[] parts = lastMatricule.split("-");
                if (parts.length > 0) {
                    String numberPart = parts[0].substring(1); // Supprimer le 'M'
                    nextNumber = Integer.parseInt(numberPart) + 1;
                }
            }
            String year = String.valueOf(LocalDate.now().getYear());
            String newMatricule = String.format("M%05d-%s", nextNumber, year);
            // Mapper le DTO vers l'entité
            Etudiant etudiant = etudiantMapper.EtudiantDtoToentity(etudiantDto);
            etudiant.setMatricule(newMatricule);
            // Sauvegarder le nouveau étudiant
            etudiantRepository.save(etudiant);
            return ResponseEntity.status(HttpStatus.CREATED).body(etudiant);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
