package com.ednaldoluiz.moviedash.export.strategy;

import static com.ednaldoluiz.moviedash.utils.APIUtils.FILE_HEADER;

import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ednaldoluiz.moviedash.dto.response.MovieExportDTO;
import com.ednaldoluiz.moviedash.model.Movie;
import com.opencsv.CSVWriter;

@Component
public class CSVExportStrategy implements ExportStrategy {

    @Override
    public File export(String fileName, List<Movie> movies) throws IOException {
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeNext(FILE_HEADER);

        movies.forEach(movie -> {
            csvWriter.writeNext(new MovieExportDTO(movie).toArray());
        });
        csvWriter.close();
        return file;
    }
}
