package com.sene.scolarite_api.mapper;

import com.sene.scolarite_api.dto.FiliereDto;
import com.sene.scolarite_api.model.Filiere;
import org.springframework.stereotype.Component;

@Component
public class FiliereMapper {

    public FiliereDto entityToFiliereDto(Filiere filiere) {
        if (filiere == null) {
            return null;
        }
        return FiliereDto.builder()
                .idDto(filiere.getId())
                .libelleDto(filiere.getLibelle())
                .build();
    }

    public Filiere dtoToFiliere(FiliereDto filiereDto) {
        if (filiereDto == null) {
            return null;
        }
        return Filiere.builder()
                .id(filiereDto.getIdDto())
                .libelle(filiereDto.getLibelleDto())
                // Ajoutez d'autres propriétés si nécessaire
                .build();
    }
}
