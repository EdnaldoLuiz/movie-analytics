package com.ednaldoluiz.moviedash.export.strategy;

import static com.ednaldoluiz.moviedash.utils.APIUtils.FILE_HEADER;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.ednaldoluiz.moviedash.dto.response.MovieExportDTO;
import com.ednaldoluiz.moviedash.model.Movie;

@Component
public class ExcelExportStrategy implements ExportStrategy {

    @Override
    public File export(String fileName, List<Movie> movies) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Movies");
        Row headerRow = sheet.createRow(0);

        IntStream.range(0, FILE_HEADER.length).forEach(i -> {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(FILE_HEADER[i]);
        });

        IntStream.range(0, movies.size()).forEach(i -> {
            Movie movie = movies.get(i);
            MovieExportDTO movieExportDTO = new MovieExportDTO(movie);
            Row row = sheet.createRow(i + 1);

            String[] data = movieExportDTO.toArray();
            IntStream.range(0, data.length).forEach(j -> {
                Cell cell = row.createCell(j);
                cell.setCellValue(data[j]);
            });
        });

        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
        }
        workbook.close();

        return new File(fileName);
    }
}
