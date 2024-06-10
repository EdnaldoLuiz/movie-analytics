package com.ednaldoluiz.moviedash.export.strategy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ednaldoluiz.moviedash.model.Movie;

public interface ExportStrategy {

    File export(String fileName, List<Movie> movies) throws IOException;
    
}
