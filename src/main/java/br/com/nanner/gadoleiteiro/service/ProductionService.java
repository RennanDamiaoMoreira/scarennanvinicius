package br.com.nanner.gadoleiteiro.service;

import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Production;
import br.com.nanner.gadoleiteiro.model.repository.ProductionRepository;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


import static java.util.Optional.of;

@Service
public class ProductionService {
    private final ProductionRepository repository;

    public ProductionService(ProductionRepository repository){
        this.repository = repository;
    }

    public List<Production> getProductions(){
        return repository.findAll();
    }

    public Optional<Production> getProductionById(Long id){
        return repository.findById(id);
    }

    public List<Production> getProductionsByCow(Optional<Cow> cow){ return repository.findByCow(cow); }

    public Optional<Production> getProductionFromCowOnDay(@NotNull Production reference ) {
        return repository.findByCowAndDate(reference.getCow(),reference.getDate());
    }

    @Transactional
    public Production save(Production production) throws Exception {
        validate(production);
        return repository.save(production);
    }

    private void validate(Production production) throws Exception {
        String error = "";
        if (production.getCow() == null)
            error += "  Vaca não informada  ";

        if (production.getCapacity()<1)
            error += "  Produção não pode ser cadastrada com quantidade produzida menor que 1 litro ";

        if (production.getDate() == null || production.getDate().isAfter(LocalDate.now()))
            error += "  Data informada inválida  ";

        if (error != "")
            throw new Exception(error);
    }


}
