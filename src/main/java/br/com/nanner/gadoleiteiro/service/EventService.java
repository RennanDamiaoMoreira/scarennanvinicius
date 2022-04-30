package br.com.nanner.gadoleiteiro.service;

import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Event;
import br.com.nanner.gadoleiteiro.model.repository.CowRepository;
import br.com.nanner.gadoleiteiro.model.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService (EventRepository repository){ this.repository = repository;};
    
    public List<Event> getEvents(){
        return repository.findAll();
    }

    public Optional<Event> getEventById(Long id){
        return repository.findById(id);
    }

    public List<Event> getEventsByCow(Optional<Cow> cow){ return repository.findByCow(cow); }

    public Event save(Event event) throws Exception {
        validate(event);
        return repository.save(event);
    }

    private void validate(Event event) throws Exception{
        String error = "";

        if (event.getCow() == null){
            error+="  Vaca não informada  ";
        }

        if (event.getDate() == null || event.getDate().isBefore(LocalDate.now())){
            error+="  Data inválida  ";
        }

        if (event.getSituation() == null  ){
            error+= "  Estágio não informado  ";
        }
    }

    public List<Event> getEventsByCowId(Long id) {

        return repository.findEventsByCow_Id(id);
    }
}
