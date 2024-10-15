package com.sene.scolarite_api.mapper;

import com.sene.scolarite_api.dto.FiliereDto;
import com.sene.scolarite_api.dto.NiveauDto;
import com.sene.scolarite_api.model.Filiere;
import com.sene.scolarite_api.model.Niveau;
import org.springframework.stereotype.Component;

@Component
public class NiveauMapper {

    public NiveauDto entityToNiveauDto(Niveau niveau) {
        if (niveau == null) {
            return null;
        }
        return NiveauDto.builder()
                .idDto(niveau.getId())
                .libelleDto(niveau.getLibelle())
                .build();
    }

    public Niveau dtoToNiveau(NiveauDto niveauDto) {
        if (niveauDto == null) {
            return null;
        }
        return Niveau.builder()
                .id(niveauDto.getIdDto())
                .libelle(niveauDto.getLibelleDto())
                .build();
    }
}
