package com.sene.scolarite_api.mapper;

import com.sene.scolarite_api.dto.ClasseDto;
import com.sene.scolarite_api.dto.InscriptionDto;
import com.sene.scolarite_api.model.Classe;
import com.sene.scolarite_api.model.Inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapper {
    private final ClasseMapper classeMapper;
    private final EtudiantMapper etudiantMapper;
    private final PeriodeInscriptionMapper periodeInscriptionMapper;

    @Autowired
    public InscriptionMapper(ClasseMapper classeMapper, EtudiantMapper etudiantMapper, PeriodeInscriptionMapper periodeInscriptionMapper) {
        this.classeMapper = classeMapper;
        this.etudiantMapper = etudiantMapper;
        this.periodeInscriptionMapper = periodeInscriptionMapper;
    }
    public InscriptionDto entityInscriptionToInscriptionDto(Inscription inscription){
        return InscriptionDto.builder()
                .idDto(inscription.getId())
                .dateInscriptionDto(inscription.getDateInscription())
                .anneeScolaireDto(inscription.getAnneeScolaire())
                .classeDto(classeMapper.entityClasseToClasseDto(inscription.getClasse()))
                .etudiantDto(etudiantMapper.entityToEtudiantDto(inscription.getEtudiant()))
                .periodeInscriptionDto(periodeInscriptionMapper.entityToPeriodeInscriptionDto(inscription.getPeriodeInscription()))
                .build();
    }
    public Inscription InscriptionDtoToentityInscription(InscriptionDto inscriptionDto){
        return Inscription.builder()
                .id(inscriptionDto.getIdDto())
                .dateInscription(inscriptionDto.getDateInscriptionDto())
                .anneeScolaire(inscriptionDto.getAnneeScolaireDto())
                .classe(classeMapper.entityClasseDtoToClasse(inscriptionDto.getClasseDto()))
                .etudiant(etudiantMapper.EtudiantDtoToentity(inscriptionDto.getEtudiantDto()))
                .periodeInscription(periodeInscriptionMapper.PeriodeInscriptionDtoToentity(inscriptionDto.getPeriodeInscriptionDto()))
                .build();
    }
}
