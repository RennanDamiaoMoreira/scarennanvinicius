package br.com.nanner.gadoleiteiro.service;

import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.repository.CowRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CowService {
    private final CowRepository repository;

    public CowService(CowRepository repository){
        this.repository = repository;
    }

    public List<Cow> getCow(){
        return repository.findAll();
    }

    public Optional<Cow> getCowById(Long id){
        return repository.findById(id);
    }

    @Transactional
    public Cow save(Cow cow) throws Exception {
        validate(cow);
        return repository.save(cow);
    }

    private void validate(Cow cow) throws Exception {
        if (cow.getName() == null || cow.getName().equals("")) {
            throw new Exception("Nome não informado  "+cow.getName());
        }
        if (cow.getRegistration() == null || cow.getRegistration().equals("")) {
            throw new Exception("Registro não informado");
        }
        if (cow.getDateOfBirth() == null ) {
            throw new Exception("Data de nascimento não informado");
        }else{
            if (cow.getDateOfBirth().isAfter(LocalDate.now())){
                throw new Exception("A vaca não pode ter a data de nascimento no futuro");
            }
        }
//        if (cow.getCattleShed() == null){
//            throw new Exception("Curral não informado");
//        }

    }
}
