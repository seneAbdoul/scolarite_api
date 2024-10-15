package com.sene.scolarite_api.dto;

import com.sene.scolarite_api.model.Classe;
import lombok.*;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FiliereDto {
    private int idDto;
    private String libelleDto;
    private List<ClasseDto> classeDtos;
}
