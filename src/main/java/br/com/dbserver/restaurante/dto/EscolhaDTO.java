package br.com.dbserver.restaurante.dto;

import br.com.dbserver.restaurante.enumeration.DiaSemana;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EscolhaDTO {

    private Long matricula;
    private String nome;
    private Long idRestaurante;
    private String nomeRestaurante;
    private DiaSemana dia;

}
