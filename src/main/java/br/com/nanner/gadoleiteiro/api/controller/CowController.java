package br.com.nanner.gadoleiteiro.api.controller;

import br.com.nanner.gadoleiteiro.api.dto.CowDTO;
import br.com.nanner.gadoleiteiro.api.dto.ProductionDTO;
import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Event;
import br.com.nanner.gadoleiteiro.model.entity.Production;
import br.com.nanner.gadoleiteiro.service.CowService;
import br.com.nanner.gadoleiteiro.service.EventService;
import br.com.nanner.gadoleiteiro.service.SituationService;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path ="/api/v1/cows")
@RequiredArgsConstructor
@CrossOrigin
public class CowController {
    private final CowService serviceCow;
    private final EventService eventService;
    private final SituationService situationService;

    @GetMapping()
    public ResponseEntity get(){
        List<Cow> cows = serviceCow.getCow();
        return ResponseEntity.ok(cows.stream().map(CowDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id){
        System.out.println(id);
        Optional<Cow> cow = serviceCow.getCowById(id);
        System.out.println(cow);
        if (!cow.isPresent()){
            return new ResponseEntity<>("Vaca não encontrada", HttpStatus.NOT_FOUND);
        }
        CowDTO dto = cow.map(CowDTO::create).get();
        dto.setEvents(eventService.getEventsByCowId(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping()
    public ResponseEntity post( @RequestBody CowDTO dto) {
        try {
            Cow cow = converter(dto);
            Event event;
            cow.setAverage(0.0);
            cow.setActive(true);
            cow = serviceCow.save(cow);
            cow.setSituation(situationService.getSituationById(Long.parseLong("1")).get());
            event = Event.builder().cow(cow).situation(situationService.getSituationById(Long.parseLong("1")).get()).date(cow.getDateOfBirth()).build();
            eventService.save(event);
            return new ResponseEntity(cow, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id") Long id , CowDTO dto){
        Optional<Cow> reference = serviceCow.getCowById(id);
        if (! reference.isPresent()){
            return new ResponseEntity<>("Vaca não encontrada",HttpStatus.NOT_FOUND);
        }
        try{
            Cow cow = converter(dto);
            cow.setAverage(reference.get().getAverage());
            cow = serviceCow.save(cow);
            return  ResponseEntity.ok(cow);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    public Cow converter(CowDTO dto ){
        ModelMapper modelMapper = new ModelMapper();
        Cow cow = modelMapper.map(dto , Cow.class);
        return cow;
    }
}
