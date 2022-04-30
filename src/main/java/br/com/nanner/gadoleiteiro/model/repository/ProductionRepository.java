package br.com.nanner.gadoleiteiro.model.repository;

import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Production;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProductionRepository extends JpaRepository<Production,Long> {
    List<Production> findByCow(Optional<Cow> cow);

    Optional<Production> findByCowAndDate(Cow cow, LocalDate date );
}
