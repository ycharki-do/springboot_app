package com.spring.controller;


import ch.qos.logback.core.rolling.helper.RollingCalendar;
import com.spring.model.*;
import com.spring.repository.ProdRefRepository;
import com.spring.repository.ProduitRepository;
import com.spring.repository.ProjetRepository;
import com.spring.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.util.BuddhistCalendar;
import sun.util.resources.cldr.ar.CalendarData_ar_MA;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/reference")
@CrossOrigin
public class ReferenceController {

    @Autowired
    private ReferenceRepository refRep;
    @Autowired
    private ProduitRepository prodRep;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ProdRefRepository prodRefRepository;



    @PostMapping("/add")
    public Reference addReference(@RequestBody Reference reference){
        return refRep.save(reference);
    }

    @GetMapping("/list")
    public List<Reference> getAllReferences(){
        return refRep.findAll();
    }

    @GetMapping("/listFree")
    public List<Reference> getFreeCodes(){
        return refRep.findByStatut(0);
    }

    @PutMapping("/update")
    public Reference updateReference(@RequestBody Reference reference){
        Reference ref=refRep.findById(reference.getId()).orElse(null);
        return ref!=null?refRep.save(reference):null;
    }

    @DeleteMapping("/delete")
    public int deleteReference(@RequestParam int id){
        refRep.delete(refRep.findById(id).orElse(new Reference()));
        return refRep.findById(id).orElse(null)==null?1:0;
    }

    @GetMapping
    public Reference getReferenceById(@RequestParam int id){
        Calendar gregorianCalendar = new GregorianCalendar();
        return refRep.findById(id).orElse(null);
    }

    // There are some problems here related to referenced products

}
//
//class Model {
//    private int id_ref;
//    private int id_prod;
//    private String date_attr;
//
//    public int getId_ref() {
//        return id_ref;
//    }
//
//    public void setId_ref(int id_ref) {
//        this.id_ref = id_ref;
//    }
//
//    public int getId_prod() {
//        return id_prod;
//    }
//
//    public void setId_prod(int id_prod) {
//        this.id_prod = id_prod;
//    }
//
//    public String getDate_attr() {
//        return date_attr;
//    }
//
//    public void setDate_attr(String date_attr) {
//        this.date_attr = date_attr;
//    }
//}