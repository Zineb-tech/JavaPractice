package com.epi.controller;

import com.epi.entity.Entreprise;
import com.epi.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entreprise")

public class EntrepriseRestController {
    @Autowired
    EntrepriseRepository entrepriseRepository;
    @GetMapping("/all")
    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }
    @PostMapping("/add")
    public Entreprise addEntreprise(@RequestBody Entreprise e){
        return entrepriseRepository.save(e);
    }
    @GetMapping("/find/{id}")
    public Entreprise find(@PathVariable int id){
        return entrepriseRepository.findById(id).get();
    }
   @GetMapping("/getByVille/{ville}")
    public List<Entreprise> findByVille(@PathVariable("ville") String ville) {
        return entrepriseRepository.findByVille(ville);
    }

    @GetMapping("/findByNbEmployee/{min}/{max}")
    public List<Entreprise> findByNbEmployee(@PathVariable("min") int min, @PathVariable("max") int max) {
        return entrepriseRepository.findByNbemployeBetween(min,max);
    }

}

