package br.com.dbserver.restaurante.dto;

import br.com.dbserver.restaurante.enumeration.DiaSemana;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ResultadoDTO {

    private String restaurante;
    @JsonIgnore
    private Long idRestaurante;
    private Long escolha;
    private DiaSemana dia;
}
