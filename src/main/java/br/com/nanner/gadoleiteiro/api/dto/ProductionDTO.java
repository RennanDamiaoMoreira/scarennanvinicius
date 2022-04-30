package br.com.nanner.gadoleiteiro.api.dto;


import br.com.nanner.gadoleiteiro.model.entity.Production;
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
public class ProductionDTO {

    private Long id;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonDeserialize(using= LocalDateDeserializer.class)
    private LocalDate date;
    private Double capacity;
    private Long cowId;

    public static ProductionDTO create(Production production){
        ModelMapper modelMapper = new ModelMapper();
        ProductionDTO dto = modelMapper.map(production,ProductionDTO.class);
        dto.id = production.getId();
        dto.date = production.getDate();
        dto.capacity = production.getCapacity();
        dto.cowId = production.getCow().getId();
        return dto;
    }
}
