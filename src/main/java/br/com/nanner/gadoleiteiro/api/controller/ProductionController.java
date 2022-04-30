package br.com.nanner.gadoleiteiro.api.controller;

import br.com.nanner.gadoleiteiro.api.dto.ProductionDTO;
import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Production;
import br.com.nanner.gadoleiteiro.service.CowService;
import br.com.nanner.gadoleiteiro.service.ProductionService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/productions")
@RequiredArgsConstructor
public class ProductionController {
    private final ProductionService service;
    private final CowService serviceCow;

    @GetMapping()
    public ResponseEntity get() {
        List<Production> productions = service.getProductions();
        return ResponseEntity.ok(productions.stream().map(ProductionDTO::create).collect(Collectors.toList()));
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Production> production = service.getProductionById(id);
        if (!production.isPresent()) {
            return new ResponseEntity<>("Produção não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(production.map(ProductionDTO::create));
    }

    @GetMapping("/cow/{id}")
    public ResponseEntity getProductionsByCow(@PathVariable("id") Long idCow) {
        Optional<Cow> cow = serviceCow.getCowById(idCow);
        if (cow.isEmpty()) {
            return new ResponseEntity<>("Vaca não encontrada", HttpStatus.NOT_FOUND);
        }
        List<Production> productions = service.getProductionsByCow(cow);
        return ResponseEntity.ok(productions.stream().map(ProductionDTO::create).collect(Collectors.toList()));

    }

    @PostMapping()
    public ResponseEntity createProduction(ProductionDTO dto) {
        try {
            Production production = converter(dto);
            Optional<Production> alreadyCreated = service.getProductionFromCowOnDay(production);

            if (alreadyCreated.isPresent()) {
                production.setId(alreadyCreated.get().getId());
                production.setCapacity(production.getCapacity() + alreadyCreated.get().getCapacity());
            }

            service.save(production);
            return new ResponseEntity(production, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Production converter(ProductionDTO dto) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Production production = modelMapper.map(dto, Production.class);
        Optional<Cow> cow = serviceCow.getCowById(dto.getCowId());
        if (!cow.isPresent()) {
            throw new Exception("vaca não localizada ");
        }

        production.setCow(cow.get());
        return production;
    }
}
