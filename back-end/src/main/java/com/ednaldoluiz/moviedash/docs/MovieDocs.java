package com.ednaldoluiz.moviedash.docs;

public class MovieDocs {

    public static final String DESCRIPTION = "<h3>Este controlador é responsável por realizar operações CRUD relacionadas a <b>Filmes</b></h3>"
            + "<ul>"
            + "<li>Fornece endpoints para buscar todos os filmes, os 10 melhores filmes e pesquisar filmes por título.</li>"
            + "<li>Cada endpoint suporta paginação e ordenação.</li>"
            + "</ul>";

    public static final String ALL = "<h2>Este endpoint busca todos os filmes com paginação.</h2>"
            + "<h3>Parâmetros de Busca:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p><b>página</b>: Número da página para a consulta. </p></li>"
            + "<li><p><b>tamanho</b>: Quantidade de filmes por página. </p></li>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Os filmes são ordenados por data de lançamento em ordem decrescente.</p></li>"
            + "</ul>";

    public static final String TOP_10 = "<h2>Este endpoint busca os 10 melhores filmes.</h2>"
            + "<h3>Parâmetros de Busca:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p><b>tamanho</b>: Quantidade de filmes por página. </p></li>"
            + "<li><p><b>IDs dos Gêneros</b>: IDs dos gêneros dos filmes. </p></li>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Os filmes são ordenados por média de votos em ordem decrescente.</p></li>"
            + "</ul>";

    public static final String TOP_5 = "<h2>Este endpoint busca os 5 melhores filmes por ano.</h2>"
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

    public static final String SEARCH = "<h2>Este endpoint busca filmes por título com paginação.</h2>"
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