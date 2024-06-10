package com.ednaldoluiz.moviedash.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ednaldoluiz.moviedash.export.strategy.CSVExportStrategy;
import com.ednaldoluiz.moviedash.export.strategy.ExcelExportStrategy;
import com.ednaldoluiz.moviedash.export.strategy.ExportStrategy;
import com.ednaldoluiz.moviedash.model.Movie;
import com.ednaldoluiz.moviedash.model.enums.FileExportType;
import com.ednaldoluiz.moviedash.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileExportService {

    private final MovieRepository movieRepository;
    private final Map<FileExportType, ExportStrategy> strategies;

    public FileExportService(MovieRepository movieRepository, CSVExportStrategy csvStrategy,
            ExcelExportStrategy excelStrategy) {
        this.movieRepository = movieRepository;
        this.strategies = new HashMap<>();
        this.strategies.put(FileExportType.CSV, csvStrategy);
        this.strategies.put(FileExportType.EXCEL, excelStrategy);
    }

    public File exportMovies(String fileName, FileExportType type) throws IOException {
        log.info("Exportação sendo feita para o formato: {}", type.getValue());
        List<Movie> movies = movieRepository.findAllWithGenres();
        return strategies.get(type).export(fileName, movies);
    }
}
