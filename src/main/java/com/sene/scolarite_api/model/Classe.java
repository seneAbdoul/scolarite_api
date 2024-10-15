package com.sene.scolarite_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Builder
@Entity
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 30)
    private String code;
    @Column(length = 30)
    private String libelle;
    @Column
    private int fraisInscription;
    @Column
    private int mensualite;
    @Column
    private int autreFrais;

    @ManyToOne
    @JoinColumn(name = "filiere_id")
    @JsonBackReference(value = "filiere-classe")
    private Filiere filiere;

    @ManyToOne
    @JoinColumn(name = "niveau_id")
    @JsonBackReference(value = "niveau-classe")
    private Niveau niveau;

    @OneToMany(mappedBy = "classe")
    @JsonManagedReference
    private List<Inscription> inscriptions;

    public Classe() {
    }

    public Classe(int id, String code, String libelle, int fraisInscription, int mensualite, int autreFrais, Filiere filiere, Niveau niveau, List<Inscription> inscriptions) {
        this.id = id;
        this.code = code;
        this.libelle = libelle;
        this.fraisInscription = fraisInscription;
        this.mensualite = mensualite;
        this.autreFrais = autreFrais;
        this.filiere = filiere;
        this.niveau = niveau;
        this.inscriptions = inscriptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getFraisInscription() {
        return fraisInscription;
    }

    public void setFraisInscription(int fraisInscription) {
        this.fraisInscription = fraisInscription;
    }

    public int getMensualite() {
        return mensualite;
    }

    public void setMensualite(int mensualite) {
        this.mensualite = mensualite;
    }

    public int getAutreFrais() {
        return autreFrais;
    }

    public void setAutreFrais(int autreFrais) {
        this.autreFrais = autreFrais;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
