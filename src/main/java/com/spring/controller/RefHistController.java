package com.spring.controller;


import com.spring.model.HistRef;
import com.spring.model.Produit;
import com.spring.model.Reference;
import com.spring.repository.ProduitRepository;
import com.spring.repository.RefHistRepository;
import com.spring.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("histref")
public class RefHistController {


    @Autowired
    private RefHistRepository refHistRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ReferenceRepository referenceRepository;

    @PutMapping("/add")
    public HistRef addHistRef(HistRef histRef){
        return refHistRepository.save(histRef);
    }

    @GetMapping("/list")
    public List<HistRef> getAllHistRef(){
        return refHistRepository.findAll();
    }

    @GetMapping("/getByProduit")
    public List<HistRef> getHistRefByProduit(@RequestParam int id){
        return refHistRepository.findByProduit(produitRepository.findById(id).orElse(null));
    }

    @GetMapping("/getByRef")
    public List<HistRef> getHistRefByRef(@RequestParam int id){
        return refHistRepository.findByReference(referenceRepository.findById(id).orElse(null));
    }
}
