package br.com.nanner.gadoleiteiro.api.dto;


import br.com.nanner.gadoleiteiro.model.entity.CattleShed;
import br.com.nanner.gadoleiteiro.model.entity.Production;
import br.com.nanner.gadoleiteiro.utils.LocalDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CattleShedDTO {

    Long id;
    String name;
    String description;

    public static CattleShedDTO create(CattleShed cattleShed) {
        ModelMapper modelMapper = new ModelMapper();
        CattleShedDTO dto = modelMapper.map(cattleShed, CattleShedDTO.class);
        return dto;
    }

}
