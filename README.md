# API REST para Gerenciamento de Pessoas

Este repositório contém uma API REST em Java para gerenciar um mapa de pessoas em memória. A API permite criar, ler, atualizar e excluir registros de pessoas, bem como consultar detalhes específicos de cada pessoa.


## Como Executar

Para executar a API, siga os passos abaixo:

1. Clone o repositório:
   ```sh
   git clone https://github.com/danilosvieira/SpringBoot-PersonAPI.git
   ```

2. Navegue até o diretório do projeto:
   ```sh
   cd SpringBoot-PersonAPI/personmanagementservice
   ```

3. Compile o projeto:
   ```sh
   mvn clean install
   ```

4. Execute a aplicação:
   ```sh
   mvn spring-boot:run
   ```

A aplicação estará disponível em `http://localhost:8080`.

## Endpoints Disponíveis

### 1. Listar todas as pessoas

- **Endpoint:** `GET` /person
- **Descrição:** Retorna a lista de todas as pessoas, ordenada por nome em ordem alfabética, no formato JSON.
- **Resposta de Sucesso:**
  ```json
  [
      {
          "id": 11,
          "nome": "Fernanda",
          "dataNascimento": "1993-08-03",
          "dataAdmissao": "2022-05-15"
      },
      {
          "id": 12,
          "nome": "Natan",
          "dataNascimento": "1990-12-05",
          "dataAdmissao": "2022-11-01"
      }
  ]
  ```

### 2. Incluir uma nova pessoa

- **Endpoint:** `POST` /person
- **Descrição:** Inclui uma nova pessoa no mapa em memória.
- **Regras:**
    - Se o ID não for especificado, será atribuído automaticamente o maior ID existente + 1.
    - Se o ID especificado já existir, retorna um erro de conflito.
- **Corpo da Requisição:**
  ```json
  {
      "id": 15,
      "nome": "Maria",
      "data_nascimento":"1989-05-16",
      "data_admissao":"2024-05-20"
  }
  ```
- **Resposta de Sucesso:**
  ```
  SUCESS: Pessoa incluída com sucesso.
  ```

### 3. Remover uma pessoa

- **Endpoint:** `DELETE` /person/**{id}**
- **Descrição:** Remove uma pessoa do mapa em memória pelo ID.
- **Regras:**
    - Se o ID não for encontrado, retorna um erro de registro não encontrado.
- **Resposta de Sucesso:**
  ```
  SUCESS: Pessoa excluída com sucesso.
  ```

### 4. Atualizar uma pessoa

- **Endpoint:** `PUT` /person/**{id}**
- **Descrição:** Atualiza todos os atributos de uma pessoa no mapa em memória pelo ID.
- **Regras:**
    - Se o ID não for encontrado, retorna um erro de registro não encontrado.
- **Corpo da Requisição:**
  ```json
  {
      "id":20,
      "nome": "Paulo",
      "data_nascimento":"1985-02-07",
      "data_admissao":"2024-02-01"
  }
  ```
- **Resposta de Sucesso:**
  ```
  SUCESS: Pessoa atualizada com sucesso.
  ```

### 5. Atualizar um atributo de uma pessoa

- **Endpoint:** `PATCH` /person/**{id}**
- **Descrição:** Atualiza atributos específicos de uma pessoa no mapa em memória pelo ID.
- **Regras:**
    - Se o ID não for encontrado, retorna um erro de registro não encontrado.
- **Corpo da Requisição:**
  ```json
  {
      "data_nascimento": "1989-04-20"
  }
  ```
- **Resposta de Sucesso:**
  ```
  SUCESS: Pessoa atualizada com sucesso.
  ```

### 6. Obter detalhes de uma pessoa

- **Endpoint:** `GET` /person/**{id}**
- **Descrição:** Retorna os detalhes de uma pessoa pelo ID.
- **Regras:**
    - Se o ID não for encontrado, retorna um erro de registro não encontrado.
- **Resposta de Sucesso:**
  ```json
  {
      "id": 11,
      "nome": "Fernanda",
      "dataNascimento": "1993-08-03",
      "dataAdmissao": "2022-05-15"
  }
  ```

### 7. Obter a idade de uma pessoa em diferentes formatos

- **Endpoint:** `GET` /person/**{id}**/age?**output**={days | months | years}
- **Descrição:** Retorna a idade atual de uma pessoa em dias, meses ou anos completos, de acordo com o parâmetro de formato de saída.
- **Regras:**
    - Se o ID não for encontrado, retorna um erro de registro não encontrado.
    - Se o parâmetro de formato de saída não for reconhecido, retorna um erro de requisição mal formatada.
- **Exemplos de Respostas:**
    - Dias: `GET /person/11/age?output=days`
      ```
      11266
      ```
    - Meses: `GET /person/11/age?output=months`
      ```
      370
      ```
    - Anos: `GET /person/11/age?output=years`
      ```
      30
      ```

### 8. Obter o salário de uma pessoa

- **Endpoint:** `GET` /person/**{id}**/salary?**output**={min | full}
- **Descrição:** Retorna o salário da pessoa.
- **Parâmetros de Saída:**
    - `min`: Retorna o salário em quantidade de salários mínimos.
    - `full`: Retorna o salário completo em reais.
- **Exemplos de Respostas:**
    - Salário Mínimo: `GET /person/11/salary?output=min`
      ```
      2,50
      ```
    - Salário Completo: `GET /person/11/salary?output=full`
      ```
      3259,36
      ```

