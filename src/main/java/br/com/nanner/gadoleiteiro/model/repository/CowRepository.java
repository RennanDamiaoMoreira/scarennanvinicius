package br.com.nanner.gadoleiteiro.model.repository;

import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CowRepository extends JpaRepository<Cow,Long> {

}
