package com.ednaldoluiz.moviedash.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ednaldoluiz.moviedash.model.enums.FileExportType;
import com.ednaldoluiz.moviedash.service.FileExportService;

import static com.ednaldoluiz.moviedash.constant.APIConstants.*;
import static com.ednaldoluiz.moviedash.docs.FileExportDocs.*;
import static com.ednaldoluiz.moviedash.utils.APIUtils.ATTACHMENT;
import static com.ednaldoluiz.moviedash.utils.APIUtils.CONTENT_DISPOSITION;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(API_V1 + FILE_EXPORT)
@RequiredArgsConstructor
@Tag(name = FILE_EXPORT_CONTROLLER_NAME, description = FILE_EXPORT_CONTROLLER_DESCRIPTION)
public class FileExportController {

    private final FileExportService service;

    @GetMapping("/export")
    @Operation(summary = FILE_EXPORT_SUMMARY, description = FILE_EXPORT_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = FILE_EXPORT_RESPONSE_200),
            @ApiResponse(responseCode = "404", description = FILE_EXPORT_RESPONSE_404),
    })
    public ResponseEntity<Resource> exportData(
            @Parameter(description = "Tipo de formato de exportação") @RequestParam FileExportType type) throws IOException {

        log.info("Exportando dados para o formato: {}", type.getValue());
        String fileName = "movies." + type.getValue();
        File file = service.exportMovies(fileName, type);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        return ResponseEntity.ok()
                .header(CONTENT_DISPOSITION, ATTACHMENT + fileName)
                .contentType(MediaType.parseMediaType("application/" + type.getValue()))
                .body(resource);
    }
}