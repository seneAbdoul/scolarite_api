package com.sene.scolarite_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;
@Builder
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 30)
    private String matricule;
    @Column(length = 30)
    private String nom;
    @Column(length = 30)
    private String prenom;
    @Column(length = 30)
    private String telphone;
    @Column(length = 30)
    private String email;
    @Column(length = 30)
    private String adresse;
    @Column
    private LocalDate dateNaissance;
    @OneToMany(mappedBy = "etudiant")
    @JsonManagedReference
    private List<Inscription> inscriptions;

    public Etudiant() {
    }

    public Etudiant(int id, String matricule, String nom, String prenom, String telphone, String email, String adresse, LocalDate dateNaissance, List<Inscription> inscriptions) {
        this.id = id;
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.telphone = telphone;
        this.email = email;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.inscriptions = inscriptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public List<Inscription> getInscriptions() {
        return inscriptions;
    }

    public void setInscriptions(List<Inscription> inscriptions) {
        this.inscriptions = inscriptions;
    }
}
