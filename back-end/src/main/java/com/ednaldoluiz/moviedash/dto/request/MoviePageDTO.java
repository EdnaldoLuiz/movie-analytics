package com.ednaldoluiz.moviedash.dto.request;

import java.util.List;

public record MoviePageDTO(int page, List<MovieRequestDTO> results) {}
