package com.spring.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.spring.model.ProdRef;
import com.spring.model.Produit;
import com.spring.model.Projet;
import com.spring.model.Reference;
import com.spring.repository.ProdRefRepository;
import com.spring.repository.ProduitRepository;
import com.spring.repository.ProjetRepository;
import com.spring.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produit")
@CrossOrigin
public class ProduitController {
    @Autowired
    private ProduitRepository prodRep;

    @Autowired
    private ProdRefRepository prodRefRepository;

    @Autowired
    private ProjetRepository projetRepository;

    @PostMapping("/add")
    public Produit addProduit(@RequestBody Produit produit){
        Projet projet=projetRepository.findById(produit.getProjet().getId()).orElse(null);
        produit.setProjet(projet);
        Produit pr=projet!=null?prodRep.save(produit):null;
        //System.out.printf("=====>> "+pr.getProjet().getId()+" ||| "+pr.getProjet().getProduits().get(0).getNom());
        //pr.setProjet(null);
        return pr;
    }

    @GetMapping("/list")
    public List<Produit> getAllProduit(){
        return prodRep.findAll();
    }

    @GetMapping("/")
    public Produit getProduitId(@RequestParam int id){
        return prodRep.findById(id).orElse(null);
    }


    @PutMapping("/update")
    public Produit updateProduit(@RequestBody Produit pr){
        Produit produit=prodRep.findById(pr.getId()).orElse(null);
        return produit!=null?prodRep.save(pr):null;
    }

    @DeleteMapping("/delete")
    public int deleteProduit(@RequestParam int id){
        prodRep.delete(prodRep.findById(id).orElse(new Produit()));
        return prodRep.findById(id).orElse(null)==null?1:0;
    }

    @GetMapping
    public Produit getProduitById(@RequestParam int id){
        return prodRep.findById(id).orElse(null);
    }

    @GetMapping("/null")
    public List<Produit> getAllNull(){
        return prodRep.findByProjetNull();
    }


}
