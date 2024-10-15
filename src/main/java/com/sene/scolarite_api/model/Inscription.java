package com.sene.scolarite_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
@Builder
@Entity
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private LocalDate dateInscription;
    @Column(length = 100)
    private String anneeScolaire;
    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    @JsonBackReference(value = "etudiant-inscription")
    private Etudiant etudiant;
    @ManyToOne
    @JoinColumn(name = "classe_id")
    @JsonBackReference(value = "classe-inscription")
    private Classe classe;
    @ManyToOne
    @JoinColumn(name = "periodeInscription_id")
    @JsonBackReference(value = "periodeInscription-inscription")
    private PeriodeInscription periodeInscription;

    public Inscription() {
    }

    public Inscription(int id, LocalDate dateInscription, String anneeScolaire, Etudiant etudiant, Classe classe, PeriodeInscription periodeInscription) {
        this.id = id;
        this.dateInscription = dateInscription;
        this.anneeScolaire = anneeScolaire;
        this.etudiant = etudiant;
        this.classe = classe;
        this.periodeInscription = periodeInscription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public PeriodeInscription getPeriodeInscription() {
        return periodeInscription;
    }

    public void setPeriodeInscription(PeriodeInscription periodeInscription) {
        this.periodeInscription = periodeInscription;
    }
}
