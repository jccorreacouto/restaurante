package br.com.dbserver.restaurante.web;

import br.com.dbserver.restaurante.dto.EscolhaDTO;
import br.com.dbserver.restaurante.dto.ResultadoDTO;

import java.util.List;

public interface RestauranteApi {

    List<ResultadoDTO> escolherRestaurante(List<EscolhaDTO> request) throws Exception;
}
