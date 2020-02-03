package br.com.dbserver.restaurante.util;

import br.com.dbserver.restaurante.dto.EscolhaDTO;
import br.com.dbserver.restaurante.enumeration.DiaSemana;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class DiaSemanaUtil {

    public static List<EscolhaDTO> validarVotosPorDia(List<EscolhaDTO> request) {
        List<EscolhaDTO> retorno = new ArrayList<>();
        for(DiaSemana diaSemana : DiaSemana.values()) {
            List<Long> matriculas = new ArrayList<>();
            List<EscolhaDTO> votosPorDia = request.stream().filter(dto -> diaSemana == dto.getDia()).collect(Collectors.toList());
            votosPorDia.stream().forEach(voto -> {
                if (matriculas.isEmpty()) {
                    matriculas.add(voto.getMatricula());
                    retorno.add(voto);
                } else {
                    if (!matriculas.contains(voto.getMatricula())) {
                        matriculas.add(voto.getMatricula());
                        retorno.add(voto);
                    } else {
                        log.warn("Voto duplo de {} desconsiderado na {}.", voto.getNome(), voto.getDia().getDescricao());
                    }
                }
            });
        }
        return retorno;
    }

}
