# Weather App

Previsão de Tempo App - Backend

## Descrição

Este é o backend do aplicativo de Previsão de Tempo, desenvolvido em Java Spring. Ele fornece uma API REST para obtenção de informações sobre a previsão do tempo.

## Funcionalidades

- Fornecimento de dados de previsão do tempo para diferentes cidades.
- Endpoints para consulta da previsão do tempo atual, por cidade.

## Pré-requisitos

- Java Development Kit (JDK) (versão 17.0.6)
- Gradle (versão 8.0.2)
- Banco de Dados PostgreSQL


## Configuração

Edite o arquivo `application.properties` em `src/main/resources` para configurar as seguintes propriedades:

- spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
- spring.datasource.username=usuario
- spring.datasource.password=senha

## Instalação

1. Clone o repositório: git clone https://github.com/ralfdw3/weather-app-spring.git
2. Navegue até o diretório do projeto: cd weather-app-spring
3. Execute o seguinte comando para buildar o projeto: build gradle
4. Execute o seguinte comando para executar o projeto: gradle bootRun

O backend estará em execução na porta `8080`.
Você pode acessar os endpoints da API em "http://localhost:8080/swagger-ui/index.html".

