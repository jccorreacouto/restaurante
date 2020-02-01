package br.com.dbserver.restaurante.util;

import br.com.dbserver.restaurante.dto.EscolhaDTO;
import br.com.dbserver.restaurante.enumeration.DiaSemana;
import br.com.dbserver.restaurante.enumeration.Mensagem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiaSemanaUtil {

    private static boolean duploVoto = false;

    public static void validarVotosPorDia(List<EscolhaDTO> request) throws Exception {
        for(DiaSemana diaSemana : DiaSemana.values()) {
            List<Long> matriculas = new ArrayList<>();
            List<EscolhaDTO> votosPorDia = request.stream().filter(dto -> diaSemana == dto.getDia()).collect(Collectors.toList());
            votosPorDia.stream().forEach(voto -> {
                if (matriculas.isEmpty()) {
                    matriculas.add(voto.getMatricula());
                } else {
                    if (!matriculas.contains(voto.getMatricula())) {
                        matriculas.add(voto.getMatricula());
                    } else {
                        duploVoto = true;
                    }
                }
            });

            if (duploVoto) {
                throw new Exception(Mensagem.ERRO_VALIDACAO_VOTOS.getDescricao());
            }
        }
    }

}
