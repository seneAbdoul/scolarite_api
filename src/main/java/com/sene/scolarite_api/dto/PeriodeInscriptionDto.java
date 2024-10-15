package com.sene.scolarite_api.dto;

import com.sene.scolarite_api.model.Inscription;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodeInscriptionDto {
    private int idDto;
    private LocalDate dateDebutDto;
    private LocalDate dateFinDto;
    private int statutDto;
    private List<InscriptionDto> inscriptionDtos;
}
