package com.spring.controller;

import com.spring.model.HistCode;
import com.spring.model.HistRef;
import com.spring.repository.CodeHistRepository;
import com.spring.repository.CodeRepository;
import com.spring.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/histcode")
public class HistCodeController {

    @Autowired
    private CodeHistRepository histCodeRepository;

    @Autowired
    private ReferenceRepository referenceRepository;

    @Autowired
    private CodeRepository codeRepository;

    @PutMapping("/add")
    public HistCode addHistCode(HistCode histCode){
        return histCodeRepository.save(histCode);
    }

    @GetMapping("/list")
    public List<HistCode> getAllHistCode(){
        return histCodeRepository.findAll();
    }

    @GetMapping("/getByCode")
    public List<HistCode> getHistCodeByProduit(@RequestParam int id){
        return histCodeRepository.findByCode(codeRepository.findById(id).orElse(null));
    }

    @GetMapping("/getByRef")
    public List<HistCode> getHistCodeByRef(@RequestParam int id){
        return histCodeRepository.findByReference(referenceRepository.findById(id).orElse(null));
    }
}
