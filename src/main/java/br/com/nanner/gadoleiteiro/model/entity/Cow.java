package br.com.nanner.gadoleiteiro.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class Cow extends Cattle{

    private Double average;

    @ManyToOne
    @JoinColumn(name = "situation_id")
    private Situation situation;
}
