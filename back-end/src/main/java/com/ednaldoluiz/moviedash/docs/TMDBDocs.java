package com.ednaldoluiz.moviedash.docs;

public final class TMDBDocs {

    public static final String TMDB_FETCH_SUMMARY = "Salvar filmes da API do TMDB";
    
    public static final String TMDB_DELETE_SUMMARY = "Deletar todos os filmes";

    public static final String TMDB_CONTROLLER_NAME = "Controller de Consumir Filmes";

    public static final String TMDB_CONTROLLER_DESCRIPTION = "<h3>Este controlador é responsável por realizar operações relacionadas ao <b>TMDB</b></h3>"
            + "<ul>"
            +   "<li>Fornece um endpoint para buscar e salvar dados do TMDB.</li>"
            +   "<li>Fornece um endpoint para remoção de filmes da base de dados.</li>"
            + "</ul>";

    public static final String TMDB_FETCH_DATA = "<h2>Este endpoint busca e salva dados do TMDB.</h2>"
            + "<h3>Parâmetros de Busca:</h3>"
            + "<hr>"
            + "<ul>"
            +   "<li><p><b>Numero de páginas</b>: Número de páginas de dados para buscar do TMDB. </p></li>"
            +   "<li><p><b>Gêneros</b>: IDs dos gêneros dos filmes para buscar. Sendo os Seguintes gêneros disponiveis:</p>"
            + "<table>"
            + "<tr>"
            +   "<td><p><b>Ação</b>: 28</p></td>"
            +   "<td><p><b>Aventura</b>: 12</p></td>"
            +   "<td><p><b>Animação</b>: 16</p></td>"
            +   "<td><p><b>Comédia</b>: 35</p></td>"
            +   "<td><p><b>Crime</b>: 80</p></td>"
            + "</tr>"
            + "<tr>"
            +   "<td><p><b>Documentário</b>: 99</p></td>"
            +   "<td><p><b>Drama</b>: 18</p></td>"
            +   "<td><p><b>Família</b>: 10751</p></td>"
            +   "<td><p><b>Fantasia</b>: 14</p></td>"
            +   "<td><p><b>História</b>: 36</p></td>"
            + "</tr>"
            + "<tr>"
            +   "<td><p><b>Terror</b>: 27</p></td>"
            +   "<td><p><b>Música</b>: 10402</p></td>"
            +   "<td><p><b>Mistério</b>: 9648</p></td>"
            +   "<td><p><b>Romance</b>: 10749</p></td>"
            +   "<td><p><b>Ficção Científica</b>: 878</p></td>"
            + "</tr>"
            + "<tr>"
            +   "<td><p><b>Filme de TV</b>: 10770</p></td>"
            +   "<td><p><b>Suspense</b>: 53</p></td>"
            +   "<td><p><b>Guerra</b>: 10752</p></td>"
            +   "<td><p><b>Ocidental</b>: 37</p></td>"
            + "</tr>"
            + "</table>"
            + "</li>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            +   "<li><p>Os dados são buscados do TMDB e salvos no banco de dados local.</p></li>"
            +   "<li><p>Se nenhum ID de Gênero for informado, será buscado por todos.</p></li>"
            + "</ul>";

    public static final String TMDB_DELETE_DATA = "<h2>Este endpoint deleta todos os filmes de acordo com o genero informado ou todos os filmes se nenhum genero for informado.</h2>"
            + "<h3>Parâmetros:</h3>"
            + "<hr>"
            + "<ul>"
            +   "<li><p><b>Ids dos Gêneros</b>: Uma lista com os Ids dos Gêneros.</p></li>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            +   "<li><p>Se nenhum valor for informado na lista, todos os filmes serão deletados.</p></li>"
            +   "<li><p>Não é possivel recuperar os filmes deletados.</p></li>"
            + "</ul>";
}