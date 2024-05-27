package com.ednaldoluiz.moviedash.docs;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FileExportDocs {

    public static final String FILE_EXPORT_SUMMARY = "Exportar dados de filmes";
    public static final String FILE_EXPORT_RESPONSE_200 = "Dados de filmes exportados com sucesso";
    public static final String FILE_EXPORT_RESPONSE_404 = "Falha ao exportar dados de filmes";

    public static final String FILE_EXPORT_CONTROLLER_NAME = "Controller de Exportação de Arquivos";
    public static final String FILE_EXPORT_CONTROLLER_DESCRIPTION = "Este controlador é responsável por exportar dados de filmes para diferentes formatos.";

    public static final String FILE_EXPORT_DESCRIPTION = "<h2>Este endpoint exporta dados de filmes.</h2>"
            + "<h3>Parâmetros:</h3>"
            + "<hr>"
            + "<ul>"
            +   "<li><p><b>Tipo</b>: Tipo de arquivo que será exportado.</p></li>"
            + "</ul>"
            + "<h3>Observações:</h3>"
            + "<hr>"
            + "<ul>"
            +   "<li><p>Se a exportação falhar, uma mensagem de erro será retornada.</p></li>"
            + "</ul>";

}
