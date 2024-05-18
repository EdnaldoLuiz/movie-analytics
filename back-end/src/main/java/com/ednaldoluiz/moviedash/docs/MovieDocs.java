package com.ednaldoluiz.moviedash.docs;

public final class MovieDocs {

    /**
     * ------------------------- Busca de todos em Filmes -------------------------
     */

    public static final String MOVIE_ALL_SUMMARY = "Buscar todos os Filmes com Paginação";
    public static final String MOVIE_ALL_RESPONSE_200 = "Lista de filmes paginada retornada com sucesso";
    public static final String MOVIE_ALL_RESPONSE_404 = "Nenhum filme encontrado na página solicitada";

    /**
     * ------------------------- Top 10 Filmes -------------------------
     */

    public static final String MOVIE_TOP10_SUMMARY = "Buscar uma Página com o Top 10 de Filmes";
    public static final String MOVIE_TOP10_RESPONSE_200 = "Top 10 filmes retornados com sucesso";
    public static final String MOVIE_TOP10_RESPONSE_404 = "Nenhum filme encontrado para os gêneros solicitados";

    /**
     * ------------------------- Top 5 Filmes -------------------------
     */

    public static final String MOVIE_TOP5_SUMMARY = "Buscar uma Página com o Top 5 Filmes por Ano";
    public static final String MOVIE_TOP5_RESPONSE_200 = "Top 5 filmes retornados com sucesso";
    public static final String MOVIE_TOP5_RESPONSE_404 = "Nenhum filme encontrado para os gêneros e ano solicitado";

    /**
     * ------------------------- Busca por Filmes -------------------------
     */

    public static final String MOVIE_SEARCH_SUMMARY = "Buscar todos os Filmes com Paginação";
    public static final String MOVIE_SEARCH_RESPONSE_200 = "Filmes com o título solicitado encontrados com sucesso";
    public static final String MOVIE_SEARCH_RESPONSE_404 = "Nenhum filme encontrado com o título solicitado";

    /**
     * ------------------------- Controller de Filmes -------------------------
     */

    public static final String MOVIE_CONTROLLER_NAME = "Controller de Buscar Filmes";

    public static final String MOVIE_CONTROLLER_DESCRIPTION = "<h3>Este controlador é responsável por realizar operações CRUD relacionadas a <b>Filmes</b></h3>"
            + "<ul>"
            +   "<li>Fornece endpoints para buscar todos os filmes, os 10 melhores filmes e pesquisar filmes por título.</li>"        + "<li>Fornece um endpoint para buscar todos os filmes com paginação.</li>"
            +   "<li>Fornece um endpoint para buscar o Top 10 de filmes.</li>"
            +   "<li>Fornece um endpoint para buscar o Top 5 de filmes por ano.</li>"
            +   "<li>Fornece um endpoint para buscar filmes por título.</li>"          
            + "</ul>";

    public static final String MOVIE_ALL = "<h2>Este endpoint busca todos os filmes com paginação.</h2>"
            + "<h3>Parâmetros de Busca:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p><b>página</b>: Número da página para a consulta. </p></li>"
            + "<li><p><b>tamanho</b>: Quantidade de filmes por página. </p></li>"
            + "<li><p><b>sort</b>: Tipo de Ordenação da Página, recebendo um dos valores: </p></li>"
            + "<ul>"
            + "<li><p><b>voteAverage</b>: Ordena pela média de votos.</p></li>"
            + "<li><p><b>releaseDate</b>: Ordena pela data de lançamento.</p></li>"
            + "<li><p><b>voteCount</b>: Ordena pela quantidade de votos.</p></li>"
            + "<li><p><b>popularity</b>: Ordena pela popularidade.</p></li>"
            + "</ul>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Os filmes são ordenados por data de lançamento em ordem decrescente.</p></li>"
            + "</ul>";

    public static final String MOVIE_TOP_10 = "<h2>Este endpoint busca os 10 melhores filmes.</h2>"
            + "<h3>Parâmetros de Busca:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p><b>IDs dos Gêneros</b>: IDs dos gêneros dos filmes. </p></li>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Os filmes são ordenados por média de votos em ordem decrescente.</p></li>"
            + "</ul>";

    public static final String MOVIE_TOP_5 = "<h2>Este endpoint busca os 5 melhores filmes por ano.</h2>"
            + "<h3>Parâmetros de Busca:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p><b>anos</b>: Quantidade de filmes por página. </p></li>"
            + "<li><p><b>IDs dos Gêneros</b>: IDs dos gêneros dos filmes. </p></li>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Os filmes são ordenados por média de votos em ordem decrescente.</p></li>"
            + "</ul>";

    public static final String MOVIE_SEARCH = "<h2>Este endpoint busca filmes por título com paginação.</h2>"
            + "<h3>Parâmetros de Busca:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p><b>título</b>: Título do filme para a pesquisa. </p></li>"
            + "<li><p><b>página</b>: Número da página para a consulta. </p></li>"
            + "<li><p><b>tamanho</b>: Quantidade de filmes por página. </p></li>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Os filmes são ordenados por título.</p></li>"
            + "</ul>";
}