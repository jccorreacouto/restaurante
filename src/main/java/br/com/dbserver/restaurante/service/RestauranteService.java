package br.com.dbserver.restaurante.service;

import br.com.dbserver.restaurante.dto.EscolhaDTO;
import br.com.dbserver.restaurante.dto.ResultadoDTO;
import br.com.dbserver.restaurante.enumeration.DiaSemana;
import br.com.dbserver.restaurante.enumeration.Mensagem;
import br.com.dbserver.restaurante.util.DiaSemanaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RestauranteService {

    public List<ResultadoDTO> escolherRestaurante(List<EscolhaDTO> request) throws Exception {

        this.validarRequest(request);

        request = DiaSemanaUtil.validarVotosPorDia(request);

        List<ResultadoDTO> resultados = new ArrayList<>();
        Set<Long> idsRestaurante = request.stream().map(dto -> dto.getIdRestaurante()).collect(Collectors.toSet());

        log.info("Populando resultados.");
        request.stream().forEach(req -> {
            idsRestaurante.stream().forEach(id -> {
                if(req.getIdRestaurante().equals(id)) {
                    this.montarResultados(resultados, req.getDia(), req.getNomeRestaurante(), id);
                }
            });
        });

        return this.tratarResultados(resultados);
    }

    private void validarRequest(List<EscolhaDTO> request) throws Exception {
        for (EscolhaDTO dto : request) {
            if(Objects.isNull(dto.getMatricula())) {
                throw new Exception(Mensagem.ERROR_CAMPO_MATRICULA_OBRIGATORIO.getDescricao());
            }
            if(StringUtils.isEmpty(dto.getNome())) {
                throw new Exception(Mensagem.ERROR_CAMPO_NOME_OBRIGATORIO.getDescricao());
            }
            if(Objects.isNull(dto.getIdRestaurante())) {
                throw new Exception(Mensagem.ERROR_CAMPO_ID_RESTAURANTE_OBRIGATORIO.getDescricao());
            }
            if(StringUtils.isEmpty(dto.getNomeRestaurante())) {
                throw new Exception(Mensagem.ERROR_CAMPO_NOME_RESTAURANTE_OBRIGATORIO.getDescricao());
            }
            if(Objects.isNull(dto.getDia())) {
                throw new Exception(Mensagem.ERROR_CAMPO_DIA_OBRIGATORIO.getDescricao());
            }
        }
    }

    private List<ResultadoDTO> montarResultados(List<ResultadoDTO> resultados, DiaSemana diaSemana, String nomeRestaurante, Long idRestaurante) {
        boolean alterado = false;
        if(resultados.isEmpty()) {
            log.info("Lista vazia: Adicionou.");
            resultados.add(ResultadoDTO.builder().escolha(1L).dia(diaSemana)
                    .restaurante(nomeRestaurante)
                    .idRestaurante(idRestaurante)
                    .build());
        } else {

            for (ResultadoDTO res : resultados) {
                if(diaSemana == res.getDia() && res.getIdRestaurante().equals(idRestaurante)) {
                    log.info("Lista não vazia: Alterou existente.");
                    res.setEscolha(res.getEscolha() + 1L);
                    alterado = true;
                }
            }

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

    private List<ResultadoDTO> tratarResultados(List<ResultadoDTO> resultados) {

        List<ResultadoDTO> resultadosTratados = new ArrayList<>();

        List<ResultadoDTO> resSegunda = resultados.stream().filter(resultado -> resultado.getDia() == DiaSemana.SEGUNDA).sorted(Comparator.comparing(ResultadoDTO::getEscolha).reversed()).collect(Collectors.toList());
        log.info("Escolhas na {}: {}.", DiaSemana.SEGUNDA.getDescricao(), resSegunda.size());
        resultadosTratados = this.popularRetorno(resultadosTratados, resSegunda);

        List<ResultadoDTO> resTerca = resultados.stream().filter(resultado -> resultado.getDia() == DiaSemana.TERCA).sorted(Comparator.comparing(ResultadoDTO::getEscolha).reversed()).collect(Collectors.toList());
        log.info("Escolhas na {}: {}.", DiaSemana.TERCA.getDescricao(), resTerca.size());
        resultadosTratados = this.popularRetorno(resultadosTratados, resTerca);

        List<ResultadoDTO> resQuarta = resultados.stream().filter(resultado -> resultado.getDia() == DiaSemana.QUARTA).sorted(Comparator.comparing(ResultadoDTO::getEscolha).reversed()).collect(Collectors.toList());
        log.info("Escolhas na {}: {}.", DiaSemana.QUARTA.getDescricao(), resQuarta.size());
        resultadosTratados = this.popularRetorno(resultadosTratados, resQuarta);

        List<ResultadoDTO> resQuinta = resultados.stream().filter(resultado -> resultado.getDia() == DiaSemana.QUINTA).sorted(Comparator.comparing(ResultadoDTO::getEscolha).reversed()).collect(Collectors.toList());
        log.info("Escolhas na {}: {}.", DiaSemana.QUINTA.getDescricao(), resQuinta.size());
        resultadosTratados = this.popularRetorno(resultadosTratados, resQuinta);

        List<ResultadoDTO> resSexta = resultados.stream().filter(resultado -> resultado.getDia() == DiaSemana.SEXTA).sorted(Comparator.comparing(ResultadoDTO::getEscolha).reversed()).collect(Collectors.toList());
        log.info("Escolhas na {}: {}.", DiaSemana.SEXTA.getDescricao(), resSexta.size());
        resultadosTratados = this.popularRetorno(resultadosTratados, resSexta);

        log.info("Apresentando resultado final.");
        return resultadosTratados;
    }

    private List<ResultadoDTO> popularRetorno(List<ResultadoDTO> resultadosTratados, List<ResultadoDTO> resultadoPorDia) {
        if(!resultadoPorDia.isEmpty()) {
            if(resultadosTratados.isEmpty()) {
                resultadosTratados.add(resultadoPorDia.stream().findFirst().get());
            } else {
                resultadoPorDia.forEach(porDia -> {
                    boolean contemNosResultados = resultadosTratados.stream().filter(tratado -> tratado.getIdRestaurante().equals(porDia.getIdRestaurante())).findAny().isPresent();
                    if(!contemNosResultados) {
                        resultadosTratados.add(porDia);
                        return;
                    }
                });
            }
        }

        return resultadosTratados;
    }
}
