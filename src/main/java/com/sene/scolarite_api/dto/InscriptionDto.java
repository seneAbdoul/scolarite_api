package com.sene.scolarite_api.dto;

import com.sene.scolarite_api.model.Classe;
import com.sene.scolarite_api.model.Etudiant;
import com.sene.scolarite_api.model.PeriodeInscription;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionDto {
    private int idDto;
    private LocalDate dateInscriptionDto;
    private String anneeScolaireDto;
    private EtudiantDto etudiantDto;
    private ClasseDto classeDto;
    private PeriodeInscriptionDto periodeInscriptionDto;
}
