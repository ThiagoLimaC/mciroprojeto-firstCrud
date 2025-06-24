
# ProjetoBackendJava

<img src="videoApiUsuarioJava.gif" width=800>

## Descrição

API RESTful desenvolvida em Java com Spring Boot, utilizando Spring Data JPA para persistência de dados e PostgreSQL como banco de dados. A API gerencia usuários, permitindo operações de cadastro, consulta, atualização e remoção. O projeto foi criado com foco em aprendizagem dos mecanismos do Spring para construção de APIs.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.2
- Spring Data JPA
- PostgreSQL
- ModelMapper
- Springdoc OpenAPI (Swagger)
- Maven

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/ThiagoLimaC/ProjetoBackendJava.git
   ```

2. **Configure o banco de dados:**

- Altere as propriedades em `src/main/resources/application.properties` conforme seu ambiente:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/usuariosDb
spring.datasource.username=postgres
spring.datasource.password=123456

```

3. Instale as dependências e execute o projeto:

- `mvn spring-boot:run`

4. Acesse a documentação Swagger:
- http://localhost:8083/swagger-ui.html

## Endpoints Principais

| Método | Endpoint           | Descrição                     |
|--------|--------------------|-------------------------------|
| GET    | /api/usuarios      | Lista todos os usuarios       |
| GET    | /api/usuarios/{id} | Busca usuario por ID          |
| POST   | /api/usuarios      | Cadastra um novo usuario      |
| PUT    | /api/usuarios/{id} | Atualiza um usuario existente |
| DELETE | /api/usuarios/{id} | Remove um usuario pelo ID     |

## Exemplo de Requisição (JSON)
- POST/api/produtos

```
{
  "nome": "João Silva",
  "telefone": "11999999999",
  "email": "joao@email.com",
  "dataNascimento": "1990-05-20"
}
```

Observações
O projeto utiliza ModelMapper para conversão entre entidades e DTOs.
O Springdoc OpenAPI gera automaticamente a documentação interativa da API.
Certifique-se de que o banco de dados PostgreSQL esteja rodando antes de iniciar a aplicação.
Os IDs dos usuários são gerados automaticamente pelo banco de dados.