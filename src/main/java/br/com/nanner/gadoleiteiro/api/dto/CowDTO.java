package br.com.nanner.gadoleiteiro.api.dto;

import br.com.nanner.gadoleiteiro.model.entity.Cow;
import br.com.nanner.gadoleiteiro.model.entity.Event;
import br.com.nanner.gadoleiteiro.model.entity.Production;
import br.com.nanner.gadoleiteiro.model.entity.Situation;
import br.com.nanner.gadoleiteiro.model.repository.EventRepository;
import br.com.nanner.gadoleiteiro.service.EventService;
import br.com.nanner.gadoleiteiro.service.ProductionService;
import br.com.nanner.gadoleiteiro.utils.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CowDTO {
    static  private  EventService eventService ;
    private Long id;
    private String registration;
    private String name;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize(using= LocalDateDeserializer.class)
    private LocalDate dateOfBirth;
    private Double average;
    private List<Event> events;
    private Long situationId;



    public static CowDTO create(Cow cow){

        ModelMapper modelMapper = new ModelMapper();
        CowDTO dto = modelMapper.map(cow,CowDTO.class);
        dto.id = cow.getId();
        dto.registration = cow.getRegistration();
        dto.name = cow.getName();
        dto.dateOfBirth = cow.getDateOfBirth();
        dto.average = cow.getAverage();
//        dto.events = eventService.getEventsByCowId(cow.getId());
        //dto.situation = cow.getSituation().getId();
        return dto;
    }
}
