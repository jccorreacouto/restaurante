package br.com.dbserver.restaurante.service;

import br.com.dbserver.restaurante.dto.EscolhaDTO;
import br.com.dbserver.restaurante.dto.ResultadoDTO;
import br.com.dbserver.restaurante.enumeration.DiaSemana;
import br.com.dbserver.restaurante.util.DiaSemanaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RestauranteService {

    boolean alterado;
    List<ResultadoDTO> resultados;

    public List<ResultadoDTO> escolherRestaurante(List<EscolhaDTO> request) throws Exception {

        DiaSemanaUtil.validarVotosPorDia(request);

        resultados = new ArrayList<>();
        Set<Long> idsRestaurante = request.stream().map(dto -> dto.getIdRestaurante()).collect(Collectors.toSet());

        log.info("Populando resultados.");
        request.stream().forEach(req -> {
            idsRestaurante.stream().forEach(id -> {
                if(req.getIdRestaurante().equals(id)) {
                    resultados = this.montarResultados(resultados, req.getDia(), req.getNomeRestaurante(), id);
                }
            });
        });

        return resultados;
    }

    private List<ResultadoDTO> montarResultados(List<ResultadoDTO> resultados, DiaSemana diaSemana, String nomeRestaurante, Long idRestaurante) {
        alterado = false;
        if(resultados.isEmpty()) {
            log.info("Lista vazia: Adicionou.");
            resultados.add(ResultadoDTO.builder().escolha(1L).dia(diaSemana)
                    .restaurante(nomeRestaurante)
                    .idRestaurante(idRestaurante)
                    .build());
        } else {
            resultados.stream().forEach(res -> {
                if(diaSemana == res.getDia() && res.getIdRestaurante().equals(idRestaurante)) {
                    log.info("Lista não vazia: Alterou existente.");
                    res.setEscolha(res.getEscolha() + 1L);
                    alterado = true;
                }
            });

            if(!alterado) {
                log.info("Lista não vazia: Adicionou.");
                resultados.add(ResultadoDTO.builder().escolha(1L).dia(diaSemana)
                        .restaurante(nomeRestaurante)
                        .idRestaurante(idRestaurante)
                        .build());
            }
        }

        return resultados;
    }
}
