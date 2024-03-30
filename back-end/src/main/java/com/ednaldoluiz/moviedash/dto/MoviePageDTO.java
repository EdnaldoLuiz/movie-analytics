package com.ednaldoluiz.moviedash.dto;

import java.util.List;

public record MoviePageDTO(int page, List<MovieResponseDTO> results) {}
