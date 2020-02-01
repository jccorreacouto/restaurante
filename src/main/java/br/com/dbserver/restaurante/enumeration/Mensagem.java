package br.com.dbserver.restaurante.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Mensagem {

    ERRO_VALIDACAO_VOTOS("Funcionário informou seu voto mais de vez.");

    private String descricao;
}
