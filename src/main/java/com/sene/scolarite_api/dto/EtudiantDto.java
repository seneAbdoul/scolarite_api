package com.sene.scolarite_api.dto;

import com.sene.scolarite_api.model.Inscription;
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
public class EtudiantDto {
    private int idDto;
    private String matriculeDto;
    private String nomDto;
    private String prenomDto;
    private String telphoneDto;
    private String emailDto;
    private String adresseDto;
    private LocalDate dateNaissanceDto;
    private List<InscriptionDto> inscriptionDtos;
}
