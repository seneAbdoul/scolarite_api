package com.sene.scolarite_api.service;

import com.sene.scolarite_api.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;

    public void isValidEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
            throw new IllegalArgumentException("L'e-mail n'est pas valide");
        }
        if (etudiantRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("L'e-mail existe déjà");
        }
    }

    public void validateDateNaissance(LocalDate dateNaissance) {
        LocalDate today = LocalDate.now();
        LocalDate minDate = today.minusYears(80);
        LocalDate maxDate = today.minusYears(18);
        if (dateNaissance.isBefore(minDate) || dateNaissance.isAfter(maxDate)) {
            throw new IllegalArgumentException("La date de naissance doit être comprise entre 18 et 70 ans.");
        }
    }
}
