package br.com.nanner.gadoleiteiro.service;

import br.com.nanner.gadoleiteiro.model.entity.Situation;
import br.com.nanner.gadoleiteiro.model.repository.SituationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SituationService {
    private final SituationRepository repository;

    public SituationService(SituationRepository repository){
        this.repository = repository;
    }
    public List<Situation> getSituations(){
        return repository.findAll();
    }

    public Optional<Situation> getSituationById(Long id){
        return repository.findById(id);
    }
}
