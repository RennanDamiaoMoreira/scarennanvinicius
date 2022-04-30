package br.com.nanner.gadoleiteiro.model.repository;

import br.com.nanner.gadoleiteiro.model.entity.Situation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SituationRepository  extends JpaRepository<Situation,Long> {
}
