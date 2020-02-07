package com.spring.controller;

import com.spring.model.*;
import com.spring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/prodRef")
@CrossOrigin
public class ProdRefController {

    @Autowired
    private ProdRefRepository prodRefRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private RefCodeRepository refCodeRepository;
    @Autowired
    private RefHistRepository refHistRepository;

    @GetMapping("/getByProduit")
    public List<ProdRef> getByProduit(@RequestParam int id){
        Produit pr= produitRepository.findById(id).orElse(null);
        return prodRefRepository.findByProduit(pr);
    }

    @GetMapping("/getByReference")
    public List<ProdRef> getByReference(@RequestParam int id){
        Reference ref= referenceRepository.findById(id).orElse(null);
        return prodRefRepository.findByReference(ref);
    }
    @GetMapping("/list")
    public List<ProdRef> getAll(){
        return prodRefRepository.findAll();
    }

    @PostMapping("/add")
    public ProdRef addProdRef(@RequestBody ProdRef prodRef){
        if(checkReference(prodRef.getReference())){
           // if(checkProduit(prodRef.getProduit())){
                referenceRepository.save(prodRef.getReference());
                return prodRefRepository.save(prodRef);
          //  }
        }
        return null;
    }

    public boolean checkProduit(Produit pr){
        return prodRefRepository.findByProduit(pr).isEmpty();
    }

    public boolean checkReference(Reference ref){
        if (referenceRepository.findById(ref.getId()).get().getStatut() != 0) {
            return false;
        }
        List<ProdRef> prodRefs = prodRefRepository.findByReference(ref);
        if(prodRefs.isEmpty()){
            return true;
        }
        return false;
    }

    @DeleteMapping("/delete")
        public int deleteProdRef(@RequestParam int id){
        ProdRef prodRef = prodRefRepository.findById(id).orElse(null);
        if(prodRef != null){
            HistRef histRef = new HistRef();
            histRef.setProduit(prodRef.getProduit());
            histRef.setReference(prodRef.getReference());
            histRef.setDate_debut(prodRef.getDate_attr());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            histRef.setDate_fin(now.toString());
            refHistRepository.save(histRef);
            prodRef.getReference().setStatut(0);
            referenceRepository.save(prodRef.getReference());
            prodRefRepository.delete(prodRef);
            return 1;
        }
        return 0;
    }

    @GetMapping("/all")
    public List<FullModel> getALLLLL(){
        List<RefCode> refCodes = new ArrayList<>();
        List<FullModel> fullModels = new ArrayList<>();
        List<ProdRef> prodRefs = new ArrayList<>();
        codeRepository.findAll().forEach( pr ->{
            List<RefCode> prodRefs1=refCodeRepository.findByCode(pr);
            if (prodRefs1.isEmpty()){
                RefCode refCode = new RefCode();
                refCode.setCode(pr);
                refCodes.add(refCode);
            }else{
                prodRefs1.forEach( prodRef -> {
                    refCodes.add(prodRef);
                });
            }
        });
        refCodes.forEach( val -> {
            if(val.getReference() == null ){
                FullModel fullModel = new FullModel();
                fullModel.setRefCode(val);
                fullModel.setProdRef(new ProdRef());
                fullModels.add(fullModel);
            }else{
                List<ProdRef> prodRefs1=prodRefRepository.findByReference(val.getReference());
                if( prodRefs1.isEmpty()){
                    FullModel fullModel = new FullModel();
                    fullModel.setProdRef(new ProdRef());
                    fullModel.setRefCode(val);
                    fullModels.add(fullModel);
                }else{
                    prodRefs1.forEach( v -> {
                        FullModel fullModel = new FullModel();
                        fullModel.setProdRef(v);
                        fullModel.setRefCode(val);
                        fullModels.add(fullModel);
                    });
                }
            }
        });
        return fullModels;
    }
}
