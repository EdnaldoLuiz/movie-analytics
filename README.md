## TMDB Controller

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
            <td>Salvar filmes do TMDB</td>
        </tr>
    </tbody>
</table>

## Genres Controller

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

## Movies Controller

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

<h2>Tech Stack Utilizada 🛠️</h2>

<table align="center" width=1000px>
    <thead>
        <tr>
            <th><img src="https://skillicons.dev/icons?i=mysql" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=hibernate" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=spring" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=java" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=redis" width=100px height=100px/></th>
            <th><img src="https://skillicons.dev/icons?i=idea" width=100px height=100px/></th>
        </tr>
    </thead>
    <tbody align="center">
        <tr>
            <td>MySQL 🔖 8.1.0</td>
            <td>Hibernate 🔖 6.3</td>
            <td>Spring Boot 🔖 3.2.3</td>
            <td>Java 🔖 17</td>
            <td>Redis 🔖 7.2.4</td>
            <td>IntelliJ 🔖 2023.3.2</td>
        </tr>
    </tbody>
</table>