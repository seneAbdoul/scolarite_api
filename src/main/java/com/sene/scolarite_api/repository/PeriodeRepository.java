package com.sene.scolarite_api.repository;

import com.sene.scolarite_api.model.PeriodeInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodeRepository extends JpaRepository<PeriodeInscription, Integer> {

}
