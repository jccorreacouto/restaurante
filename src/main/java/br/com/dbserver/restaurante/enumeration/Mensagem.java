package br.com.dbserver.restaurante.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Mensagem {

    ERROR_CAMPO_MATRICULA_OBRIGATORIO("Campo MATRÍCULA obrigatório."),
    ERROR_CAMPO_NOME_OBRIGATORIO("Campo NOME obrigatório."),
    ERROR_CAMPO_ID_RESTAURANTE_OBRIGATORIO("Campo ID RESTAURANTE obrigatório."),
    ERROR_CAMPO_NOME_RESTAURANTE_OBRIGATORIO("Campo NOME RESTAURANTE obrigatório."),
    ERROR_CAMPO_DIA_OBRIGATORIO("Campo DIA obrigatório.");

    private String descricao;
}
