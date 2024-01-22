package com.epi.repository;

import com.epi.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer> {
    List<Entreprise> findByNbemployeBetween(int min, int max);
    List<Entreprise> findByVille(String ville);

}

