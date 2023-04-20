# Meal-API ![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/Joao-Uchoas/Meal-API/maven-dev)

Esta API foi desenvolvida utilizando Spring Boot, Firebase e JWT para gerenciamento de refeições do usuário. A aplicação permite adicionar, listar e deletar refeições, e usa autenticação com JWT para proteger os endpoints.

## Tecnologias utilizadas

- Spring Boot
- Firebase
- JSON Web Token (JWT)
- Maven

## Arquitetura do projeto

A aplicação segue o padrão MVC (Model-View-Controller) e está organizada da seguinte forma:

- `config`: Classes de configuração do Firebase e da segurança com JWT.
- `controller`: Classe controladora responsável por lidar com as solicitações HTTP.
- `dto`: Classe DTO (Data Transfer Object) para transferir dados entre o modelo e o cliente.
- `entity`: Classe da identidade dos dados (Refeição).
- `repository`: Interface do repositório que estende o JpaRepository para realizar operações de banco de dados.
- `service`: Interface do serviço e sua implementação.
- `resources`: Arquivos de configuração, como o application.properties e o arquivo JSON do Firebase.

## Como executar

1. Clone este repositório.
2. Navegue até o diretório do projeto.
3. Execute `mvn clean install` para baixar e instalar as dependências.
4. Importe o projeto para sua IDE de preferência.
5. Configure o Firebase com suas credenciais no arquivo `firebase.json`.
6. Execute a aplicação utilizando a classe principal `MyApiApplication`.
7. Acesse os endpoints através da URL base `http://localhost:8080`.

## Endpoints disponíveis

- `GET /api/meals`: Lista todas as refeições
- `GET /api/meals/{id}`: Lista uma refeição
- `POST /api/meals`: Adiciona uma nova refeição
- `DELETE /api/meals/{id}`: Deleta uma refeição pelo ID

