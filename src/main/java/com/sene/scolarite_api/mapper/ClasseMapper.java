package com.sene.scolarite_api.mapper;

import com.sene.scolarite_api.dto.ClasseDto;
import com.sene.scolarite_api.model.Classe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClasseMapper {

    private final FiliereMapper filiereMapper;
    private final NiveauMapper niveauMapper;

    @Autowired
    public ClasseMapper(FiliereMapper filiereMapper, NiveauMapper niveauMapper) {
        this.filiereMapper = filiereMapper;
        this.niveauMapper = niveauMapper;
    }
    public ClasseDto entityClasseToClasseDto(Classe classe){
        return ClasseDto.builder()
                .idDto(classe.getId())
                .codeDto(classe.getCode())
                .libelleDto(classe.getLibelle())
                .fraisInscriptionDto(classe.getFraisInscription())
                .mensualiteDto(classe.getMensualite())
                .autreFraisDto(classe.getAutreFrais())
                .filiereDto(filiereMapper.entityToFiliereDto(classe.getFiliere()))
                .niveauDto(niveauMapper.entityToNiveauDto(classe.getNiveau()))
                .build();
    }
    public Classe entityClasseDtoToClasse(ClasseDto classeDto){
        return Classe.builder()
                .id(classeDto.getIdDto())
                .code(classeDto.getCodeDto())
                .libelle(classeDto.getLibelleDto())
                .fraisInscription(classeDto.getFraisInscriptionDto())
                .mensualite(classeDto.getMensualiteDto())
                .autreFrais(classeDto.getAutreFraisDto())
                .filiere(filiereMapper.dtoToFiliere(classeDto.getFiliereDto()))
                .niveau(niveauMapper.dtoToNiveau(classeDto.getNiveauDto()))
                .build();
    }

}
