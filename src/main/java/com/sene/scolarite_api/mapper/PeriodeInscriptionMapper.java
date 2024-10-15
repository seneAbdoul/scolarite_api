package com.sene.scolarite_api.mapper;

import com.sene.scolarite_api.dto.FiliereDto;
import com.sene.scolarite_api.dto.PeriodeInscriptionDto;
import com.sene.scolarite_api.model.Filiere;
import com.sene.scolarite_api.model.PeriodeInscription;
import org.springframework.stereotype.Component;

@Component
public class PeriodeInscriptionMapper {
    public PeriodeInscriptionDto entityToPeriodeInscriptionDto(PeriodeInscription periodeInscription) {
        if (periodeInscription == null) {
            return null;
        }
        return PeriodeInscriptionDto.builder()
                .idDto(periodeInscription.getId())
                .dateDebutDto(periodeInscription.getDateDebut())
                .dateFinDto(periodeInscription.getDateFin())
                .statutDto(periodeInscription.getStatut())
                .build();
    }
    public PeriodeInscription PeriodeInscriptionDtoToentity(PeriodeInscriptionDto periodeInscriptionDto) {
        if (periodeInscriptionDto == null) {
            return null;
        }
        return PeriodeInscription.builder()
                .id(periodeInscriptionDto.getIdDto())
                .dateDebut(periodeInscriptionDto.getDateDebutDto())
                .dateFin(periodeInscriptionDto.getDateFinDto())
                .statut(periodeInscriptionDto.getStatutDto())
                .build();
    }
}
