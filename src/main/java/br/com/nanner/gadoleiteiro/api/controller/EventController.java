package br.com.nanner.gadoleiteiro.api.controller;

import br.com.nanner.gadoleiteiro.api.dto.EventDTO;
import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Event;
import br.com.nanner.gadoleiteiro.model.entity.Situation;
import br.com.nanner.gadoleiteiro.service.CowService;
import br.com.nanner.gadoleiteiro.service.EventService;
import br.com.nanner.gadoleiteiro.service.SituationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path ="/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final CowService cowService;
    private final SituationService situationService;

    @GetMapping()
    public ResponseEntity get(){
        List<Event> events = eventService.getEvents();
        return ResponseEntity.ok(events.stream().map(EventDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/id")
    public ResponseEntity get(@PathVariable("id")Long id){
        Optional<Event> event = eventService.getEventById(id);
        if (!event.isPresent())
            return new ResponseEntity<>("Evento não encontrado", HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(event.map((EventDTO::create)));
    }

    @PostMapping()
    public ResponseEntity post (@RequestBody EventDTO dto ){
        try{
            Event event = converter(dto);
            event = eventService.save(event);
            event.getCow().setSituation(event.getSituation());
            cowService.save(event.getCow());
            return  new ResponseEntity<>(event,HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Event converter(EventDTO dto) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Cow> cow = cowService.getCowById(dto.getCowId());
        Optional<Situation> situation = situationService.getSituationById(dto.getSituationId());
        if (!cow.isPresent())
            throw new Exception("  Vaca não localizada  ");

        if (!situation.isPresent())
            throw new Exception("  Estágio não localizado  "+dto);

        Event event = modelMapper.map(dto, Event.class);
        event.setCow(cow.get());
        event.setSituation(situation.get());
        return event;
    }


}
