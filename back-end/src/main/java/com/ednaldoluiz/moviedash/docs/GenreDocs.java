package com.ednaldoluiz.moviedash.docs;

public final class GenreDocs {

    /**
     * ------------------------- Count de Gêneros -------------------------
     */

    public static final String GENRE_COUNT_SUMMARY = "Contar a Quantidade de Filmes por Gênero";
    public static final String GENRE_COUNT_RESPONSE_200 = "Quantidade de filmes por gênero retornada com sucesso";
    public static final String GENRE_COUNT_RESPONSE_404 = "Nenhum filme encontrado para o gênero solicitado";

    /**
     * ------------------------- Count do Total de Gêneros -------------------------
     */

    public static final String GENRE_TOTAL_SUMMARY = "Contar a Quantidade Total de Gêneros";
    public static final String GENRE_TOTAL_RESPONSE_200 = "Quantidade total de gêneros retornada com sucesso";
    public static final String GENRE_TOTAL_RESPONSE_404 = "Nenhum gênero encontrado";

    /**
     * ------------------------- Count de Gêneros Mais Populares
     * -------------------------
     */

    public static final String GENRE_POPULARITY_SUMMARY = "Contar os Gêneros Mais Populares";
    public static final String GENRE_POPULARITY_RESPONSE_200 = "Gêneros mais populares retornados com sucesso";
    public static final String GENRE_POPULARITY_RESPONSE_404 = "Nenhum gênero popular encontrado";

    /**
     * ------------------------- Count de Média de Votos por Gênero
     * -------------------------
     */

    public static final String GENRE_VOTE_AVERAGE_SUMMARY = "Contar a Média de Votos por Gênero";
    public static final String GENRE_VOTE_AVERAGE_RESPONSE_200 = "Média de votos por gênero retornada com sucesso";
    public static final String GENRE_VOTE_AVERAGE_RESPONSE_404 = "Nenhum gênero encontrado para calcular a média de votos";

    /**
     * ------------------------- Controller de Gêneros -------------------------
     */

    public static final String GENRE_CONTROLLER_NAME = "Controller de Analisar Gêneros";

    public static final String GENRE_CONTROLLER_DESCRIPTION = "<h3>Este controlador é responsável por realizar operações relacionadas a <b>Gêneros</b></h3>"
            + "<ul>"
            + "<li>Fornece um endpoint para contar a quantidade de filmes por gênero.</li>"
            + "<li>Fornece um endpoint para contar a quantidade total de gêneros.</li>"
            + "<li>Fornece um endpoint para identificar os gêneros com maior crescimento de popularidade.</li>"
            + "<li>Fornece um endpoint para identificar os gêneros mais populares.</li>"
            + "</ul>";

    public static final String GENRE_COUNT_DESCRIPTION = "<h2>Este endpoint conta a quantidade de filmes por gênero.</h2>"
            + "<h3>Parâmetros de Busca:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p><b>ID do Gênero</b>: ID do gênero para a consulta. </p></li>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Se nenhum filme for encontrado para o gênero solicitado, uma mensagem será retornada.</p></li>"
            + "</ul>";

    public static final String GENRE_TOTAL_DESCRIPTION = "<h2>Este endpoint conta a quantidade total de gêneros.</h2>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Se nenhum gênero for encontrado, uma mensagem será retornada.</p></li>"
            + "</ul>";

    public static final String GENRE_VOTE_AVERAGE_DESCRIPTION = "<h2>Este endpoint conta a média de votos por gênero.</h2>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Se nenhum gênero for encontrado para calcular a média de votos, uma mensagem será retornada.</p></li>"
            + "</ul>";

    public static final String GENRE_POPULARITY_DESCRIPTION = "<h2>Este endpoint conta os gêneros mais populares.</h2>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            + "<li><p>Se nenhum gênero popular for encontrado, uma mensagem será retornada.</p></li>"
            + "</ul>";

}
