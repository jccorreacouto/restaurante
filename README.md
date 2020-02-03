[![Build Status](https://travis-ci.com/jccorreacouto/restaurante.svg?branch=develop)](https://travis-ci.com/jccorreacouto/restaurante)
[![Coverage Status](https://coveralls.io/repos/github/jccorreacouto/restaurante/badge.svg?branch=develop)](https://coveralls.io/github/jccorreacouto/restaurante?branch=develop)

## Onde almoçar? :thinking:

### Descrição do problema
Os times da Empresa enfrentam um grande problema: 
Como eles são muito democráticos, todos os dias eles gastam 30 minutos decidindo onde eles irão almoçar.
Esse projeto propõe um sistema que auxilie nesta tomada de decisão!

### Características do desenvolvimento
* IDE utilizada: IntelliJ IDEA 2019.3.2.
* Linguagem de Programação Java, versão 8 (1.8.0_231), para desenvolvimento do código fonte.
* Pacote Lombok, para anotações dos atributos e métodos das classes.
* Framework Spring Boot (versão 2.2.3.RELEASE) e suas bibliotecas, para publicação e gerenciamento da aplicação.
* Junit 4, para criação dos testes unitários.

### Subindo o projeto
Para iniciar o container é necessário que o comando ``mvn spring-boot:run`` seja executado na raiz do projeto.

### Descrição da solução

#### Request
* Foi criado um endpoint em: [localhost](http://localhost:8080/restaurante/escolher)
* É um método POST que recebe no ``Body`` do request os dados no formato abaixo:
````json
[
    {
        "matricula": 11111,
        "nome": "Funcionario 1",
        "idRestaurante": 11,
        "nomeRestaurante": "Restaurante 1",
        "dia": "SEGUNDA"
    }, 
    {
        "matricula": 22222,
        "nome": "Funcionario 2",
        "idRestaurante": 12,
        "nomeRestaurante": "Restaurante 2",
        "dia": "TERCA"
    },
    {
        "matricula": 33333,
        "nome": "Funcionario 3",
        "idRestaurante": 13,
        "nomeRestaurante": "Restaurante 3",
        "dia": "QUARTA"
    },
    {
        "matricula": 44444,
        "nome": "Funcionario 4",
        "idRestaurante": 14,
        "nomeRestaurante": "Restaurante 4",
        "dia": "QUINTA"
    }, 
    {
        "matricula": 55555,
        "nome": "Funcionario 5",
        "idRestaurante": 15,
        "nomeRestaurante": "Restaurante 5",
        "dia": "SEXTA"
    }
]
````
* No ``Header`` passar os valores: ``KEY: Content-Type`` e ``VALUE: application/json``.

#### Response
* Para o response é esperado uma lista de objetos no formato abaixo:

````json
[
    {
        "restaurante": "Restaurante 1",
        "escolha": 1,
        "dia": "SEGUNDA"
    },
    {
        "restaurante": "Restaurante 2",
        "escolha": 1,
        "dia": "TERCA"
    },
    {
        "restaurante": "Restaurante 3",
        "escolha": 1,
        "dia": "QUARTA"
    },
    {
        "restaurante": "Restaurante 4",
        "escolha": 1,
        "dia": "QUINTA"
    },
    {
        "restaurante": "Restaurante 5",
        "escolha": 1,
        "dia": "SEXTA"
    }
]
````

### Possibilidades de melhorias
1. Tratar os métodos privados (``Code Smell``) para ampliar a cobertura dos testes unitários.
2. Criar o teste de URL para que, caso haja alteração da mesma, seja evidenciado.