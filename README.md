# Hackathon Orbitall 2025

API REST em Java com Spring Boot para gerenciamento de clientes e registro de transações.

## Como Executar a Aplicação

### Pré-requisitos
- Java 21
- Maven

### Passos para Execução

1. Clone o repositório:
   ```
   git clone [URL_DO_REPOSITÓRIO]
   ```

2. Navegue até a pasta do projeto:
   ```
   cd hackathon-orbitall-2025-main
   ```

3. Execute a aplicação com Maven:
   ```
   ./mvnw spring-boot:run
   ```
   
   No Windows, use:
   ```
   mvnw.cmd spring-boot:run
   ```

4. A aplicação estará disponível em:
   ```
   http://localhost:8080
   ```

5. Para acessar o console do banco de dados H2:
   ```
   http://localhost:8080/h2-console
   ```
   - JDBC URL: `jdbc:h2:mem:hackathon-orbitall-2025`
   - Usuário: `sa`
   - Senha: (deixe em branco)

## Endpoints da API

### Clientes (Customers)

#### Listar todos os clientes ativos
- **GET** `/customers`
- **Resposta**: Lista de clientes ativos
  ```json
  [
    {
      "id": "550e8400-e29b-41d4-a716-446655440000",
      "fullName": "Nome Completo",
      "email": "email@exemplo.com",
      "phone": "11999999999",
      "createdAt": "2023-01-01T10:00:00",
      "updatedAt": "2023-01-01T10:00:00",
      "active": true
    }
  ]
  ```

#### Buscar cliente por ID
- **GET** `/customers/{id}`
- **Resposta**: Dados do cliente
  ```json
  {
    "id": "550e8400-e29b-41d4-a716-446655440000",
    "fullName": "Nome Completo",
    "email": "email@exemplo.com",
    "phone": "11999999999",
    "createdAt": "2023-01-01T10:00:00",
    "updatedAt": "2023-01-01T10:00:00",
    "active": true
  }
  ```

#### Cadastrar novo cliente
- **POST** `/customers`
- **Corpo da Requisição**:
  ```json
  {
    "fullName": "Nome Completo",
    "email": "email@exemplo.com",
    "phone": "11999999999"
  }
  ```
- **Resposta**: Cliente criado com status 201 (Created)

#### Atualizar cliente
- **PUT** `/customers/{id}`
- **Corpo da Requisição**:
  ```json
  {
    "fullName": "Nome Atualizado",
    "email": "novo@exemplo.com",
    "phone": "11988888888"
  }
  ```
- **Resposta**: Cliente atualizado

#### Excluir cliente
- **DELETE** `/customers/{id}`
- **Resposta**: Status 204 (No Content)

### Transações (Transactions)

#### Listar todas as transações
- **GET** `/transactions`
- **Resposta**: Lista de transações

#### Listar transações por cliente
- **GET** `/transactions?customerId={customerId}`
- **Resposta**: Lista de transações do cliente
  ```json
  [
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "customerId": "550e8400-e29b-41d4-a716-446655440000",
      "amount": 100.50,
      "cardType": "CREDIT",
      "createdAt": "2023-01-01T10:00:00",
      "updatedAt": null,
      "active": true
    }
  ]
  ```

#### Buscar transação por ID
- **GET** `/transactions/{id}`
- **Resposta**: Dados da transação

#### Criar nova transação
- **POST** `/transactions`
- **Corpo da Requisição**:
  ```json
  {
    "customerId": "550e8400-e29b-41d4-a716-446655440000",
    "amount": 100.50,
    "cardType": "CREDIT"
  }
  ```
- **Resposta**: Transação criada com status 201 (Created)

#### Atualizar transação
- **PUT** `/transactions/{id}`
- **Corpo da Requisição**:
  ```json
  {
    "customerId": "550e8400-e29b-41d4-a716-446655440000",
    "amount": 150.75,
    "cardType": "DEBIT"
  }
  ```
- **Resposta**: Transação atualizada

#### Excluir transação
- **DELETE** `/transactions/{id}`
- **Resposta**: Status 204 (No Content)

## Exemplos com Postman

A coleção do Postman está disponível na pasta `postman` do projeto. Para utilizá-la:

1. Abra o Postman
2. Importe a coleção: `Hackathon Orbitall 2025.postman_collection.json`
3. Importe o ambiente: `Hackathon Orbitall 2025.postman_environment.json`
4. Selecione o ambiente importado
5. Execute as requisições da coleção

## Dependências Utilizadas

- **Spring Boot Starter Web**: Framework para desenvolvimento de aplicações web
- **Spring Boot Starter Data JPA**: Suporte para persistência de dados com JPA
- **Spring Boot Starter Validation**: Validação de dados
- **H2 Database**: Banco de dados em memória
- **Spring Boot Starter Actuator**: Monitoramento e gerenciamento da aplicação
- **Lombok**: Redução de código boilerplate
- **Spring Boot Starter Test**: Suporte para testes

## Regras de Negócio Implementadas

- Validação do nome completo (fullName) do Cliente como obrigatório e com máximo de 255 caracteres
- Validação do e-mail (email) do Cliente como obrigatório e com máximo de 100 caracteres
- Validação do telefone (phone) do Cliente como obrigatório
- Validação para não permitir transações para clientes inexistentes (retorna 404 Not Found)
- Validação do cliente (customerId) da Transação como obrigatório e cliente válido
- Validação do valor (amount) da transação como obrigatório e maior que zero
- Validação do tipo de cartão (cardType) da Transação como obrigatório e valor como DÉBITO ou CRÉDITO