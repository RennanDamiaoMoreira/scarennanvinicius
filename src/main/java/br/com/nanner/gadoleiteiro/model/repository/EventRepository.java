package br.com.nanner.gadoleiteiro.model.repository;

import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findByCow(Optional<Cow> cow);

    List<Event> findEventsByCow_Id(Long id);
}
