


<h2>Strategy Pattern ♟️</h2>

### Padrão de Projeto Strategy

No projeto utilizamos o padrão de projeto Strategy para lidar com a exportação de dados de filmes para diferentes formatos de arquivo, especificamente CSV e Excel. permitindo que a logica de exportação seja selecionado em tempo de execução.

### Estrutura

A estrutura do padrão Strategy em nosso projeto é composta por uma interface ExportStrategy e duas classes concretas CSVExportStrategy e ExcelExportStrategy que implementam essa interface. A interface define um método export que recebe um nome de arquivo e uma lista de filmes para exportar.

<div align=center>
    <img width=500px src="https://github.com/EdnaldoLuiz/movie-analytics/assets/112354693/0773a2f8-acf0-4066-ba0b-ad631d591ae4">    
</div>

As classes concretas implementam o método export de maneira específica para cada formato de arquivo. CSVExportStrategy escreve os dados em um arquivo CSV usando a biblioteca CSVWriter, enquanto ExcelExportStrategy cria uma planilha Excel usando a biblioteca XSSFWorkbook.

No serviço FileExportService, temos um mapa strategies que mapeia um FileExportType (um enum que representa o tipo de arquivo) para uma instância de ExportStrategy. Isso nos permite selecionar a estratégia de exportação correta com base no tipo de arquivo desejado em tempo de execução.

### Por que o Padrão Strategy?

Ele oferece uma maneira flexível de selecionar um algoritmo em tempo de execução. Isso nos permite adicionar facilmente suporte para novos formatos de arquivo no futuro, simplesmente adicionando novas classes que implementam a interface ExportStrategy e adicionando-as ao Map no serviço, promovendo a separação de preocupações e tornando o código mais testável, pois cada estratégia de exportação pode ser testada isoladamente.

<h2 id="api-rest">API Rest 🌐</h2>

### TMDB Controller

<table align=center>
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Método</th>
            <th>Response</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody align=center>
        <tr>
            <td>/api/v1/tmdb/fetch</td>
            <td>GET</td>
            <td>Void</td>
            <td>Salvar filmes da API do TMDB no banco de dados local</td>
        </tr>
        <tr>
            <td>/api/v1/tmdb/delete</td>
            <td>DELETE</td>
            <td>Void</td>
            <td>Deletar todos os filmes de gêneros específicos</td>
        </tr>
    </tbody>
</table>

### Genres Controller

<table align=center>
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Método</th>
            <th>Response</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody align=center>
        <tr>
            <td>/api/v1/genres/count</td>
            <td>GET</td>
            <td>GenreProjection</td>
            <td>Contar a Quantidade de Filmes por Gênero</td>
        </tr>
        <tr>
            <td>/api/v1/genres/total</td>
            <td>GET</td>
            <td>Map&lt;String, Long&gt;</td>
            <td>Contar a Quantidade Total de Gêneros</td>
        </tr>
        <tr>
            <td>/api/v1/genres/vote-average</td>
            <td>GET</td>
            <td>Map&lt;String, Double&gt;</td>
            <td>Calcular a média de votos por gênero</td>
        </tr>
        <tr>
            <td>/api/v1/genres/popular-genres</td>
            <td>GET</td>
            <td>List&lt;GenreProjection&gt;</td>
            <td>Obter os gêneros mais populares</td>
        </tr>
    </tbody>
</table>

### Movies Controller

<table align=center>
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Método</th>
            <th>Response</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody align=center>
        <tr>
            <td>/api/v1/movies/all</td>
            <td>GET</td>
            <td>Page&lt;MovieResponseDTO&gt;</td>
            <td>Buscar todos os Filmes com Paginação</td>
        </tr>
        <tr>
            <td>/api/v1/movies/top10</td>
            <td>GET</td>
            <td>Page&lt;MovieResponseDTO&gt;</td>
            <td>Buscar uma Página com o Top 10 de Filmes</td>
        </tr>
        <tr>
            <td>/api/v1/movies/top5</td>
            <td>GET</td>
            <td>Page&lt;MovieResponseDTO&gt;</td>
            <td>Buscar uma Página com o Top 5 Filmes por Ano</td>
        </tr>
        <tr>
            <td>/api/v1/movies/search</td>
            <td>GET</td>
            <td>Page&lt;Movie&gt;</td>
            <td>Buscar todos os Filmes com Paginação</td>
        </tr>
    </tbody>
</table>

### File Export Controller

<table align=center>
    <thead>
        <tr>
            <th>Endpoint</th>
            <th>Método</th>
            <th>Response</th>
            <th>Descrição</th>
        </tr>
    </thead>
    <tbody align=center>
        <tr>
            <td>/api/v1/file-export/export</td>
            <td>GET</td>
            <td>Resource</td>
            <td>Exportar dados de filmes para um arquivo</td>
        </tr>
    </tbody>
</table>

<h2>Modelo Relacional</h2>

```mermaid
classDiagram
direction LR
    class Genre {
        - id: Long
        - name: String
        - movies: List&lt;Movie&gt
    }

    class Movie {
        - id: Long
        - adult: Boolean
        - poster: String
        - language: String
        - title: String
        - description: String
        - popularity: Double
        - releaseDate: LocalDate
        - video: Boolean
        - voteAverage: Double
        - voteCount: Integer
        - genres: List&lt;Genre&gt
    }

    Genre "1..*" -- "0..*" Movie : classificado como
```

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

### Exportar Arquivos 📁

Bibliotecas utilizadas para a exportação de arquivos CSV e Excel:

```xml
<!-- CSV -->
<dependency>
    <groupId>com.opencsv</groupId>
    <artifactId>opencsv</artifactId>
    <version>5.6</version>
</dependency>
<!-- Excel -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.5</version>
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
