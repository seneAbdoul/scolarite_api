package com.sene.scolarite_api.mapper;

import com.sene.scolarite_api.dto.EtudiantDto;
import com.sene.scolarite_api.dto.FiliereDto;
import com.sene.scolarite_api.model.Etudiant;
import com.sene.scolarite_api.model.Filiere;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {
    public EtudiantDto entityToEtudiantDto(Etudiant etudiant) {
        if (etudiant == null) {
            return null;
        }
        return EtudiantDto.builder()
                .idDto(etudiant.getId())
                .matriculeDto(etudiant.getMatricule())
                .nomDto(etudiant.getNom())
                .prenomDto(etudiant.getPrenom())
                .telphoneDto(etudiant.getTelphone())
                .emailDto(etudiant.getEmail())
                .adresseDto(etudiant.getAdresse())
                .dateNaissanceDto(etudiant.getDateNaissance())
                .build();
    }
    public Etudiant EtudiantDtoToentity(EtudiantDto etudiantDto) {
        if (etudiantDto == null) {
            return null;
        }
        return Etudiant.builder()
                .id(etudiantDto.getIdDto())
                .matricule(etudiantDto.getMatriculeDto())
                .nom(etudiantDto.getNomDto())
                .prenom(etudiantDto.getPrenomDto())
                .telphone(etudiantDto.getTelphoneDto())
                .email(etudiantDto.getEmailDto())
                .adresse(etudiantDto.getAdresseDto())
                .dateNaissance(etudiantDto.getDateNaissanceDto())
                .build();
    }
}
