package br.com.nanner.gadoleiteiro.api.controller;

import br.com.nanner.gadoleiteiro.model.entity.Situation;
import br.com.nanner.gadoleiteiro.service.SituationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/situations")
@RequiredArgsConstructor
@CrossOrigin()
public class SituationController {
    private final SituationService service;

    @GetMapping()
    public ResponseEntity get(){
        List<Situation> situations = service.getSituations();
        return ResponseEntity.ok(situations);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id){
        Optional<Situation> situation = service.getSituationById(id);
        return ResponseEntity.ok(situation);
    }
}
