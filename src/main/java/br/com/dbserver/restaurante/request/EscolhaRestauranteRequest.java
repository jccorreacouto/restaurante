package br.com.dbserver.restaurante.request;

import br.com.dbserver.restaurante.dto.EscolhaDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EscolhaRestauranteRequest {

    private List<EscolhaDTO> escolha;
}
