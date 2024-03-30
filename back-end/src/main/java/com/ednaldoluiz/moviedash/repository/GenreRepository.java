package com.ednaldoluiz.moviedash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ednaldoluiz.moviedash.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{
    
}
