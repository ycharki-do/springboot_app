package com.spring.controller;

import com.spring.model.Code;
import com.spring.model.Reference;
import com.spring.repository.CodeRepository;
import com.spring.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/code")
@CrossOrigin
public class CodeController {

    @Autowired
    private CodeRepository codeRep;

    @GetMapping
    public Code getById(@RequestParam int id){
        return codeRep.findById(id).orElse(null);
    }

    @GetMapping("/name")
    public Code getByName(@RequestParam String name){
        return codeRep.findByNumero(name).orElse(null);
    }

    @GetMapping("/list")
    public List<Code> getAllCodes(){
        return codeRep.findAll();
    }

    @GetMapping("/listFree")
    public List<Code> getFreeCodes(){
        return codeRep.findByStatut(0);
    }

    @PostMapping("/add")
    public Code addCode(@RequestBody Code code){
        return codeRep.save(code);
    }

    @PutMapping("/update")
    public Code updateCode(@RequestBody Code code){
        Code cd=codeRep.findById(code.getId()).orElse(null);
        return cd!=null?codeRep.save(code):null;
    }

    @DeleteMapping("/delete")
    public int deleteCode(@RequestParam int id){
        codeRep.delete(codeRep.findById(id).orElse(new Code()));
        return codeRep.findById(id).orElse(null)==null?1:0;
    }
}
