package br.com.dbserver.restaurante.util;

import br.com.dbserver.restaurante.dto.EscolhaDTO;
import br.com.dbserver.restaurante.enumeration.DiaSemana;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DiaSemanaUtilTest {

    @Test
    public void testValidarVotosPorDiaOK() {
        EscolhaDTO dto1 = EscolhaDTO.builder().matricula(11111L).nome("Funcionario 1").idRestaurante(11L).nomeRestaurante("Restaurante 1").dia(DiaSemana.SEGUNDA).build();
        EscolhaDTO dto2 = EscolhaDTO.builder().matricula(22222L).nome("Funcionario 2").idRestaurante(12L).nomeRestaurante("Restaurante 2").dia(DiaSemana.TERCA).build();
        EscolhaDTO dto3 = EscolhaDTO.builder().matricula(33333L).nome("Funcionario 3").idRestaurante(13L).nomeRestaurante("Restaurante 3").dia(DiaSemana.QUARTA).build();
        EscolhaDTO dto4 = EscolhaDTO.builder().matricula(44444L).nome("Funcionario 4").idRestaurante(14L).nomeRestaurante("Restaurante 4").dia(DiaSemana.QUINTA).build();
        EscolhaDTO dto5 = EscolhaDTO.builder().matricula(55555L).nome("Funcionario 5").idRestaurante(15L).nomeRestaurante("Restaurante 5").dia(DiaSemana.SEXTA).build();
        List<EscolhaDTO> request = Arrays.asList(dto1, dto2, dto3, dto4, dto5);
        Assert.assertNotNull(DiaSemanaUtil.validarVotosPorDia(request));
    }

    @Test
    public void testValidarVotosPorDiaDuploVoto() {
        List<EscolhaDTO> request = this.getRequestTest();
        Assert.assertNotNull(DiaSemanaUtil.validarVotosPorDia(request));
    }

    @Test
    public void testValidarVotosPorDiaRemocaoItem() {
        List<EscolhaDTO> request = this.getRequestTest();
        Assert.assertNotEquals(request.size(), DiaSemanaUtil.validarVotosPorDia(request).size());
    }

    private List<EscolhaDTO> getRequestTest() {
        EscolhaDTO dto1 = EscolhaDTO.builder().matricula(11111L).nome("Funcionario 1").idRestaurante(11L).nomeRestaurante("Restaurante 1").dia(DiaSemana.SEGUNDA).build();
        EscolhaDTO dto2 = EscolhaDTO.builder().matricula(22222L).nome("Funcionario 2").idRestaurante(12L).nomeRestaurante("Restaurante 2").dia(DiaSemana.TERCA).build();
        EscolhaDTO dto3 = EscolhaDTO.builder().matricula(33333L).nome("Funcionario 3").idRestaurante(13L).nomeRestaurante("Restaurante 3").dia(DiaSemana.QUARTA).build();
        EscolhaDTO dto4 = EscolhaDTO.builder().matricula(44444L).nome("Funcionario 4").idRestaurante(14L).nomeRestaurante("Restaurante 4").dia(DiaSemana.QUINTA).build();
        EscolhaDTO dto5 = EscolhaDTO.builder().matricula(55555L).nome("Funcionario 5").idRestaurante(15L).nomeRestaurante("Restaurante 5").dia(DiaSemana.SEXTA).build();
        EscolhaDTO dto6 = EscolhaDTO.builder().matricula(11111L).nome("Funcionario 1").idRestaurante(12L).nomeRestaurante("Restaurante 2").dia(DiaSemana.SEGUNDA).build();
        return Arrays.asList(dto1, dto2, dto3, dto4, dto5, dto6);
    }
}
