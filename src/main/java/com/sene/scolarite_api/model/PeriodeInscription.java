package com.sene.scolarite_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
@Entity
public class PeriodeInscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private LocalDate dateDebut;
    @Column
    private LocalDate dateFin;
    @Column(name = "statut")
    private int statut;
    @OneToMany(mappedBy = "periodeInscription")
    @JsonManagedReference
    private List<Inscription> inscriptions;

    public PeriodeInscription() {
    }

    public PeriodeInscription(int id, LocalDate dateDebut, LocalDate dateFin, int statut, List<Inscription> inscriptions) {
        this.id = id;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = statut;
        this.inscriptions = inscriptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
