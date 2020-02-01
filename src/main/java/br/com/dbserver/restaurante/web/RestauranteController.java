package br.com.dbserver.restaurante.web;

import br.com.dbserver.restaurante.dto.EscolhaDTO;
import br.com.dbserver.restaurante.dto.ResultadoDTO;
import br.com.dbserver.restaurante.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
class RestauranteController implements RestauranteApi {

    @Autowired
    private RestauranteService service;

    @PostMapping("/escolher")
    @Override
    public List<ResultadoDTO> escolherRestaurante(@RequestBody final List<EscolhaDTO> request) throws Exception {
        return this.service.escolherRestaurante(request);
    }
}
