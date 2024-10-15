package com.sene.scolarite_api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NiveauDto {
    private int idDto;
    private String libelleDto;
    private List<ClasseDto> classeDtos;
}
