package br.com.nanner.gadoleiteiro.model.entity;

import br.com.nanner.gadoleiteiro.utils.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cow cow;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize(using= LocalDateDeserializer.class)
    private LocalDate date;

    @ManyToOne
    private Situation situation;
}
