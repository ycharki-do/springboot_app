package com.spring.controller;


import com.spring.model.*;
import com.spring.repository.CodeHistRepository;
import com.spring.repository.CodeRepository;
import com.spring.repository.RefCodeRepository;
import com.spring.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/refcode")
@CrossOrigin
public class RefCodeController {

    @Autowired
    private RefCodeRepository refCodeRepository;
    @Autowired
    private ReferenceRepository referenceRepository;
    @Autowired
    private CodeRepository codeRepository;
    @Autowired
    private CodeHistRepository histCodeRepository;

    @PostMapping("/add")
    public RefCode addRefCode(@RequestBody RefCode refCode){
        Reference reference=referenceRepository.findById(refCode.getReference() .getId()) .orElse(null);
        Code code=codeRepository.findById(refCode.getCode().getId()).orElse(null);
        if(reference!=null && code!=null && (code.getStatut()==0 || code.getStatut()==3)){
            if(refCodeRepository.findByReference(reference).isEmpty()){
                System.out.println("status :: "+refCode.getCode().getStatut());
                codeRepository.save(refCode.getCode());
                return refCodeRepository.save(refCode);
            }
        }
        return null;
    }

    @DeleteMapping("/delete")
    public int deleteRefCode(@RequestParam int id){
        RefCode rc = refCodeRepository.findById(id).orElse(null);
        if(rc!=null){
            HistCode histCode = new HistCode();
            histCode.setCode(rc.getCode());
            histCode.setReference(rc.getReference());
            histCode.setDate_debut(rc.getDate_attr());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            histCode.setDate_fin(now.toString());
            histCodeRepository.save(histCode);
            Code code=codeRepository.findById(rc.getCode().getId()).orElse(null);
            code.setStatut(0);
            codeRepository.save(code);
            refCodeRepository.delete(rc);
        }else{ return -1;}
        rc = refCodeRepository.findById(id).orElse(null);
        return rc!=null?0:1;
    }

    @GetMapping("/getByCode")
    public List<RefCode> getByCode(@RequestParam int id){
        Code code= codeRepository.findById(id).orElse(null);
        return refCodeRepository.findByCode(code);
    }

    @GetMapping("/getByReference")
    public List<RefCode> getByReference(@RequestParam int id){
        Reference ref= referenceRepository.findById(id).orElse(null);
        return refCodeRepository.findByReference(ref);
    }
}
