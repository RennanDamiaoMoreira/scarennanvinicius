package br.com.nanner.gadoleiteiro.service;

import br.com.nanner.gadoleiteiro.model.entity.Situation;
import br.com.nanner.gadoleiteiro.model.repository.CattleShedRepository;
import br.com.nanner.gadoleiteiro.model.repository.SituationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CattleShedService {
    private final CattleShedRepository repository;

    public CattleShedService(CattleShedRepository repository) {
        this.repository = repository;
    }


}
