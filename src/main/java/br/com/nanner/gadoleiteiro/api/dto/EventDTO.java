package br.com.nanner.gadoleiteiro.api.dto;

import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Event;
import br.com.nanner.gadoleiteiro.model.entity.Situation;
import br.com.nanner.gadoleiteiro.utils.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private Long cowId;
    private Long situationId;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize(using= LocalDateDeserializer.class)
    private LocalDate date;

    private Cow cow;
    private Situation situation;

    public static EventDTO create(Event event){
        ModelMapper modelMapper = new ModelMapper();
        EventDTO dto = modelMapper.map(event , EventDTO.class);

        dto.id = event.getId();
        dto.cowId = event.getCow().getId();
        dto.date = event.getDate();
        dto.situationId = event.getSituation().getId();

        return dto;
    }
}
