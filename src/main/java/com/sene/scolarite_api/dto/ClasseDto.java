package com.sene.scolarite_api.dto;

import com.sene.scolarite_api.model.Filiere;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseDto {
    private int idDto;
    private String codeDto;
    private String libelleDto;
    private int fraisInscriptionDto;
    private int mensualiteDto;
    private int autreFraisDto;
    private FiliereDto filiereDto;
    private NiveauDto niveauDto;
    private List<InscriptionDto> inscriptionDtos;

}
