package com.sene.scolarite_api.repository;

import com.sene.scolarite_api.model.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Integer> {
}
