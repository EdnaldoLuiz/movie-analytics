
<h2 id="api-rest">API Rest 🌐</h2>

### TMDB Controller

<table align=center>
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Método</th>
            <th>Status</th>
            <th>Response</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody align=center>
        <tr>
            <td>/api/v1/tmdb/fetch</td>
            <td>GET</td>
            <td>200</td>
            <td></td>
            <td>Salvar filmes da API do TMDB no banco de dados local</td>
        </tr>
    </tbody>
</table>

### Genres Controller

<table align=center>
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Método</th>
            <th>Status</th>
            <th>Response</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody align=center>
        <tr>
            <td>/api/v1/genres/count</td>
            <td>GET</td>
            <td>200, 404</td>
            <td>GenreProjection</td>
            <td>Contar a Quantidade de Filmes por Gênero</td>
        </tr>
        <tr>
            <td>/api/v1/genres/total</td>
            <td>GET</td>
            <td>200, 404</td>
            <td>Map&lt;String, Long&gt;</td>
            <td>Contar a Quantidade Total de Gêneros</td>
        </tr>
    </tbody>
</table>

### Movies Controller

<table align=center>
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Método</th>
            <th>Status</th>
            <th>Response</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody align=center>
        <tr>
            <td>/api/v1/movies/all</td>
            <td>GET</td>
            <td>200, 404</td>
            <td>Page&lt;MovieResponseDTO&gt;</td>
            <td>Buscar todos os Filmes com Paginação</td>
        </tr>
        <tr>
            <td>/api/v1/movies/top10</td>
            <td>GET</td>
            <td>200, 404</td>
            <td>Page&lt;MovieResponseDTO&gt;</td>
            <td>Buscar uma Página com o Top 10 de Filmes</td>
        </tr>
        <tr>
            <td>/api/v1/movies/top5</td>
            <td>GET</td>
            <td>200, 404</td>
            <td>Page&lt;MovieResponseDTO&gt;</td>
            <td>Buscar uma Página com o Top 5 Filmes por Ano</td>
        </tr>
        <tr>
            <td>/api/v1/movies/search</td>
            <td>GET</td>
            <td>200, 404</td>
            <td>Page&lt;Movie&gt;</td>
            <td>Buscar todos os Filmes com Paginação</td>
        </tr>
    </tbody>
</table>

<h2 id="principais-bibliotecas">Principais Dependências 📚</h2>

### Spring Boot 🌱

Bibliotecas Spring Boot base utilizadas no projeto:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### Lombok 🌶️

Biblioteca utilizada para facilitar o desenvolvimento, removendo código boilerplate:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

### Banco de Dados 🛢️

Bibliotecas utilizadas para migração e estruturação do banco de dados SQL com Flyway e Postgres:

```xml
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-database-postgresql</artifactId>
    <version>10.10.0</version>
    <scope>runtime</scope>
</dependency>
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <scope>runtime</scope>
</dependency>
```

### Cache com Redis 🟥🔄

Biblioteca utilizada para acesso o armazenamento em cache com Redis:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

### Docker Compose 🐳

Biblioteca utilizada para facilitar a inicialização do docker-compose ao iniciar um projeto Spring:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-docker-compose</artifactId>
    <scope>runtime</scope>
</dependency>
```

### Documentação 📖

Bibliotecas utilizadas para documentação com Springdoc OpenAPI e visualização com Swagger:

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
    <version>1.5.12</version>
</dependency>
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-data-rest</artifactId>
    <version>1.5.12</version>
</dependency>
```


<h2>Tech Stack Utilizada 🛠️</h2>

<table align="center" width=1000px>
    <thead>
        <tr>
            <th><img src="https://skillicons.dev/icons?i=postgres" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=hibernate" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=spring" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=java" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=redis" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=idea" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=docker" width=100px height=100px/></th>
        </tr>
    </thead>
    <tbody align="center">
        <tr>
            <td>Postgres 🔖 16.2</td>
            <td>Hibernate 🔖 6.3</td>
            <td>Spring Boot 🔖 3.2.3</td>
            <td>Java 🔖 17.0.6</td>
            <td>Redis 🔖 7.2.4</td>
            <td>IntelliJ 🔖 2023.3.2</td>
            <td>Docker 🔖 4.29.0</td>
        </tr>
    </tbody>
</table>