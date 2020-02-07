package com.spring.controller;


import com.spring.model.Produit;
import com.spring.model.Projet;
import com.spring.repository.CodeRepository;
import com.spring.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projet")
@CrossOrigin
public class ProjetController {

    @Autowired
    private ProjetRepository projetRepository;

    @PostMapping("/add")
    public Projet addProjet(@RequestBody Projet projet){
        return projetRepository.save(projet);
    }

    @GetMapping("/list")
    public List<Projet> getAllProjets(){
        return projetRepository.findAll();
    }

    @PutMapping("/update")
    public Projet updateProjet(@RequestBody Projet pr){
        Projet projet=projetRepository.findById(pr.getId()).orElse(null);
        return projet!=null?projetRepository.save(pr):null;
    }

    @DeleteMapping("/delete")
    public int deleteProjet(@RequestParam int id){
        projetRepository.delete(projetRepository.findById(id).orElse(new Projet()));
        return projetRepository.findById(id).orElse(null)==null?1:0;
    }

    @GetMapping
    public Projet getProjetById(@RequestParam int id){
        return projetRepository.findById(id).orElse(null);
    }
}
